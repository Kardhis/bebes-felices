export const CATEGORY_AGES = [3, 4, 5] as const;

export type CategoryAge = (typeof CATEGORY_AGES)[number];

export function parseCategoryAge(value: string | undefined | null): CategoryAge {
  if (value === "4" || value === "5") {
    return Number(value) as CategoryAge;
  }
  return 3;
}

export function categoryAgeHref(pathname: string, age: CategoryAge): string {
  const normalized = pathname.endsWith("/") ? pathname : `${pathname}/`;
  return `${normalized}?edad=${age}`;
}
