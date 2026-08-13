export const COMPARISON_SLUGS = [
  "mejores-bicicletas-sin-pedales-3-anos",
] as const;

export type ComparisonSlug = (typeof COMPARISON_SLUGS)[number];

export function isComparisonSlug(slug: string): slug is ComparisonSlug {
  return COMPARISON_SLUGS.includes(slug as ComparisonSlug);
}
