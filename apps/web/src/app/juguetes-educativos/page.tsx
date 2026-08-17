import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("juguetes-educativos");
}

export default function EducationalToysCategoryPage() {
  return <CategoryPage slug="juguetes-educativos" />;
}
