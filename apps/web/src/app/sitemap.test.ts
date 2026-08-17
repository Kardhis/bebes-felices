import { afterEach, describe, expect, it, vi } from "vitest";
import { LEGAL_PAGES } from "@/lib/legal/legalPages";
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
});
