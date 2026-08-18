import { AgeFaq } from "@/components/age/AgeFaq";
import { BuyingConsiderations } from "@/components/age/BuyingConsiderations";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { CollectionProducts } from "@/components/collection/CollectionProducts";
import { EditorialHero } from "@/components/editorial/EditorialHero";
import { EditorialPageShell } from "@/components/editorial/EditorialPageShell";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import { TrustAuthority } from "@/components/home/TrustAuthority";
import type { CollectionPageResponse } from "@/lib/collection/getCollectionPage";
import {
  buildBreadcrumbListSchema,
  buildCollectionPageSchema,
  buildFaqPageSchema,
  buildItemListSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { SITE_URL } from "@/lib/seo/metadata";

type CollectionPageViewProps = {
  page: CollectionPageResponse;
};

export function CollectionPageView({ page }: CollectionPageViewProps) {
  const itemListEntries = page.products.flatMap((item) =>
    item.href ? [{ title: item.title, href: item.href }] : [],
  );

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
      <BuyingConsiderations items={page.buyingCriteria} />
      <CollectionProducts items={page.products} />
      <ContentLinkSection
        id="contenidos-relacionados"
        title="Contenidos relacionados"
        description="Vuelve a la categoría o amplía la información con estas páginas relacionadas."
        items={page.relatedLinks}
      />
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
