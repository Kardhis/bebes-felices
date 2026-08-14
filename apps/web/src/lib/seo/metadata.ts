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
    openGraph: {
      title: seo.title,
      description: seo.metaDescription,
      url: seo.canonicalUrl,
      locale: "es_ES",
      siteName: "Bebes Felices",
      type: "website",
    },
  };
}

export const SITE_URL =
  process.env.NEXT_PUBLIC_SITE_URL ?? "https://bebesfelices.es";
