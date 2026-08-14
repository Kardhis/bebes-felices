import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import { getProductPage, ProductPageNotFoundError } from "./getProductPage";
import { isAnalysisProductId } from "./analysisProductIds";

const minimalResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/analisis/patinete-3-ruedas/",
    title: "Patinete",
    metaDescription: "Descripción",
  },
  status: "PUBLISHED",
  productId: "patinete-3-ruedas",
  canonicalPath: "/analisis/patinete-3-ruedas/",
  breadcrumbs: [],
  header: { kicker: "Análisis", h1: "Patinete", introductionParagraphs: [] },
  category: "Movimiento",
  ageRange: "3-4 años",
  forWhom: "Inicio",
  editorialSummary: "Resumen",
  pros: [],
  cons: [],
  safetyNotes: [],
  buyingChecks: [],
  affiliateHref: null,
  faq: [],
  relatedLinks: [],
  trustAuthority: {
    howWeSelect: "",
    analysisCriteria: [],
    editorialTransparency: [],
  },
  affiliation: { noticeText: "", shortNoticeText: "" },
  legalLinks: [],
  author: null,
  publishedAt: "2026-08-14",
  updatedAt: "2026-08-14",
};

describe("analysis product pages", () => {
  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("fetches a published analysis with ISR", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(minimalResponse), { status: 200 }),
    );

    const page = await getProductPage("patinete-3-ruedas");

    expect(page.productId).toBe("patinete-3-ruedas");
    expect(page.affiliateHref).toBeNull();
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining("/api/product-pages/patinete-3-ruedas"),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("throws when the analysis is missing", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 404 }));

    await expect(getProductPage("bici-chicco-red-bullet")).rejects.toBeInstanceOf(
      ProductPageNotFoundError,
    );
  });

  it("does not treat the Chicco comparison spotlight as an analysis route", () => {
    expect(isAnalysisProductId("patinete-3-ruedas")).toBe(true);
    expect(isAnalysisProductId("bici-chicco-red-bullet")).toBe(false);
  });
});
