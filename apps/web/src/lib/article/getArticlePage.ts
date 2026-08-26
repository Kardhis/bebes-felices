import {
  fetchEditorialJson,
  type EditorialChrome,
} from "@/lib/editorial/types";

export type ArticleSection = {
  id: string;
  title: string;
  paragraphs: string[];
};

export type ArticleAgeVariant = {
  hubAge: number;
  introductionParagraphs: string[];
  sections: ArticleSection[];
  faq: EditorialChrome["faq"];
  relatedLinks: EditorialChrome["relatedLinks"];
};

export type ArticlePageResponse = EditorialChrome & {
  slug: string;
  canonicalPath: string;
  sections: ArticleSection[];
  ageVariants: ArticleAgeVariant[];
};

export class ArticlePageNotFoundError extends Error {
  constructor(slug: string) {
    super(`Article page not found: ${slug}`);
    this.name = "ArticlePageNotFoundError";
  }
}

export async function getArticlePage(slug: string): Promise<ArticlePageResponse> {
  return fetchEditorialJson<ArticlePageResponse>(
    `/api/article-pages/${slug}`,
    new ArticlePageNotFoundError(slug),
  );
}
