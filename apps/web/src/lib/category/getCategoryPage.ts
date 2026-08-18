import {
  fetchEditorialJson,
  type EditorialChrome,
  type EditorialLink,
} from "@/lib/editorial/types";

export type CategoryPageResponse = EditorialChrome & {
  slug: string;
  canonicalPath: string;
  childCollections: Array<EditorialLink & { hubAge: 3 | 4 | 5 }>;
};

export class CategoryPageNotFoundError extends Error {
  constructor(slug: string) {
    super(`Category page not found: ${slug}`);
    this.name = "CategoryPageNotFoundError";
  }
}

export async function getCategoryPage(slug: string): Promise<CategoryPageResponse> {
  return fetchEditorialJson<CategoryPageResponse>(
    `/api/category-pages/${slug}`,
    new CategoryPageNotFoundError(slug),
  );
}
