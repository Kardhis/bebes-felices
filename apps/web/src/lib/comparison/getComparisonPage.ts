export type PageStatus = "DRAFT" | "PUBLISHED";

export type ComparisonPageResponse = {
  seo: {
    canonicalUrl: string;
    title: string;
    metaDescription: string;
  };
  status: PageStatus;
  slug: string;
  targetAge: number;
  breadcrumbs: Array<{ label: string; href: string }>;
  header: {
    h1: string;
    subtitle: string;
    introductionParagraphs: string[];
  };
  quickNavigation: Array<{ label: string; anchor: string }>;
  quickSummary: Array<{
    label: string;
    productId: string;
    reason: string;
  }>;
  methodology: {
    introduction: string;
    criteria: Array<{ name: string; description: string }>;
  };
  entries: Array<{
    rank: number;
    productId: string;
    title: string;
    bestFor: string;
    editorialSummary: string;
    pros: string[];
    cons: string[];
    ageRange: string;
    criteriaNotes: Array<{ criterion: string; note: string }>;
    affiliateHref: string | null;
  }>;
  buyingGuide: {
    sections: Array<{ title: string; paragraphs: string[] }>;
  };
  faq: Array<{ question: string; answer: string }>;
  relatedLinks: Array<{ title: string; href: string; description: string }>;
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
  publishedAt: string | null;
  updatedAt: string;
};

const defaultApiBaseUrl = "http://localhost:8080";

export class ComparisonPageNotFoundError extends Error {
  constructor(slug: string) {
    super(`Comparison page not found: ${slug}`);
    this.name = "ComparisonPageNotFoundError";
  }
}

export async function getComparisonPage(
  slug: string,
): Promise<ComparisonPageResponse> {
  const apiBaseUrl =
    process.env.NEXT_PUBLIC_API_BASE_URL ?? defaultApiBaseUrl;
  const res = await fetch(`${apiBaseUrl}/api/comparison-pages/${slug}`, {
    next: { revalidate: 60 },
  });

  if (res.status === 404) {
    throw new ComparisonPageNotFoundError(slug);
  }

  if (!res.ok) {
    throw new Error(
      `Failed to fetch /api/comparison-pages/${slug}: ${res.status}`,
    );
  }

  return (await res.json()) as ComparisonPageResponse;
}
