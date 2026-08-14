import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import {
  CollectionPageNotFoundError,
  getCollectionPage,
} from "./getCollectionPage";

const minimalResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/juguetes-educativos/puzles/",
    title: "Puzles",
    metaDescription: "Descripción",
  },
  status: "PUBLISHED",
  slug: "puzles",
  canonicalPath: "/juguetes-educativos/puzles/",
  breadcrumbs: [],
  header: { kicker: "Categoría", h1: "Puzles", introductionParagraphs: [] },
  buyingCriteria: [],
  products: [],
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

describe("getCollectionPage", () => {
  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("returns the collection and uses ISR", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(minimalResponse), { status: 200 }),
    );

    const page = await getCollectionPage("puzles");

    expect(page.slug).toBe("puzles");
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining("/api/collection-pages/puzles"),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("throws a specific error for a missing collection", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 404 }));

    await expect(getCollectionPage("desconocida")).rejects.toBeInstanceOf(
      CollectionPageNotFoundError,
    );
  });
});
