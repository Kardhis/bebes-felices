import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";
import { parseCategoryAge } from "@/lib/category/categoryAge";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("movimiento");
}

type Props = {
  searchParams: Promise<{ edad?: string }>;
};

export default async function MovementCategoryPage({ searchParams }: Props) {
  const { edad } = await searchParams;
  return <CategoryPage slug="movimiento" initialAge={parseCategoryAge(edad)} />;
}
