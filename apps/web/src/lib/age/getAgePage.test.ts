import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import { AgePageNotFoundError, getAgePage } from "./getAgePage";

const minimalAgePageResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/por-edad/3-anos/",
    title: "Mejores juguetes para niños de 3 años",
    metaDescription: "Descripción de prueba",
  },
  age: 3,
  ageLabel: "3 años",
  slug: "3-anos",
  breadcrumbs: [],
  header: {
    h1: "H1 de prueba",
    introductionParagraphs: ["Introducción de prueba"],
  },
  quickNavigation: [],
  quickSummary: [],
  optionsByNeed: [],
  featuredSelection: [],
  developmentSkills: [],
  buyingConsiderations: [],
  featuredGuides: [],
  featuredRankings: [],
  giftIdeas: [],
  informativeArticles: [],
  faq: [],
  otherAges: [],
  trustAuthority: { howWeSelect: "", analysisCriteria: [], editorialTransparency: [] },
  affiliation: { noticeText: "", shortNoticeText: "" },
  legalLinks: [],
  author: null,
  publishedAt: "2026-08-11",
  updatedAt: "2026-08-11",
};

describe("getAgePage", () => {
  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("returns the parsed response on a successful request", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(minimalAgePageResponse), { status: 200 }),
    );

    const page = await getAgePage("3-anos");

    expect(page.slug).toBe("3-anos");
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining("/api/age-pages/3-anos"),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("fetches the five-year hub with ISR", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(
        JSON.stringify({
          ...minimalAgePageResponse,
          seo: {
            ...minimalAgePageResponse.seo,
            canonicalUrl: "https://bebesfelices.es/por-edad/5-anos/",
          },
          age: 5,
          ageLabel: "5 años",
          slug: "5-anos",
        }),
        { status: 200 },
      ),
    );

    const page = await getAgePage("5-anos");

    expect(page.slug).toBe("5-anos");
    expect(page.age).toBe(5);
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining("/api/age-pages/5-anos"),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("normalizes legacy introduction string into introductionParagraphs", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(
        JSON.stringify({
          ...minimalAgePageResponse,
          header: {
            h1: "H1 legacy",
            introduction: "Texto introductorio en formato antiguo.",
          },
        }),
        { status: 200 },
      ),
    );

    const page = await getAgePage("4-anos");

    expect(page.header.introductionParagraphs).toEqual([
      "Texto introductorio en formato antiguo.",
    ]);
  });

  it("throws AgePageNotFoundError when the API responds with 404", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 404 }));

    await expect(getAgePage("2-anos")).rejects.toBeInstanceOf(AgePageNotFoundError);
  });

  it("throws a generic error for other non-ok responses", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 500 }));

    await expect(getAgePage("3-anos")).rejects.toThrow(/500/);
  });
});
