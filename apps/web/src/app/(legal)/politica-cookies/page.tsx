import type { Metadata } from "next";
import { LegalPageView } from "@/components/legal/LegalPageView";
import { getLegalPage } from "@/lib/legal/legalPages";
import { buildPageMetadata } from "@/lib/seo/metadata";

const page = getLegalPage("politica-cookies");

export const metadata: Metadata = buildPageMetadata({
  title: page.title,
  metaDescription: page.metaDescription,
  canonicalUrl: page.canonicalUrl,
});

export default function CookiesPolicyPage() {
  return <LegalPageView page={page} />;
}
