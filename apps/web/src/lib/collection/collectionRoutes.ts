export const COLLECTION_PAGES = [
  {
    slug: "juegos-montessori",
    prefix: "juguetes-educativos",
    hubAge: 3,
  },
  {
    slug: "puzles",
    prefix: "juguetes-educativos",
    hubAge: 3,
  },
  {
    slug: "juegos-stem",
    prefix: "juguetes-educativos",
    hubAge: 4,
  },
  {
    slug: "juegos-de-mesa",
    prefix: "juguetes-educativos",
    hubAge: 5,
  },
  {
    slug: "juego-simbolico",
    prefix: "juguetes-educativos",
    hubAge: 3,
  },
  {
    slug: "juguetes-sensoriales",
    prefix: "juguetes-educativos",
    hubAge: 3,
  },
  {
    slug: "munecos-figuras-pequenos-mundos",
    prefix: "juguetes-educativos",
    hubAge: 3,
  },
  {
    slug: "juguetes-musicales",
    prefix: "juguetes-educativos",
    hubAge: 3,
  },
  {
    slug: "juguetes-construccion",
    prefix: "juguetes-educativos",
    hubAge: 4,
  },
  {
    slug: "arte-manualidades",
    prefix: "juguetes-educativos",
    hubAge: 4,
  },
  {
    slug: "causa-efecto-experimentacion",
    prefix: "juguetes-educativos",
    hubAge: 4,
  },
  {
    slug: "lenguaje-lectoescritura",
    prefix: "juguetes-educativos",
    hubAge: 5,
  },
  {
    slug: "matematicas-logica",
    prefix: "juguetes-educativos",
    hubAge: 5,
  },
  {
    slug: "juegos-cooperativos-socioemocionales",
    prefix: "juguetes-educativos",
    hubAge: 5,
  },
  {
    slug: "patinetes",
    prefix: "movimiento",
    hubAge: 3,
  },
  {
    slug: "bicicletas-sin-pedales",
    prefix: "movimiento",
    hubAge: 4,
  },
  {
    slug: "torres-de-aprendizaje",
    prefix: "autonomia",
    hubAge: 3,
  },
  {
    slug: "vajilla-infantil",
    prefix: "autonomia",
    hubAge: 3,
  },
  {
    slug: "ideas-regalo-3-anos",
    prefix: "regalos",
    hubAge: 3,
  },
  {
    slug: "ideas-regalo-4-anos",
    prefix: "regalos",
    hubAge: 4,
  },
  {
    slug: "ideas-regalo-5-anos",
    prefix: "regalos",
    hubAge: 5,
  },
  {
    slug: "sostenibles",
    prefix: null,
    hubAge: 3,
  },
] as const;

export type CollectionSlug = (typeof COLLECTION_PAGES)[number]["slug"];
export type CollectionHubAge = (typeof COLLECTION_PAGES)[number]["hubAge"];

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
