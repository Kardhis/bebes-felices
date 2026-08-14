export type PageStatus = "DRAFT" | "PUBLISHED";

export type EditorialSeo = {
  canonicalUrl: string;
  title: string;
  metaDescription: string;
};

export type EditorialBreadcrumb = {
  label: string;
  href: string;
};

export type EditorialHeader = {
  kicker: string;
  h1: string;
  introductionParagraphs: string[];
};

export type EditorialFaq = {
  question: string;
  answer: string;
};

export type EditorialLink = {
  title: string;
  href: string;
  description: string;
};

export type EditorialChrome = {
  seo: EditorialSeo;
  status: PageStatus;
  breadcrumbs: EditorialBreadcrumb[];
  header: EditorialHeader;
  faq: EditorialFaq[];
  relatedLinks: EditorialLink[];
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

export const defaultApiBaseUrl = "http://localhost:8080";

export async function fetchEditorialJson<T>(path: string, notFoundError: Error): Promise<T> {
  const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? defaultApiBaseUrl;
  const res = await fetch(`${apiBaseUrl}${path}`, {
    next: { revalidate: 60 },
  });

  if (res.status === 404) {
    throw notFoundError;
  }

  if (!res.ok) {
    throw new Error(`Failed to fetch ${path}: ${res.status}`);
  }

  return (await res.json()) as T;
}
