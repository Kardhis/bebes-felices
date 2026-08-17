import type { MetadataRoute } from "next";
import { AGE_SLUGS } from "@/lib/age/ageSlugs";
import { ANALYSIS_PRODUCT_IDS } from "@/lib/analysis/analysisProductIds";
import { getProductPage } from "@/lib/analysis/getProductPage";
import { ARTICLE_SLUGS } from "@/lib/article/articleSlugs";
import { getArticlePage } from "@/lib/article/getArticlePage";
import {
  CATEGORY_PAGE_SLUGS,
  categoryPath,
} from "@/lib/category/categoryRoutes";
import { COLLECTION_PAGES } from "@/lib/collection/collectionRoutes";
import { getCollectionPage } from "@/lib/collection/getCollectionPage";
import { COMPARISON_SLUGS } from "@/lib/comparison/comparisonSlugs";
import { getComparisonPage } from "@/lib/comparison/getComparisonPage";
import { getInfoPage, INFO_PAGE_SLUGS } from "@/lib/info/infoPages";
import { LEGAL_PAGES } from "@/lib/legal/legalPages";
import { SITE_URL } from "@/lib/seo/metadata";

type SitemapEntry = MetadataRoute.Sitemap[number];

async function publishedCanonical(
  loader: () => Promise<{ status: string; seo: { canonicalUrl: string }; updatedAt: string }>,
  priority: number,
): Promise<SitemapEntry | null> {
  try {
    const page = await loader();
    if (page.status !== "PUBLISHED") {
      return null;
    }
    return {
      url: page.seo.canonicalUrl,
      lastModified: new Date(page.updatedAt),
      changeFrequency: "weekly",
      priority,
    };
  } catch {
    return null;
  }
}

export default async function sitemap(): Promise<MetadataRoute.Sitemap> {
  const lastModified = new Date();
  const editorial = await Promise.all([
    ...COMPARISON_SLUGS.map((slug) =>
      publishedCanonical(() => getComparisonPage(slug), 0.85),
    ),
    ...ARTICLE_SLUGS.map((slug) =>
      publishedCanonical(() => getArticlePage(slug), 0.8),
    ),
    ...COLLECTION_PAGES.map((page) =>
      publishedCanonical(() => getCollectionPage(page.slug), 0.75),
    ),
    ...ANALYSIS_PRODUCT_IDS.map((productId) =>
      publishedCanonical(() => getProductPage(productId), 0.7),
    ),
  ]);

  return [
    {
      url: `${SITE_URL}/`,
      lastModified,
      changeFrequency: "weekly",
      priority: 1,
    },
    ...AGE_SLUGS.map((slug) => ({
      url: `${SITE_URL}/por-edad/${slug}/`,
      lastModified,
      changeFrequency: "weekly" as const,
      priority: 0.9,
    })),
    ...CATEGORY_PAGE_SLUGS.map((slug) => ({
      url: `${SITE_URL}${categoryPath(slug)}`,
      lastModified,
      changeFrequency: "weekly" as const,
      priority: 0.85,
    })),
    ...INFO_PAGE_SLUGS.map((slug) => {
      const page = getInfoPage(slug);
      return {
        url: page.canonicalUrl,
        lastModified: new Date(page.updatedAt),
        changeFrequency: "yearly" as const,
        priority: 0.3,
      };
    }),
    ...LEGAL_PAGES.map((page) => ({
      url: page.canonicalUrl,
      lastModified: new Date(page.updatedAt),
      changeFrequency: "yearly" as const,
      priority: 0.2,
    })),
    ...editorial.filter((entry): entry is SitemapEntry => entry !== null),
  ];
}
