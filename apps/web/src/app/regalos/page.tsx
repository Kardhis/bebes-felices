import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("regalos");
}

export default function GiftsCategoryPage() {
  return <CategoryPage slug="regalos" />;
}
