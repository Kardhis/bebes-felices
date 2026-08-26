import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";
import { parseCategoryAge } from "@/lib/category/categoryAge";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("sostenibles");
}

type Props = {
  searchParams: Promise<{ edad?: string }>;
};

export default async function SustainableCategoryPage({ searchParams }: Props) {
  const { edad } = await searchParams;
  return (
    <CategoryPage slug="sostenibles" initialAge={parseCategoryAge(edad)} />
  );
}
