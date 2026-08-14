import type { Metadata } from "next";
import {
  CollectionPrefixPage,
  collectionMetadata,
  collectionStaticParams,
} from "@/lib/collection/CollectionPrefixPage";

const PREFIX = "regalos";

type Props = {
  params: Promise<{ giftSlug: string }>;
};

export function generateStaticParams() {
  return collectionStaticParams(PREFIX).map(({ categorySlug }) => ({
    giftSlug: categorySlug,
  }));
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { giftSlug } = await params;
  return collectionMetadata(PREFIX, giftSlug);
}

export default async function GiftPage({ params }: Props) {
  const { giftSlug } = await params;
  return <CollectionPrefixPage prefix={PREFIX} slug={giftSlug} />;
}
