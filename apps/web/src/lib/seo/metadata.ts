import type { Metadata } from "next";

type PageSeo = {
  title: string;
  metaDescription: string;
  canonicalUrl: string;
};

export function buildPageMetadata(seo: PageSeo): Metadata {
  return {
    title: seo.title,
    description: seo.metaDescription,
    alternates: {
      canonical: seo.canonicalUrl,
    },
  };
}

export const SITE_URL =
  process.env.NEXT_PUBLIC_SITE_URL ?? "https://bebesfelices.es";
