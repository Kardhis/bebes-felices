export function JsonLd({ data }: { data: unknown }) {
  return (
    <script
      type="application/ld+json"
      // Solo datos visibles; sin ratings/reviews inventados.
      dangerouslySetInnerHTML={{ __html: JSON.stringify(data) }}
    />
  );
}

export function buildOrganizationSchema(url: string) {
  return {
    "@context": "https://schema.org",
    "@type": "Organization",
    name: "BebesFelices",
    url,
  };
}

export function buildWebSiteSchema(url: string) {
  return {
    "@context": "https://schema.org",
    "@type": "WebSite",
    name: "BebesFelices",
    url,
  };
}

type WebPageInput = {
  url: string;
  name: string;
  description: string;
  dateModified: string;
};

export function buildWebPageSchema({
  url,
  name,
  description,
  dateModified,
}: WebPageInput) {
  return {
    "@context": "https://schema.org",
    "@type": "WebPage",
    url,
    name,
    description,
    dateModified,
    inLanguage: "es-ES",
  };
}

type BreadcrumbItem = {
  label: string;
  href: string;
};

export function buildBreadcrumbListSchema(items: BreadcrumbItem[], siteUrl: string) {
  return {
    "@context": "https://schema.org",
    "@type": "BreadcrumbList",
    itemListElement: items.map((item, index) => ({
      "@type": "ListItem",
      position: index + 1,
      name: item.label,
      item: absoluteUrl(item.href, siteUrl),
    })),
  };
}

type CollectionPageInput = {
  url: string;
  name: string;
  description: string;
  dateModified: string;
};

export function buildCollectionPageSchema({
  url,
  name,
  description,
  dateModified,
}: CollectionPageInput) {
  return {
    "@context": "https://schema.org",
    "@type": "CollectionPage",
    url,
    name,
    description,
    dateModified,
  };
}

type ItemListEntry = {
  title: string;
  href: string;
};

/**
 * Genera un ItemList a partir de URLs propias (guías, comparativas,
 * selección destacada...). No incluye precio, disponibilidad ni
 * valoraciones: eso requeriría datos reales y autorizados de producto.
 */
export function buildItemListSchema(items: ItemListEntry[], siteUrl: string) {
  return {
    "@context": "https://schema.org",
    "@type": "ItemList",
    itemListElement: items.map((item, index) => ({
      "@type": "ListItem",
      position: index + 1,
      name: item.title,
      url: absoluteUrl(item.href, siteUrl),
    })),
  };
}

type ArticleInput = {
  url: string;
  name: string;
  description: string;
  datePublished: string;
  dateModified: string;
};

export function buildArticleSchema({
  url,
  name,
  description,
  datePublished,
  dateModified,
}: ArticleInput) {
  return {
    "@context": "https://schema.org",
    "@type": "Article",
    headline: name,
    description,
    url,
    datePublished,
    dateModified,
    inLanguage: "es-ES",
    publisher: {
      "@type": "Organization",
      name: "BebesFelices",
    },
  };
}

type FaqEntry = {
  question: string;
  answer: string;
};

export function buildFaqPageSchema(items: FaqEntry[]) {
  return {
    "@context": "https://schema.org",
    "@type": "FAQPage",
    mainEntity: items.map((item) => ({
      "@type": "Question",
      name: item.question,
      acceptedAnswer: {
        "@type": "Answer",
        text: item.answer,
      },
    })),
  };
}

function absoluteUrl(href: string, siteUrl: string) {
  return href.startsWith("http") ? href : `${siteUrl}${href}`;
}
