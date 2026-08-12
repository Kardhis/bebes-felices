export type HomeResponse = {
  seo: {
    canonicalUrl: string;
    title: string;
    metaDescription: string;
  };
  hero: {
    brand: string;
    h1: string;
    valueProposition: string;
    primaryCtaLabel: string;
    primaryCtaHref: string;
    secondaryCtaLabel: string;
    secondaryCtaHref: string;
    imageUrl: string;
    imageAlt: string;
  };
  ageNavigation: Array<{
    ageLabel: string;
    href: string;
  }>;
  mainCategories: Array<{
    title: string;
    href: string;
    description: string;
  }>;
  featuredGuides: Array<{
    title: string;
    href: string;
    description: string;
  }>;
  recentComparisons: Array<{
    title: string;
    href: string;
    description: string;
  }>;
  trustAuthority: {
    howWeSelect: string;
    analysisCriteria: string[];
    editorialTransparency: string[];
  };
  affiliation: {
    noticeText: string;
    shortNoticeText: string;
  };
  legalLinks: Array<{
    label: string;
    href: string;
  }>;
  updatedAt: string;
};

const defaultApiBaseUrl = "http://localhost:8080";

export async function getHome(): Promise<HomeResponse> {
  const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? defaultApiBaseUrl;
  const res = await fetch(`${apiBaseUrl}/api/home`, {
    next: { revalidate: 60 },
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch /api/home: ${res.status}`);
  }

  return (await res.json()) as HomeResponse;
}
