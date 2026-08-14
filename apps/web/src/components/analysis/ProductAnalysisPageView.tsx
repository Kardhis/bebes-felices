import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { ProductAnalysisBody } from "@/components/analysis/ProductAnalysisBody";
import { EditorialHero } from "@/components/editorial/EditorialHero";
import { EditorialPageShell } from "@/components/editorial/EditorialPageShell";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import { TrustAuthority } from "@/components/home/TrustAuthority";
import type { ProductAnalysisResponse } from "@/lib/analysis/getProductPage";
import {
  buildArticleSchema,
  buildBreadcrumbListSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { SITE_URL } from "@/lib/seo/metadata";

type ProductAnalysisPageViewProps = {
  page: ProductAnalysisResponse;
};

export function ProductAnalysisPageView({ page }: ProductAnalysisPageViewProps) {
  return (
    <EditorialPageShell
      breadcrumbs={page.breadcrumbs}
      legalLinks={page.legalLinks}
      updatedAt={page.updatedAt}
    >
      <EditorialHero
        kicker={page.header.kicker}
        h1={page.header.h1}
        introductionParagraphs={page.header.introductionParagraphs}
      />
      <AffiliationNotice
        noticeText={page.affiliation.noticeText}
        variant="compact"
      />
      <ProductAnalysisBody
        category={page.category}
        ageRange={page.ageRange}
        forWhom={page.forWhom}
        editorialSummary={page.editorialSummary}
        pros={page.pros}
        cons={page.cons}
        safetyNotes={page.safetyNotes}
        buyingChecks={page.buyingChecks}
        affiliateHref={page.affiliateHref}
      />
      <ContentLinkSection
        id="contenidos-relacionados"
        title="Contenidos relacionados"
        description="Vuelve a la categoría y al hub de 3 años."
        items={page.relatedLinks}
      />
      <TrustAuthority
        howWeSelect={page.trustAuthority.howWeSelect}
        analysisCriteria={page.trustAuthority.analysisCriteria}
        editorialTransparency={page.trustAuthority.editorialTransparency}
        updatedAt={page.updatedAt}
      />
      <JsonLd data={buildBreadcrumbListSchema(page.breadcrumbs, SITE_URL)} />
      <JsonLd
        data={buildArticleSchema({
          url: page.seo.canonicalUrl,
          name: page.seo.title,
          description: page.seo.metaDescription,
          datePublished: page.publishedAt ?? page.updatedAt,
          dateModified: page.updatedAt,
        })}
      />
    </EditorialPageShell>
  );
}
