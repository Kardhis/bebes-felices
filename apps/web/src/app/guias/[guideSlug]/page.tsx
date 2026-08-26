import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { ArticlePageView } from "@/components/article/ArticlePageView";
import { GUIDE_SLUGS, isGuideSlug } from "@/lib/article/articleSlugs";
import {
  ArticlePageNotFoundError,
  getArticlePage,
} from "@/lib/article/getArticlePage";
import { parseCategoryAge } from "@/lib/category/categoryAge";
import { loadOrNotFound } from "@/lib/editorial/loadOrNotFound";
import { buildPageMetadata } from "@/lib/seo/metadata";

type Props = {
  params: Promise<{ guideSlug: string }>;
  searchParams: Promise<{ edad?: string }>;
};

export function generateStaticParams() {
  return GUIDE_SLUGS.map((guideSlug) => ({ guideSlug }));
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { guideSlug } = await params;
  if (!isGuideSlug(guideSlug)) {
    return { title: "Página no encontrada | BebesFelices" };
  }

  try {
    const page = await getArticlePage(guideSlug);
    return buildPageMetadata(page.seo);
  } catch (error) {
    if (error instanceof ArticlePageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

export default async function GuidePage({ params, searchParams }: Props) {
  const { guideSlug } = await params;
  const { edad } = await searchParams;
  if (!isGuideSlug(guideSlug)) {
    notFound();
  }

  const page = await loadOrNotFound(
    () => getArticlePage(guideSlug),
    (error) => error instanceof ArticlePageNotFoundError,
  );
  return (
    <ArticlePageView page={page} initialAge={parseCategoryAge(edad)} />
  );
}
