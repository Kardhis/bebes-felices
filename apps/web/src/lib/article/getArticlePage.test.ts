import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import {
  ArticlePageNotFoundError,
  getArticlePage,
} from "./getArticlePage";

const minimalResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/guias/habilidades-3-anos/",
    title: "Habilidades",
    metaDescription: "Descripción",
  },
  status: "PUBLISHED",
  slug: "habilidades-3-anos",
  canonicalPath: "/guias/habilidades-3-anos/",
  breadcrumbs: [],
  header: { kicker: "Guía", h1: "Habilidades", introductionParagraphs: [] },
  sections: [],
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

describe("getArticlePage", () => {
  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("returns the article and uses ISR", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(minimalResponse), { status: 200 }),
    );

    const page = await getArticlePage(minimalResponse.slug);

    expect(page.slug).toBe(minimalResponse.slug);
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining(`/api/article-pages/${minimalResponse.slug}`),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("throws a specific error for a missing article", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 404 }));

    await expect(getArticlePage("desconocida")).rejects.toBeInstanceOf(
      ArticlePageNotFoundError,
    );
  });
});
