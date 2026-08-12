import type { Metadata } from "next";
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
  buildWebSiteSchema,
  JsonLd,
} from "@/lib/seo/jsonLd";
import { buildPageMetadata } from "@/lib/seo/metadata";

export async function generateMetadata(): Promise<Metadata> {
  const home = await getHome();
  return buildPageMetadata(home.seo);
}

export default async function Home() {
  const home = await getHome();

  return (
    <>
      <SiteHeader />
      <main>
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
          affiliationShortNotice={home.affiliation.shortNoticeText}
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
      
      <SiteFooter legalLinks={home.legalLinks} updatedAt={home.updatedAt} />

      <JsonLd data={buildOrganizationSchema(home.seo.canonicalUrl)} />
      <JsonLd data={buildWebSiteSchema(home.seo.canonicalUrl)} />
    </>
  );
}
