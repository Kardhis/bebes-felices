"use client";

import Link from "next/link";
import { useEffect, useState } from "react";
import { usePathname, useRouter } from "next/navigation";
import { AgeSectionHeading } from "@/components/age/AgeSectionHeading";
import {
  CATEGORY_AGES,
  categoryAgeHref,
  type CategoryAge,
} from "@/lib/category/categoryAge";
import type { CategoryPageResponse } from "@/lib/category/getCategoryPage";

type ChildCollection = CategoryPageResponse["childCollections"][number];

type CategoryCollectionGroupsProps = {
  items: ChildCollection[];
  initialAge?: CategoryAge;
};

export function CategoryCollectionGroups({
  items,
  initialAge = 3,
}: CategoryCollectionGroupsProps) {
  const router = useRouter();
  const pathname = usePathname();
  const [selectedAge, setSelectedAge] = useState<CategoryAge>(initialAge);

  useEffect(() => {
    setSelectedAge(initialAge);
  }, [initialAge]);

  if (items.length === 0) {
    return null;
  }

  const selectedItems = items.filter((item) => item.hubAge === selectedAge);

  function selectAge(age: CategoryAge) {
    setSelectedAge(age);
    router.replace(categoryAgeHref(pathname, age), { scroll: false });
  }

  return (
    <section
      id="colecciones"
      className="age-scroll-target border-t border-[var(--color-border)] bg-white"
      aria-labelledby="colecciones-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-12 sm:px-6 sm:py-14">
        <AgeSectionHeading
          id="colecciones-heading"
          title="Selecciones de esta categoría"
          description="Páginas editoriales agrupadas por la edad principal de la selección."
        />
        <div
          className="mt-6 flex flex-wrap gap-2"
          role="group"
          aria-label="Filtrar selecciones por edad"
        >
          {CATEGORY_AGES.map((age) => {
            const selected = selectedAge === age;
            return (
              <button
                key={age}
                type="button"
                aria-pressed={selected}
                onClick={() => selectAge(age)}
                className={`rounded-full border px-4 py-2 text-sm font-bold transition focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[var(--color-primary-600)] ${
                  selected
                    ? "border-[var(--color-primary-700)] bg-[var(--color-primary-700)] text-white shadow-sm"
                    : "border-[var(--color-border)] bg-white text-[var(--color-primary-700)] hover:border-[var(--color-primary-300)] hover:bg-[var(--color-primary-50)]"
                }`}
              >
                {age} años
              </button>
            );
          })}
        </div>
        <section
          className="mt-8"
          aria-labelledby={`colecciones-${selectedAge}-heading`}
        >
          <h2
            id={`colecciones-${selectedAge}-heading`}
            className="sr-only"
          >
            Selecciones para {selectedAge} años
          </h2>
          <ul className="grid gap-4 sm:grid-cols-2">
            {selectedItems.map((item) => (
              <li key={item.href}>
                <Link
                  href={item.href}
                  className="group flex h-full flex-col rounded-xl border border-[var(--color-border)] bg-[var(--color-bg-alt)] p-5 transition hover:border-[var(--color-primary-200)] hover:bg-[var(--color-primary-50)] hover:shadow-sm"
                >
                  <span className="font-[family-name:var(--font-nunito-sans)] text-base font-bold text-[var(--color-primary-700)] group-hover:underline">
                    {item.title}
                  </span>
                  <span className="mt-2 flex-1 text-sm leading-relaxed text-[var(--color-text-secondary)]">
                    {item.description}
                  </span>
                  <span className="mt-3 text-xs font-semibold text-[var(--color-primary-600)]">
                    Leer más →
                  </span>
                </Link>
              </li>
            ))}
          </ul>
        </section>
      </div>
    </section>
  );
}
