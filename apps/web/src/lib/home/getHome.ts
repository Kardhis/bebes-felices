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

export class HomeFetchError extends Error {
  constructor(
    message: string,
    readonly status?: number,
    options?: ErrorOptions,
  ) {
    super(message, options);
    this.name = "HomeFetchError";
  }
}

export async function getHome(): Promise<HomeResponse> {
  const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? defaultApiBaseUrl;
  let res: Response;

  try {
    res = await fetch(`${apiBaseUrl}/api/home`, {
      next: { revalidate: 60 },
    });
  } catch (cause) {
    throw new HomeFetchError("No se pudo conectar con la API de la Home.", undefined, {
      cause,
    });
  }

  if (!res.ok) {
    throw new HomeFetchError(
      `La API de la Home respondió con estado ${res.status}.`,
      res.status,
    );
  }

  const data: unknown = await res.json();
  if (!isHomeResponse(data)) {
    throw new HomeFetchError("La API de la Home devolvió un contrato inválido.");
  }

  return data;
}

function isHomeResponse(value: unknown): value is HomeResponse {
  if (!value || typeof value !== "object") return false;
  const home = value as Partial<HomeResponse>;

  return Boolean(
    home.seo &&
      typeof home.seo.canonicalUrl === "string" &&
      typeof home.seo.title === "string" &&
      home.hero &&
      typeof home.hero.h1 === "string" &&
      typeof home.hero.imageUrl === "string" &&
      Array.isArray(home.ageNavigation) &&
      Array.isArray(home.mainCategories) &&
      Array.isArray(home.featuredGuides) &&
      Array.isArray(home.recentComparisons) &&
      home.trustAuthority &&
      home.affiliation &&
      Array.isArray(home.legalLinks) &&
      typeof home.updatedAt === "string",
  );
}
