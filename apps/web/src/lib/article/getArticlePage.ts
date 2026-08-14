import {
  fetchEditorialJson,
  type EditorialChrome,
} from "@/lib/editorial/types";

export type ArticleSection = {
  id: string;
  title: string;
  paragraphs: string[];
};

export type ArticlePageResponse = EditorialChrome & {
  slug: string;
  canonicalPath: string;
  sections: ArticleSection[];
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
