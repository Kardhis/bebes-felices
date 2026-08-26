import type { Metadata } from "next";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { EditorialHero } from "@/components/editorial/EditorialHero";
import { EditorialPageShell } from "@/components/editorial/EditorialPageShell";
import { GUIDE_SLUGS } from "@/lib/article/articleSlugs";
import { getArticlePage } from "@/lib/article/getArticlePage";
import { LEGAL_LINKS } from "@/lib/legal/legalPages";
import {
  buildBreadcrumbListSchema,
  buildCollectionPageSchema,
  buildItemListSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { buildPageMetadata, SITE_URL } from "@/lib/seo/metadata";

const PATH = "/guias/";
const CANONICAL = `${SITE_URL}${PATH}`;
const TITLE = "Guías para elegir juguetes de 3 a 5 años";
const DESCRIPTION =
  "Guías de compra y desarrollo para elegir juguetes según la edad, con criterios de seguridad y uso real.";

export const metadata: Metadata = buildPageMetadata({
  title: `${TITLE} | Bebes Felices`,
  metaDescription: DESCRIPTION,
  canonicalUrl: CANONICAL,
});

export default async function GuidesIndexPage() {
  const pages = (
    await Promise.all(
      GUIDE_SLUGS.map(async (slug) => {
        try {
          return await getArticlePage(slug);
        } catch {
          return null;
        }
      }),
    )
  ).filter((page) => page !== null);

  const guides = pages.map((page) => ({
    title: page.header.h1,
    href: page.canonicalPath,
    description:
      page.header.introductionParagraphs[0] ?? page.seo.metaDescription,
  }));
  const updatedAt =
    pages.map((page) => page.updatedAt).sort().at(-1) ?? "2026-08-17";

  const breadcrumbs = [
    { label: "Inicio", href: "/" },
    { label: "Guías", href: PATH },
  ];

  return (
    <EditorialPageShell
      breadcrumbs={breadcrumbs}
      legalLinks={LEGAL_LINKS}
      updatedAt={updatedAt}
    >
      <EditorialHero
        kicker="Guías"
        h1={TITLE}
        introductionParagraphs={[
          "Artículos para decidir con criterio: qué mirar antes de comprar y qué suele estar aprendiendo un niño de 3, 4 o 5 años.",
          "Empieza por la guía de compra si buscas un checklist. Las páginas de habilidades explican el desarrollo esperable de cada edad.",
        ]}
      />
      <ContentLinkSection
        id="listado-guias"
        title="Guías publicadas"
        description="Compra por edad y desarrollo, sin rankings inventados."
        items={guides}
      />
      <JsonLd data={buildBreadcrumbListSchema(breadcrumbs, SITE_URL)} />
      <JsonLd
        data={buildCollectionPageSchema({
          url: CANONICAL,
          name: TITLE,
          description: DESCRIPTION,
          dateModified: updatedAt,
        })}
      />
      <JsonLd data={buildItemListSchema(guides, SITE_URL)} />
    </EditorialPageShell>
  );
}
