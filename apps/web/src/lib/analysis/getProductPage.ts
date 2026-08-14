import {
  fetchEditorialJson,
  type EditorialChrome,
} from "@/lib/editorial/types";

export type ProductAnalysisResponse = EditorialChrome & {
  productId: string;
  canonicalPath: string;
  category: string;
  ageRange: string;
  forWhom: string;
  editorialSummary: string;
  pros: string[];
  cons: string[];
  safetyNotes: string[];
  buyingChecks: string[];
  affiliateHref: string | null;
};

export class ProductPageNotFoundError extends Error {
  constructor(productId: string) {
    super(`Product page not found: ${productId}`);
    this.name = "ProductPageNotFoundError";
  }
}

export async function getProductPage(
  productId: string,
): Promise<ProductAnalysisResponse> {
  return fetchEditorialJson<ProductAnalysisResponse>(
    `/api/product-pages/${productId}`,
    new ProductPageNotFoundError(productId),
  );
}
