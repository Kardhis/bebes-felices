import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { AgeFaq } from "@/components/age/AgeFaq";
import { AgePageHero } from "@/components/age/AgePageHero";
import { Breadcrumbs } from "@/components/age/Breadcrumbs";
import { BuyingConsiderations } from "@/components/age/BuyingConsiderations";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { DevelopmentSkills } from "@/components/age/DevelopmentSkills";
import { OptionsByNeed } from "@/components/age/OptionsByNeed";
import { OtherAges } from "@/components/age/OtherAges";
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
        {(page.featuredGuides.length > 0 || page.informativeArticles.length > 0) && (
          <section className="border-t border-[var(--color-border)] bg-[var(--color-bg-alt)]">
            <div
              className={
                page.featuredGuides.length > 0 && page.informativeArticles.length > 0
                  ? "mx-auto grid max-w-6xl gap-10 px-4 py-12 sm:px-6 sm:py-14 md:grid-cols-2 md:gap-12"
                  : "mx-auto grid max-w-6xl gap-10 px-4 py-12 sm:px-6 sm:py-14"
              }
            >
              <ContentLinkSection
                id="guias-destacadas"
                title="Guías destacadas para esta edad"
                description="Contexto editorial para decidir con más información antes de comprar."
                items={page.featuredGuides}
                embedded
              />
              <ContentLinkSection
                id="articulos-informativos"
                title="Contenidos informativos"
                description="Artículos sobre desarrollo y expectativas a esta edad."
                items={page.informativeArticles}
                embedded
              />
            </div>
          </section>
        )}
        <AffiliationNotice noticeText={page.affiliation.noticeText} variant="compact" />
        <OptionsByNeed groups={page.optionsByNeed} />
        <ContentLinkSection
          id="rankings-destacados"
          title="Rankings destacados"
          description="Comparativas con selección razonada para esta edad."
          items={page.featuredRankings}
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
      {page.faq.length > 0 && <JsonLd data={buildFaqPageSchema(page.faq)} />}
    </>
  );
}
