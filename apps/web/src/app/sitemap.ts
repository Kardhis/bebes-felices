import type { MetadataRoute } from "next";
import { AGE_SLUGS } from "@/lib/age/ageSlugs";
import { COMPARISON_SLUGS } from "@/lib/comparison/comparisonSlugs";
import { getComparisonPage } from "@/lib/comparison/getComparisonPage";
import { SITE_URL } from "@/lib/seo/metadata";

export default async function sitemap(): Promise<MetadataRoute.Sitemap> {
  const lastModified = new Date();
  const comparisons = await Promise.all(
    COMPARISON_SLUGS.map(async (slug) => {
      try {
        const page = await getComparisonPage(slug);
        if (page.status !== "PUBLISHED") {
          return null;
        }
        return {
          url: `${SITE_URL}/comparativas/${slug}/`,
          lastModified: new Date(page.updatedAt),
          changeFrequency: "weekly" as const,
          priority: 0.85,
        };
      } catch {
        return null;
      }
    }),
  );

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
    ...comparisons.filter(
      (entry): entry is NonNullable<typeof entry> => entry !== null,
    ),
  ];
}
