import { describe, expect, it } from "vitest";
import {
  LEGAL_LINKS,
  LEGAL_PAGES,
  LEGAL_PAGE_SLUGS,
  getLegalPage,
} from "./legalPages";
import { buildPageMetadata } from "@/lib/seo/metadata";

function visibleText(page: (typeof LEGAL_PAGES)[number]) {
  return page.sections
    .flatMap((section) => section.blocks)
    .flatMap((block) =>
      block.type === "paragraph" ? block.parts : block.items.flat(),
    )
    .map((part) => part.text)
    .join(" ");
}

describe("legalPages", () => {
  it("defines the five unique legal routes used by the footer", () => {
    expect(LEGAL_PAGE_SLUGS).toHaveLength(5);
    expect(new Set(LEGAL_PAGE_SLUGS).size).toBe(5);
    expect(LEGAL_LINKS).toEqual(
      LEGAL_PAGES.map((page) => ({
        label: page.label,
        href: `/${page.slug}/`,
      })),
    );
  });

  it("provides complete content and canonical metadata for every page", () => {
    for (const page of LEGAL_PAGES) {
      expect(page.title).toContain("BebesFelices");
      expect(page.metaDescription.length).toBeGreaterThan(50);
      expect(page.introduction.length).toBeGreaterThan(50);
      expect(page.updatedAt).toMatch(/^\d{4}-\d{2}-\d{2}$/);
      expect(page.canonicalUrl).toBe(
        `https://bebesfelices.es/${page.slug}/`,
      );
      expect(page.sections.length).toBeGreaterThan(4);
      expect(new Set(page.sections.map((section) => section.id)).size).toBe(
        page.sections.length,
      );

      const content = visibleText(page);
      expect(content).not.toMatch(/\b(?:TODO|TBD)\b|\[[A-Z_ -]+\]/);

      const metadata = buildPageMetadata({
        title: page.title,
        metaDescription: page.metaDescription,
        canonicalUrl: page.canonicalUrl,
      });
      expect(metadata.alternates?.canonical).toBe(page.canonicalUrl);
      expect(metadata.openGraph?.url).toBe(page.canonicalUrl);
    }

    expect(visibleText(getLegalPage("aviso-legal"))).toContain(
      "David Castel Castells",
    );
    expect(visibleText(getLegalPage("politica-privacidad"))).toContain(
      "David Castel Castells",
    );
  });

  it("states Amazon affiliation and the conditional Creators API status accurately", () => {
    const affiliation = visibleText(getLegalPage("informacion-afiliacion"));

    expect(affiliation).toContain(
      "En calidad de Afiliado de Amazon, obtengo ingresos por las compras adscritas que cumplen los requisitos aplicables.",
    );
    expect(affiliation).toContain(
      "En este momento no se afirma que la API esté activa.",
    );
  });
});
