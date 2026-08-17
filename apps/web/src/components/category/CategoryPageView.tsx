import { AgeFaq } from "@/components/age/AgeFaq";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { EditorialHero } from "@/components/editorial/EditorialHero";
import { EditorialPageShell } from "@/components/editorial/EditorialPageShell";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import { TrustAuthority } from "@/components/home/TrustAuthority";
import type { CategoryPageResponse } from "@/lib/category/getCategoryPage";
import {
  buildBreadcrumbListSchema,
  buildCollectionPageSchema,
  buildItemListSchema,
  buildFaqPageSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { SITE_URL } from "@/lib/seo/metadata";

type CategoryPageViewProps = {
  page: CategoryPageResponse;
};

export function CategoryPageView({ page }: CategoryPageViewProps) {
  const itemListEntries = page.childCollections.map((item) => ({
    title: item.title,
    href: item.href,
  }));

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
      <ContentLinkSection
        id="colecciones"
        title="Selecciones de esta categoría"
        description="Páginas editoriales con criterios de compra y enlaces a comparativas o análisis cuando existen."
        items={page.childCollections}
      />
      {page.relatedLinks.length > 0 && (
        <ContentLinkSection
          id="contenidos-relacionados"
          title="Contenidos relacionados"
          description="Guías y comparativas que complementan esta categoría."
          items={page.relatedLinks}
          tone="alt"
        />
      )}
      <AgeFaq items={page.faq} />
      <TrustAuthority
        howWeSelect={page.trustAuthority.howWeSelect}
        analysisCriteria={page.trustAuthority.analysisCriteria}
        editorialTransparency={page.trustAuthority.editorialTransparency}
        updatedAt={page.updatedAt}
      />
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
    </EditorialPageShell>
  );
}
