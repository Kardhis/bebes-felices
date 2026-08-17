import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import { getHome, HomeFetchError, type HomeResponse } from "./getHome";

const homeResponse: HomeResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/",
    title: "Bebes Felices",
    metaDescription: "Descripción",
  },
  hero: {
    brand: "Bebes Felices",
    h1: "Productos para niños",
    valueProposition: "Elegir mejor",
    primaryCtaLabel: "Por edad",
    primaryCtaHref: "/#por-edad",
    secondaryCtaLabel: "Categorías",
    secondaryCtaHref: "/#categorias",
    imageUrl: "/images/home-hero.jpg",
    imageAlt: "Juguetes",
  },
  ageNavigation: [],
  mainCategories: [],
  featuredGuides: [],
  recentComparisons: [],
  trustAuthority: {
    howWeSelect: "Con criterios",
    analysisCriteria: [],
    editorialTransparency: [],
  },
  affiliation: { noticeText: "Aviso", shortNoticeText: "Aviso breve" },
  legalLinks: [],
  updatedAt: "2026-08-17",
};

describe("getHome", () => {
  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("returns a valid response using ISR", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(homeResponse), { status: 200 }),
    );

    await expect(getHome()).resolves.toEqual(homeResponse);
    expect(fetch).toHaveBeenCalledWith(
      expect.stringContaining("/api/home"),
      expect.objectContaining({ next: { revalidate: 60 } }),
    );
  });

  it("reports HTTP errors with their status", async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 503 }));

    await expect(getHome()).rejects.toMatchObject({
      name: "HomeFetchError",
      status: 503,
    });
  });

  it("normalizes network failures", async () => {
    vi.mocked(fetch).mockRejectedValue(new TypeError("connection refused"));

    await expect(getHome()).rejects.toBeInstanceOf(HomeFetchError);
  });

  it("rejects an invalid API contract", async () => {
    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify({ seo: {} }), { status: 200 }),
    );

    await expect(getHome()).rejects.toThrow("contrato inválido");
  });
});
