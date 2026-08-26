import { afterEach, describe, expect, it, vi } from "vitest";
import { CATEGORY_PAGE_SLUGS, categoryPath } from "@/lib/category/categoryRoutes";
import { getInfoPage, INFO_PAGE_SLUGS } from "@/lib/info/infoPages";
import { LEGAL_PAGES } from "@/lib/legal/legalPages";
import { SITE_URL } from "@/lib/seo/metadata";
import sitemap from "./sitemap";

describe("sitemap", () => {
  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("includes every legal canonical even when editorial loaders are unavailable", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 503,
      }),
    );

    const entries = await sitemap();

    for (const legalPage of LEGAL_PAGES) {
      expect(entries).toContainEqual({
        url: legalPage.canonicalUrl,
        lastModified: new Date(legalPage.updatedAt),
        changeFrequency: "yearly",
        priority: 0.2,
      });
    }
  });

  it("includes category and info routes from static registries when the API is unavailable", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 503,
      }),
    );

    const entries = await sitemap();

    for (const slug of CATEGORY_PAGE_SLUGS) {
      expect(entries).toContainEqual(
        expect.objectContaining({
          url: `${SITE_URL}${categoryPath(slug)}`,
          changeFrequency: "weekly",
          priority: 0.85,
        }),
      );
    }
    expect(entries).toContainEqual(
      expect.objectContaining({
        url: `${SITE_URL}/guias/`,
        changeFrequency: "weekly",
        priority: 0.85,
      }),
    );

    for (const slug of INFO_PAGE_SLUGS) {
      const page = getInfoPage(slug);
      expect(entries).toContainEqual({
        url: page.canonicalUrl,
        lastModified: new Date(page.updatedAt),
        changeFrequency: "yearly",
        priority: 0.3,
      });
    }
  });
});
