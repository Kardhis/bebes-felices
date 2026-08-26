export const CATEGORY_PAGE_SLUGS = [
  "juguetes-educativos",
  "movimiento",
  "autonomia",
  "regalos",
  "sostenibles",
] as const;

export type CategoryPageSlug = (typeof CATEGORY_PAGE_SLUGS)[number];

export function isCategoryPageSlug(slug: string): slug is CategoryPageSlug {
  return CATEGORY_PAGE_SLUGS.includes(slug as CategoryPageSlug);
}

/**
 * Prefijos de categoría alineados con {@link ../collection/collectionRoutes.ts}.
 */
export const CATEGORY_PREFIXES = CATEGORY_PAGE_SLUGS;

export function categoryPath(slug: CategoryPageSlug): string {
  return `/${slug}/`;
}
