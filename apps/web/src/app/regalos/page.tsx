import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";
import { parseCategoryAge } from "@/lib/category/categoryAge";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("regalos");
}

type Props = {
  searchParams: Promise<{ edad?: string }>;
};

export default async function GiftsCategoryPage({ searchParams }: Props) {
  const { edad } = await searchParams;
  return <CategoryPage slug="regalos" initialAge={parseCategoryAge(edad)} />;
}
