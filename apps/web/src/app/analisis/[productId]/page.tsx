import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { ProductAnalysisPageView } from "@/components/analysis/ProductAnalysisPageView";
import {
  ANALYSIS_PRODUCT_IDS,
  isAnalysisProductId,
} from "@/lib/analysis/analysisProductIds";
import {
  getProductPage,
  ProductPageNotFoundError,
} from "@/lib/analysis/getProductPage";
import { loadOrNotFound } from "@/lib/editorial/loadOrNotFound";
import { buildPageMetadata } from "@/lib/seo/metadata";

type Props = {
  params: Promise<{ productId: string }>;
};

export function generateStaticParams() {
  return ANALYSIS_PRODUCT_IDS.map((productId) => ({ productId }));
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { productId } = await params;
  if (!isAnalysisProductId(productId)) {
    return { title: "Página no encontrada | BebesFelices" };
  }

  try {
    const page = await getProductPage(productId);
    return buildPageMetadata(page.seo);
  } catch (error) {
    if (error instanceof ProductPageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

export default async function ProductAnalysisPage({ params }: Props) {
  const { productId } = await params;
  if (!isAnalysisProductId(productId)) {
    notFound();
  }

  const page = await loadOrNotFound(
    () => getProductPage(productId),
    (error) => error instanceof ProductPageNotFoundError,
  );
  return <ProductAnalysisPageView page={page} />;
}
