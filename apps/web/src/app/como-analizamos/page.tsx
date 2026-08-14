import type { Metadata } from "next";
import { ArticlePageView } from "@/components/article/ArticlePageView";
import { METHODOLOGY_SLUG } from "@/lib/article/articleSlugs";
import {
  ArticlePageNotFoundError,
  getArticlePage,
} from "@/lib/article/getArticlePage";
import { loadOrNotFound } from "@/lib/editorial/loadOrNotFound";
import { buildPageMetadata } from "@/lib/seo/metadata";

export async function generateMetadata(): Promise<Metadata> {
  try {
    const page = await getArticlePage(METHODOLOGY_SLUG);
    return buildPageMetadata(page.seo);
  } catch (error) {
    if (error instanceof ArticlePageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

export default async function MethodologyPage() {
  const page = await loadOrNotFound(
    () => getArticlePage(METHODOLOGY_SLUG),
    (error) => error instanceof ArticlePageNotFoundError,
  );
  return <ArticlePageView page={page} />;
}
