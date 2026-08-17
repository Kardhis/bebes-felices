import type { Metadata } from "next";

type PageSeo = {
  title: string;
  metaDescription: string;
  canonicalUrl: string;
  imageUrl?: string;
};

export function buildPageMetadata(seo: PageSeo): Metadata {
  const imageUrl = seo.imageUrl
    ? new URL(seo.imageUrl, seo.canonicalUrl).toString()
    : new URL("/images/og-home.jpg", seo.canonicalUrl).toString();

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
      images: [{ url: imageUrl, alt: "Bebes Felices" }],
    },
    twitter: {
      card: "summary_large_image",
      title: seo.title,
      description: seo.metaDescription,
      images: [imageUrl],
    },
  };
}

export const SITE_URL =
  process.env.NEXT_PUBLIC_SITE_URL ?? "https://bebesfelices.es";
