import { describe, expect, it } from "vitest";
import {
  ARTICLE_SLUGS,
  isArticleSlug,
  isGuideSlug,
} from "./articleSlugs";

describe("articleSlugs", () => {
  it("accepts the published guides and methodology", () => {
    expect(ARTICLE_SLUGS).toEqual([
      "como-elegir-juguetes-por-edad",
      "habilidades-3-anos",
      "habilidades-4-anos",
      "habilidades-5-anos",
      "como-analizamos",
    ]);
    expect(isGuideSlug("habilidades-3-anos")).toBe(true);
    expect(isGuideSlug("habilidades-4-anos")).toBe(true);
    expect(isGuideSlug("habilidades-5-anos")).toBe(true);
    expect(isArticleSlug("como-analizamos")).toBe(true);
  });

  it("rejects unknown articles", () => {
    expect(isGuideSlug("como-analizamos")).toBe(false);
    expect(isArticleSlug("aviso-legal")).toBe(false);
  });
});
