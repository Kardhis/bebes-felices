import type { Metadata } from "next";
import { LegalPageView } from "@/components/legal/LegalPageView";
import { getLegalPage } from "@/lib/legal/legalPages";
import { buildPageMetadata } from "@/lib/seo/metadata";

const page = getLegalPage("politica-privacidad");

export const metadata: Metadata = buildPageMetadata({
  title: page.title,
  metaDescription: page.metaDescription,
  canonicalUrl: page.canonicalUrl,
});

export default function PrivacyPolicyPage() {
  return <LegalPageView page={page} />;
}
