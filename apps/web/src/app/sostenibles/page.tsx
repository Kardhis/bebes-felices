import type { Metadata } from "next";
import { CollectionPageView } from "@/components/collection/CollectionPageView";
import {
  CollectionPageNotFoundError,
  getCollectionPage,
} from "@/lib/collection/getCollectionPage";
import { loadOrNotFound } from "@/lib/editorial/loadOrNotFound";
import { buildPageMetadata } from "@/lib/seo/metadata";

const SLUG = "sostenibles";

export async function generateMetadata(): Promise<Metadata> {
  try {
    const page = await getCollectionPage(SLUG);
    return buildPageMetadata(page.seo);
  } catch (error) {
    if (error instanceof CollectionPageNotFoundError) {
      return { title: "Página no encontrada | BebesFelices" };
    }
    throw error;
  }
}

export default async function SustainablePage() {
  const page = await loadOrNotFound(
    () => getCollectionPage(SLUG),
    (error) => error instanceof CollectionPageNotFoundError,
  );
  return <CollectionPageView page={page} />;
}
