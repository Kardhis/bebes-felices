import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { CollectionPageView } from "@/components/collection/CollectionPageView";
import {
  collectionsForPrefix,
  isCollectionSlugForPrefix,
} from "@/lib/collection/collectionRoutes";
import {
  CollectionPageNotFoundError,
  getCollectionPage,
} from "@/lib/collection/getCollectionPage";
import { loadOrNotFound } from "@/lib/editorial/loadOrNotFound";
import { buildPageMetadata } from "@/lib/seo/metadata";

type CollectionPrefixPageProps = {
  prefix: string;
  slug: string;
};

export function collectionStaticParams(prefix: string) {
  return collectionsForPrefix(prefix).map((categorySlug) => ({ categorySlug }));
}

export async function collectionMetadata(
  prefix: string,
  slug: string,
): Promise<Metadata> {
  if (!isCollectionSlugForPrefix(prefix, slug)) {
    return { title: "Página no encontrada | BebesFelices" };
  }

  try {
    const page = await getCollectionPage(slug);
    return buildPageMetadata(page.seo);
  } catch (error) {
    if (error instanceof CollectionPageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

export async function CollectionPrefixPage({
  prefix,
  slug,
}: CollectionPrefixPageProps) {
  if (!isCollectionSlugForPrefix(prefix, slug)) {
    notFound();
  }

  const page = await loadOrNotFound(
    () => getCollectionPage(slug),
    (error) => error instanceof CollectionPageNotFoundError,
  );
  return <CollectionPageView page={page} />;
}
