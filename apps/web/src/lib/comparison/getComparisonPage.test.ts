import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import {
  ComparisonPageNotFoundError,
  getComparisonPage,
} from "./getComparisonPage";

const minimalResponse = {
  seo: {
    canonicalUrl:
      "https://bebesfelices.es/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
    title: "Comparativa",
    metaDescription: "Descripción",
  },
  status: "DRAFT",
  slug: "mejores-bicicletas-sin-pedales-3-anos",
  targetAge: 3,
  breadcrumbs: [],
  header: { h1: "Comparativa", introductionParagraphs: [] },
  quickNavigation: [],
  quickSummary: [],
  methodology: { summary: "", criteria: [] },
  entries: [],
  buyingGuide: { sections: [] },
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
  publishedAt: "2026-08-13",
  updatedAt: "2026-08-13",
};

describe("getComparisonPage", () => {
  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("returns the comparison and uses ISR", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(minimalResponse), { status: 200 }),
    );

    const page = await getComparisonPage(minimalResponse.slug);

    expect(page.slug).toBe(minimalResponse.slug);
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining(`/api/comparison-pages/${minimalResponse.slug}`),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("throws a specific error for a missing comparison", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 404 }));

    await expect(getComparisonPage("desconocida")).rejects.toBeInstanceOf(
      ComparisonPageNotFoundError,
    );
  });

  it("includes the response status in other API errors", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 503 }));

    await expect(getComparisonPage(minimalResponse.slug)).rejects.toThrow(/503/);
  });
});
