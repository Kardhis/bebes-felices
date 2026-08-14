export const COLLECTION_PAGES = [
  {
    slug: "juegos-montessori",
    prefix: "juguetes-educativos",
  },
  {
    slug: "puzles",
    prefix: "juguetes-educativos",
  },
  {
    slug: "juegos-stem",
    prefix: "juguetes-educativos",
  },
  {
    slug: "patinetes",
    prefix: "movimiento",
  },
  {
    slug: "bicicletas-sin-pedales",
    prefix: "movimiento",
  },
  {
    slug: "torres-de-aprendizaje",
    prefix: "autonomia",
  },
  {
    slug: "vajilla-infantil",
    prefix: "autonomia",
  },
  {
    slug: "ideas-regalo-3-anos",
    prefix: "regalos",
  },
  {
    slug: "ideas-regalo-4-anos",
    prefix: "regalos",
  },
  {
    slug: "sostenibles",
    prefix: null,
  },
] as const;

export type CollectionSlug = (typeof COLLECTION_PAGES)[number]["slug"];

export function collectionsForPrefix(prefix: string): CollectionSlug[] {
  return COLLECTION_PAGES.filter((page) => page.prefix === prefix).map(
    (page) => page.slug,
  );
}

export function isCollectionSlugForPrefix(
  prefix: string,
  slug: string,
): slug is CollectionSlug {
  return collectionsForPrefix(prefix).includes(slug as CollectionSlug);
}

export function isCollectionSlug(slug: string): slug is CollectionSlug {
  return COLLECTION_PAGES.some((page) => page.slug === slug);
}
