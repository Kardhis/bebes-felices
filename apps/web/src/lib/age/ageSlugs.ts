export const AGE_SLUGS = ["3-anos", "4-anos", "5-anos"] as const;

export type AgeSlug = (typeof AGE_SLUGS)[number];

export function isAgeSlug(value: string): value is AgeSlug {
  return (AGE_SLUGS as readonly string[]).includes(value);
}
