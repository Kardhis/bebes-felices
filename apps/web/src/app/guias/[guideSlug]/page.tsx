import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { ArticlePageView } from "@/components/article/ArticlePageView";
import { GUIDE_SLUGS, isGuideSlug } from "@/lib/article/articleSlugs";
import {
  ArticlePageNotFoundError,
  getArticlePage,
} from "@/lib/article/getArticlePage";
import { loadOrNotFound } from "@/lib/editorial/loadOrNotFound";
import { buildPageMetadata } from "@/lib/seo/metadata";

type Props = {
  params: Promise<{ guideSlug: string }>;
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

export default async function GuidePage({ params }: Props) {
  const { guideSlug } = await params;
  if (!isGuideSlug(guideSlug)) {
    notFound();
  }

  const page = await loadOrNotFound(
    () => getArticlePage(guideSlug),
    (error) => error instanceof ArticlePageNotFoundError,
  );
  return <ArticlePageView page={page} />;
}
