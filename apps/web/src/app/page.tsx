import type { Metadata } from "next";
import { cache } from "react";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import { AgeNavigation } from "@/components/home/AgeNavigation";
import { FeaturedGuides } from "@/components/home/FeaturedGuides";
import { HomeHero } from "@/components/home/HomeHero";
import { MainCategories } from "@/components/home/MainCategories";
import { RecentComparisons } from "@/components/home/RecentComparisons";
import { SiteFooter } from "@/components/home/SiteFooter";
import { SiteHeader } from "@/components/home/SiteHeader";
import { TrustAuthority } from "@/components/home/TrustAuthority";
import { getHome } from "@/lib/home/getHome";
import {
  buildOrganizationSchema,
  buildItemListSchema,
  buildWebPageSchema,
  buildWebSiteSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { buildPageMetadata } from "@/lib/seo/metadata";

const loadHome = cache(getHome);

export async function generateMetadata(): Promise<Metadata> {
  try {
    const home = await loadHome();
    return buildPageMetadata({ ...home.seo, imageUrl: "/images/og-home.jpg" });
  } catch {
    return buildPageMetadata({
      canonicalUrl: process.env.NEXT_PUBLIC_SITE_URL ?? "https://bebesfelices.es/",
      title: "Bebes Felices | Guías y comparativas para niños",
      metaDescription:
        "Guías, comparativas y recomendaciones por edad para niños de 3 a 5 años.",
      imageUrl: "/images/og-home.jpg",
    });
  }
}

export default async function Home() {
  const home = await loadHome();
  const siteUrl = new URL(home.seo.canonicalUrl).origin;
  const categoryNavigation = home.mainCategories.map((item) => ({
    label: item.title,
    href: item.href,
  }));
  const isSostenibles = (item: { href: string }) =>
    item.href.replace(/\/+$/, "") === "/sostenibles";
  const discoverLinks = [
    { label: "Por edad", href: "/#por-edad" },
    ...categoryNavigation.filter(isSostenibles),
    ...categoryNavigation.filter((item) => !isSostenibles(item)),
    { label: "Comparativas", href: "/#comparativas" },
    { label: "Guías", href: "/#guias" },
  ];
  const featuredContent = [...home.featuredGuides, ...home.recentComparisons];

  return (
    <>
      <SiteHeader categoryItems={categoryNavigation} />
      <main id="contenido-principal">
        <HomeHero
          brand={home.hero.brand}
          h1={home.hero.h1}
          valueProposition={home.hero.valueProposition}
          primaryCtaLabel={home.hero.primaryCtaLabel}
          primaryCtaHref={home.hero.primaryCtaHref}
          secondaryCtaLabel={home.hero.secondaryCtaLabel}
          secondaryCtaHref={home.hero.secondaryCtaHref}
          imageUrl={home.hero.imageUrl}
          imageAlt={home.hero.imageAlt}
        />

        <AgeNavigation items={home.ageNavigation} />
        <MainCategories items={home.mainCategories} />

        <section className="bg-white">
          <div className="mx-auto grid max-w-6xl gap-12 px-4 py-14 sm:px-6 md:grid-cols-2">
            <FeaturedGuides items={home.featuredGuides} />
            <RecentComparisons items={home.recentComparisons} />
          </div>
        </section>

        <TrustAuthority
          howWeSelect={home.trustAuthority.howWeSelect}
          analysisCriteria={home.trustAuthority.analysisCriteria}
          editorialTransparency={home.trustAuthority.editorialTransparency}
          updatedAt={home.updatedAt}
        />

        <AffiliationNotice noticeText={home.affiliation.noticeText} />
      </main>
      <SiteFooter
        legalLinks={home.legalLinks}
        updatedAt={home.updatedAt}
        discoverLinks={discoverLinks}
      />

      <JsonLd data={buildOrganizationSchema(home.seo.canonicalUrl)} />
      <JsonLd data={buildWebSiteSchema(home.seo.canonicalUrl)} />
      <JsonLd
        data={buildWebPageSchema({
          url: home.seo.canonicalUrl,
          name: home.seo.title,
          description: home.seo.metaDescription,
          dateModified: home.updatedAt,
        })}
      />
      <JsonLd data={buildItemListSchema(featuredContent, siteUrl)} />
    </>
  );
}
