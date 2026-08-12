export type LinkItem = {
  title: string;
  href: string;
  description: string;
};

export type AgeLink = {
  ageLabel: string;
  href: string;
};

export type FeaturedProduct = {
  title: string;
  category: string;
  reason: string;
  ageRange: string;
  href: string;
  affiliateHref: string | null;
};

export type AgePageResponse = {
  seo: {
    canonicalUrl: string;
    title: string;
    metaDescription: string;
  };
  age: number;
  ageLabel: string;
  slug: string;
  breadcrumbs: Array<{ label: string; href: string }>;
  header: {
    h1: string;
    introductionParagraphs: string[];
  };
  quickNavigation: Array<{ label: string; anchor: string }>;
  quickSummary: Array<{
    need: string;
    recommendation: string;
    href: string | null;
  }>;
  optionsByNeed: Array<{
    title: string;
    anchor: string;
    items: LinkItem[];
  }>;
  featuredSelection: FeaturedProduct[];
  developmentSkills: Array<{ skill: string; description: string | null }>;
  buyingConsiderations: string[];
  featuredGuides: LinkItem[];
  featuredRankings: LinkItem[];
  giftIdeas: LinkItem[];
  informativeArticles: LinkItem[];
  faq: Array<{ question: string; answer: string }>;
  otherAges: AgeLink[];
  trustAuthority: {
    howWeSelect: string;
    analysisCriteria: string[];
    editorialTransparency: string[];
  };
  affiliation: {
    noticeText: string;
    shortNoticeText: string;
  };
  legalLinks: Array<{ label: string; href: string }>;
  author: { name: string; role: string | null } | null;
  publishedAt: string;
  updatedAt: string;
};

const defaultApiBaseUrl = "http://localhost:8080";

export class AgePageNotFoundError extends Error {
  constructor(slug: string) {
    super(`Age page not found: ${slug}`);
    this.name = "AgePageNotFoundError";
  }
}

type RawAgePageResponse = AgePageResponse & {
  header?: {
    h1?: string;
    introduction?: string;
    introductionParagraphs?: string[];
  };
};

function normalizeAgePageResponse(data: RawAgePageResponse): AgePageResponse {
  const header = data.header;
  if (!header?.h1) {
    return data as AgePageResponse;
  }

  let introductionParagraphs = header.introductionParagraphs;

  if (!Array.isArray(introductionParagraphs)) {
    const legacyIntroduction = header.introduction;
    introductionParagraphs =
      typeof legacyIntroduction === "string" && legacyIntroduction
        ? [legacyIntroduction]
        : [];
  }

  return {
    ...data,
    header: {
      h1: header.h1,
      introductionParagraphs,
    },
  } as AgePageResponse;
}

export async function getAgePage(slug: string): Promise<AgePageResponse> {
  const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? defaultApiBaseUrl;
  const res = await fetch(`${apiBaseUrl}/api/age-pages/${slug}`, {
    next: { revalidate: 60 },
  });

  if (res.status === 404) {
    throw new AgePageNotFoundError(slug);
  }

  if (!res.ok) {
    throw new Error(`Failed to fetch /api/age-pages/${slug}: ${res.status}`);
  }

  const data = (await res.json()) as RawAgePageResponse;
  return normalizeAgePageResponse(data);
}
