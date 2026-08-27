export const COMPARISON_SLUGS = [
  "mejores-bicicletas-sin-pedales-3-anos",
  "mejores-juegos-montessori-3-anos",
  "mejores-puzles-3-anos",
  "mejores-patinetes-3-anos",
  "mejores-torres-aprendizaje-3-anos",
  "mejores-vajillas-infantiles-3-anos",
  "mejores-ideas-regalo-3-anos",
  "mejores-regalos-sostenibles-3-anos",
  "mejores-regalos-duraderos-3-anos",
  "mejores-manualidades-materiales-naturales-3-anos",
  "mejores-juegos-montessori-madera-3-anos",
  "mejores-juegos-simbolicos-3-anos",
  "mejores-juguetes-sensoriales-3-anos",
  "mejores-bicicletas-equilibrio-3-anos",
  "mejores-patinetes-triciclos-3-anos",
  "mejores-torres-cocina-3-anos",
  "mejores-vajillas-mesa-diaria-3-anos",
  "mejores-seleccion-regalos-3-anos",
  "mejores-elegir-regalo-edad-3-anos",
  "mejores-juegos-de-mesa-4-anos",
  "mejores-patinetes-4-anos",
  "mejores-torres-aprendizaje-4-anos",
  "mejores-vajillas-infantiles-4-anos",
  "mejores-regalos-sostenibles-4-anos",
  "mejores-juguetes-stem-5-anos",
] as const;

export type ComparisonSlug = (typeof COMPARISON_SLUGS)[number];

export function isComparisonSlug(slug: string): slug is ComparisonSlug {
  return COMPARISON_SLUGS.includes(slug as ComparisonSlug);
}
