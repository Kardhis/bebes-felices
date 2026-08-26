import { AgeFaq } from "@/components/age/AgeFaq";
import { ArticleAgeContent } from "@/components/article/ArticleAgeContent";
import { ArticleBody } from "@/components/article/ArticleBody";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { EditorialHero } from "@/components/editorial/EditorialHero";
import { EditorialPageShell } from "@/components/editorial/EditorialPageShell";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import { TrustAuthority } from "@/components/home/TrustAuthority";
import type { ArticlePageResponse } from "@/lib/article/getArticlePage";
import type { CategoryAge } from "@/lib/category/categoryAge";
import {
  buildArticleSchema,
  buildBreadcrumbListSchema,
  buildFaqPageSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { SITE_URL } from "@/lib/seo/metadata";

type ArticlePageViewProps = {
  page: ArticlePageResponse;
  initialAge?: CategoryAge;
};

export function ArticlePageView({ page, initialAge = 3 }: ArticlePageViewProps) {
  const ageSwitchable = (page.ageVariants ?? []).length > 0;

  return (
    <EditorialPageShell
      breadcrumbs={page.breadcrumbs}
      legalLinks={page.legalLinks}
      updatedAt={page.updatedAt}
    >
      {ageSwitchable ? (
        <ArticleAgeContent page={page} initialAge={initialAge} />
      ) : (
        <>
          <EditorialHero
            kicker={page.header.kicker}
            h1={page.header.h1}
            introductionParagraphs={page.header.introductionParagraphs}
          />
          <AffiliationNotice
            noticeText={page.affiliation.noticeText}
            variant="compact"
          />
          <ArticleBody sections={page.sections} />
          <ContentLinkSection
            id="contenidos-relacionados"
            title="Contenidos relacionados"
            description="Sigue el circuito editorial hacia el hub de 3 años y páginas de apoyo."
            items={page.relatedLinks}
          />
          <AgeFaq items={page.faq} />
          {page.faq.length > 0 && <JsonLd data={buildFaqPageSchema(page.faq)} />}
        </>
      )}
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
