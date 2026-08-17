import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { CategoryPageView } from "@/components/category/CategoryPageView";
import { isCategoryPageSlug } from "@/lib/category/categoryRoutes";
import {
  CategoryPageNotFoundError,
  getCategoryPage,
} from "@/lib/category/getCategoryPage";
import { loadOrNotFound } from "@/lib/editorial/loadOrNotFound";
import { buildPageMetadata } from "@/lib/seo/metadata";

export async function categoryMetadata(slug: string): Promise<Metadata> {
  if (!isCategoryPageSlug(slug)) {
    return { title: "Página no encontrada | BebesFelices" };
  }

  try {
    const page = await getCategoryPage(slug);
    return buildPageMetadata(page.seo);
  } catch (error) {
    if (error instanceof CategoryPageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

type CategoryPageProps = {
  slug: string;
};

export async function CategoryPage({ slug }: CategoryPageProps) {
  if (!isCategoryPageSlug(slug)) {
    notFound();
  }

  const page = await loadOrNotFound(
    () => getCategoryPage(slug),
    (error) => error instanceof CategoryPageNotFoundError,
  );
  return <CategoryPageView page={page} />;
}
