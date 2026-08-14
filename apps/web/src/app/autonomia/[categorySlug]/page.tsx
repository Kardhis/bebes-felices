import type { Metadata } from "next";
import {
  CollectionPrefixPage,
  collectionMetadata,
  collectionStaticParams,
} from "@/lib/collection/CollectionPrefixPage";

const PREFIX = "autonomia";

type Props = {
  params: Promise<{ categorySlug: string }>;
};

export function generateStaticParams() {
  return collectionStaticParams(PREFIX);
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { categorySlug } = await params;
  return collectionMetadata(PREFIX, categorySlug);
}

export default async function AutonomyCategoryPage({ params }: Props) {
  const { categorySlug } = await params;
  return <CollectionPrefixPage prefix={PREFIX} slug={categorySlug} />;
}
