import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";
import { parseCategoryAge } from "@/lib/category/categoryAge";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("juguetes-educativos");
}

type Props = {
  searchParams: Promise<{ edad?: string }>;
};

export default async function EducationalToysCategoryPage({ searchParams }: Props) {
  const { edad } = await searchParams;
  return (
    <CategoryPage
      slug="juguetes-educativos"
      initialAge={parseCategoryAge(edad)}
    />
  );
}
