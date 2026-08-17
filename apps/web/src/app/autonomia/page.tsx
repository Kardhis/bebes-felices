import type { Metadata } from "next";
import { CategoryPage, categoryMetadata } from "@/lib/category/CategoryPageRoute";

export async function generateMetadata(): Promise<Metadata> {
  return categoryMetadata("autonomia");
}

export default function AutonomyCategoryPage() {
  return <CategoryPage slug="autonomia" />;
}
