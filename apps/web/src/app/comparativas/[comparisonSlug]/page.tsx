import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { AgeFaq } from "@/components/age/AgeFaq";
import { Breadcrumbs } from "@/components/age/Breadcrumbs";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { QuickNavigation } from "@/components/age/QuickNavigation";
import { QuickSummary } from "@/components/age/QuickSummary";
import { ComparisonBuyingGuide } from "@/components/comparison/ComparisonBuyingGuide";
import { ComparisonHero } from "@/components/comparison/ComparisonHero";
import { ComparisonMethodology } from "@/components/comparison/ComparisonMethodology";
import { RankedProductList } from "@/components/comparison/RankedProductList";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import { SiteFooter } from "@/components/home/SiteFooter";
import { SiteHeader } from "@/components/home/SiteHeader";
import { TrustAuthority } from "@/components/home/TrustAuthority";
import {
  COMPARISON_SLUGS,
  isComparisonSlug,
} from "@/lib/comparison/comparisonSlugs";
import {
  ComparisonPageNotFoundError,
  getComparisonPage,
} from "@/lib/comparison/getComparisonPage";
import {
  buildBreadcrumbListSchema,
  buildCollectionPageSchema,
  buildFaqPageSchema,
  buildItemListSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { buildPageMetadata, SITE_URL } from "@/lib/seo/metadata";

type Props = {
  params: Promise<{ comparisonSlug: string }>;
};

export function generateStaticParams() {
  return COMPARISON_SLUGS.map((comparisonSlug) => ({ comparisonSlug }));
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { comparisonSlug } = await params;
  if (!isComparisonSlug(comparisonSlug)) {
    return { title: "Página no encontrada | BebesFelices" };
  }

  try {
    const page = await getComparisonPage(comparisonSlug);
    const metadata = buildPageMetadata(page.seo);
    return page.status === "DRAFT"
      ? { ...metadata, robots: { index: false, follow: false } }
      : metadata;
  } catch (error) {
    if (error instanceof ComparisonPageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

export default async function ComparisonPage({ params }: Props) {
  const { comparisonSlug } = await params;
  if (!isComparisonSlug(comparisonSlug)) {
    notFound();
  }

  let page;
  try {
    page = await getComparisonPage(comparisonSlug);
  } catch (error) {
    if (error instanceof ComparisonPageNotFoundError) {
      notFound();
    }
    throw error;
  }

  const itemListEntries = page.entries.map((entry) => ({
    title: entry.title,
    href: `#producto-${entry.productId}`,
  }));

  return (
    <>
      <SiteHeader variant="inner" />
      <main>
        <Breadcrumbs items={page.breadcrumbs} />
        <ComparisonHero
          h1={page.header.h1}
          targetAge={page.targetAge}
          introductionParagraphs={page.header.introductionParagraphs}
          shortAffiliationNotice={page.affiliation.shortNoticeText}
          isDraft={page.status === "DRAFT"}
        />
        <AffiliationNotice
          noticeText={page.affiliation.noticeText}
          variant="compact"
        />
        <QuickNavigation items={page.quickNavigation} />
        <QuickSummary
          items={page.quickSummary.map((item) => ({
            need: item.label,
            recommendation: item.reason,
            href: `#producto-${item.productId}`,
          }))}
        />
        <RankedProductList entries={page.entries} />
        <ComparisonMethodology
          summary={page.methodology.introduction}
          criteria={page.methodology.criteria.map((criterion, index) => ({
            id: `criterio-${index + 1}`,
            label: criterion.name,
            description: criterion.description,
          }))}
        />
        <ComparisonBuyingGuide sections={page.buyingGuide.sections} />
        <ContentLinkSection
          id="contenidos-relacionados"
          title="Contenidos relacionados"
          description="Más contexto para elegir según la edad y el tipo de actividad."
          items={page.relatedLinks}
        />
        <AgeFaq items={page.faq} />
        <TrustAuthority
          howWeSelect={page.trustAuthority.howWeSelect}
          analysisCriteria={page.trustAuthority.analysisCriteria}
          editorialTransparency={page.trustAuthority.editorialTransparency}
          updatedAt={page.updatedAt}
        />
      </main>

      <SiteFooter legalLinks={page.legalLinks} updatedAt={page.updatedAt} />

      <JsonLd data={buildBreadcrumbListSchema(page.breadcrumbs, SITE_URL)} />
      <JsonLd
        data={buildCollectionPageSchema({
          url: page.seo.canonicalUrl,
          name: page.seo.title,
          description: page.seo.metaDescription,
          dateModified: page.updatedAt,
        })}
      />
      {itemListEntries.length > 0 && (
        <JsonLd data={buildItemListSchema(itemListEntries, page.seo.canonicalUrl)} />
      )}
      {page.faq.length > 0 && <JsonLd data={buildFaqPageSchema(page.faq)} />}
    </>
  );
}
