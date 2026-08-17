import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import {
  CategoryPageNotFoundError,
  getCategoryPage,
} from "./getCategoryPage";

const minimalResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/regalos/",
    title: "Ideas de regalo por edad | Bebes Felices",
    metaDescription: "Selecciones por edad y ocasión.",
  },
  status: "PUBLISHED",
  slug: "regalos",
  canonicalPath: "/regalos/",
  breadcrumbs: [
    { label: "Inicio", href: "/" },
    { label: "Regalos", href: "/regalos/" },
  ],
  header: {
    kicker: "Regalos",
    h1: "Ideas de regalo por edad para niños de 3 a 5 años",
    introductionParagraphs: ["Intro"],
  },
  childCollections: [
    {
      title: "Ideas de regalo para niños de 3 años",
      href: "/regalos/ideas-regalo-3-anos/",
      description: "Selección por ocasión.",
    },
  ],
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

describe("getCategoryPage", () => {
  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("returns the category page and uses ISR", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(minimalResponse), { status: 200 }),
    );

    const page = await getCategoryPage("regalos");

    expect(page.slug).toBe("regalos");
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining("/api/category-pages/regalos"),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("throws a specific error for a missing category", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 404 }));

    await expect(getCategoryPage("desconocida")).rejects.toBeInstanceOf(
      CategoryPageNotFoundError,
    );
  });
});
