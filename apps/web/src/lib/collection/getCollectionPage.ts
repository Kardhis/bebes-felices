import {
  fetchEditorialJson,
  type EditorialChrome,
} from "@/lib/editorial/types";

export type CollectionProduct = {
  title: string;
  category: string;
  reason: string;
  ageRange: string;
  href: string;
  affiliateHref: string | null;
  ctaLabel: string;
};

export type CollectionPageResponse = EditorialChrome & {
  slug: string;
  canonicalPath: string;
  buyingCriteria: string[];
  products: CollectionProduct[];
};

export class CollectionPageNotFoundError extends Error {
  constructor(slug: string) {
    super(`Collection page not found: ${slug}`);
    this.name = "CollectionPageNotFoundError";
  }
}

export async function getCollectionPage(
  slug: string,
): Promise<CollectionPageResponse> {
  return fetchEditorialJson<CollectionPageResponse>(
    `/api/collection-pages/${slug}`,
    new CollectionPageNotFoundError(slug),
  );
}
