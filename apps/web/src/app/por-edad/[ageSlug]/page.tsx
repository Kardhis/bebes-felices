import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { AgeFaq } from "@/components/age/AgeFaq";
import { AgePageHero } from "@/components/age/AgePageHero";
import { Breadcrumbs } from "@/components/age/Breadcrumbs";
import { BuyingConsiderations } from "@/components/age/BuyingConsiderations";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { DevelopmentSkills } from "@/components/age/DevelopmentSkills";
import { FeaturedSelection } from "@/components/age/FeaturedSelection";
import { OptionsByNeed } from "@/components/age/OptionsByNeed";
import { OtherAges } from "@/components/age/OtherAges";
import { QuickNavigation } from "@/components/age/QuickNavigation";
import { QuickSummary } from "@/components/age/QuickSummary";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import { SiteFooter } from "@/components/home/SiteFooter";
import { SiteHeader } from "@/components/home/SiteHeader";
import { TrustAuthority } from "@/components/home/TrustAuthority";
import { AGE_SLUGS, isAgeSlug } from "@/lib/age/ageSlugs";
import { AgePageNotFoundError, getAgePage } from "@/lib/age/getAgePage";
import {
  buildBreadcrumbListSchema,
  buildCollectionPageSchema,
  buildFaqPageSchema,
  buildItemListSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { buildPageMetadata, SITE_URL } from "@/lib/seo/metadata";

type Props = {
  params: Promise<{ ageSlug: string }>;
};

export function generateStaticParams() {
  return AGE_SLUGS.map((ageSlug) => ({ ageSlug }));
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { ageSlug } = await params;
  if (!isAgeSlug(ageSlug)) {
    return { title: "Página no encontrada | BebesFelices" };
  }

  try {
    const page = await getAgePage(ageSlug);
    return buildPageMetadata(page.seo);
  } catch (error) {
    if (error instanceof AgePageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

export default async function AgePage({ params }: Props) {
  const { ageSlug } = await params;
  if (!isAgeSlug(ageSlug)) {
    notFound();
  }

  let page;
  try {
    page = await getAgePage(ageSlug);
  } catch (error) {
    if (error instanceof AgePageNotFoundError) {
      notFound();
    }
    throw error;
  }

  const itemListEntries = page.featuredSelection.map((item) => ({
    title: item.title,
    href: item.href,
  }));
  const rankingsSection = (
    <ContentLinkSection
      id="rankings-destacados"
      title="Rankings destacados"
      description="Comparativas con selección razonada para esta edad."
      items={page.featuredRankings}
    />
  );

  return (
    <>
      <SiteHeader variant="inner" />
      <main>
        <Breadcrumbs items={page.breadcrumbs} />
        <AgePageHero
          h1={page.header.h1}
          age={page.age}
          ageLabel={page.ageLabel}
          introductionParagraphs={page.header.introductionParagraphs}
        />
        <DevelopmentSkills items={page.developmentSkills} />
        <BuyingConsiderations items={page.buyingConsiderations} />
        <ContentLinkSection
          id="guias-destacadas"
          title="Guías destacadas para esta edad"
          description="Contexto editorial para decidir con más información antes de comprar."
          items={page.featuredGuides}
        />
        <ContentLinkSection
          id="articulos-informativos"
          title="Contenidos informativos"
          description="Artículos sobre desarrollo y expectativas a esta edad."
          items={page.informativeArticles}
        />
        <AffiliationNotice noticeText={page.affiliation.noticeText} variant="compact" />
        <QuickNavigation items={page.quickNavigation} />
        <QuickSummary items={page.quickSummary} />
        <OptionsByNeed groups={page.optionsByNeed} />
        {ageSlug === "3-anos" && rankingsSection}
        <FeaturedSelection items={page.featuredSelection} />
        {ageSlug !== "3-anos" && rankingsSection}
        <ContentLinkSection
          id="ideas-de-regalo"
          title="Ideas de regalo"
          description="Inspiración por ocasión y presupuesto, sin perder utilidad."
          items={page.giftIdeas}
        />
        <AgeFaq items={page.faq} />
        <OtherAges items={page.otherAges} currentAgeLabel={page.ageLabel} />
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
        <JsonLd data={buildItemListSchema(itemListEntries, SITE_URL)} />
      )}
      {page.faq.length > 0 && <JsonLd data={buildFaqPageSchema(page.faq)} />}
    </>
  );
}
