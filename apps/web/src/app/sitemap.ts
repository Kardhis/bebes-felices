import type { MetadataRoute } from "next";
import { AGE_SLUGS } from "@/lib/age/ageSlugs";
import { SITE_URL } from "@/lib/seo/metadata";

export default function sitemap(): MetadataRoute.Sitemap {
  const lastModified = new Date();

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
  ];
}
