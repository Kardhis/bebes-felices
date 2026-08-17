import type { Metadata } from "next";
import { InfoPageView } from "@/components/info/InfoPageView";
import { getInfoPage } from "@/lib/info/infoPages";
import { buildPageMetadata } from "@/lib/seo/metadata";

const page = getInfoPage("quienes-somos");

export const metadata: Metadata = buildPageMetadata({
  title: page.title,
  metaDescription: page.metaDescription,
  canonicalUrl: page.canonicalUrl,
});

export default function AboutPage() {
  return <InfoPageView page={page} />;
}
