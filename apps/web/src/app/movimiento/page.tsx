import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("movimiento");
}

export default function MovementCategoryPage() {
  return <CategoryPage slug="movimiento" />;
}
