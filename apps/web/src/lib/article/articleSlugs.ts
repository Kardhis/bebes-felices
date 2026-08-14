export const GUIDE_SLUGS = [
  "como-elegir-juguetes-por-edad",
  "habilidades-3-anos",
  "habilidades-4-anos",
] as const;

export const METHODOLOGY_SLUG = "como-analizamos";

export type GuideSlug = (typeof GUIDE_SLUGS)[number];

export function isGuideSlug(slug: string): slug is GuideSlug {
  return GUIDE_SLUGS.includes(slug as GuideSlug);
}

export const ARTICLE_SLUGS = [...GUIDE_SLUGS, METHODOLOGY_SLUG] as const;

export type ArticleSlug = (typeof ARTICLE_SLUGS)[number];

export function isArticleSlug(slug: string): slug is ArticleSlug {
  return ARTICLE_SLUGS.includes(slug as ArticleSlug);
}
