"use client";

import { useEffect, useState } from "react";
import { usePathname, useRouter } from "next/navigation";
import { AgeFaq } from "@/components/age/AgeFaq";
import { ArticleBody } from "@/components/article/ArticleBody";
import { ContentLinkSection } from "@/components/age/ContentLinkSection";
import { EditorialHero } from "@/components/editorial/EditorialHero";
import { AffiliationNotice } from "@/components/home/AffiliationNotice";
import {
  CATEGORY_AGES,
  categoryAgeHref,
  type CategoryAge,
} from "@/lib/category/categoryAge";
import type { ArticlePageResponse } from "@/lib/article/getArticlePage";
import { buildFaqPageSchema, JsonLd } from "@/lib/seo/jsonLd";

type ArticleAgeContentProps = {
  page: ArticlePageResponse;
  initialAge: CategoryAge;
};

export function ArticleAgeContent({ page, initialAge }: ArticleAgeContentProps) {
  const router = useRouter();
  const pathname = usePathname();
  const [selectedAge, setSelectedAge] = useState<CategoryAge>(initialAge);

  useEffect(() => {
    setSelectedAge(initialAge);
  }, [initialAge]);

  const variant =
    (page.ageVariants ?? []).find((item) => item.hubAge === selectedAge) ??
    page.ageVariants?.[0];

  function selectAge(age: CategoryAge) {
    setSelectedAge(age);
    router.replace(categoryAgeHref(pathname, age), { scroll: false });
  }

  return (
    <>
      <EditorialHero
        kicker={page.header.kicker}
        h1={page.header.h1}
        introductionParagraphs={
          variant?.introductionParagraphs ?? page.header.introductionParagraphs
        }
      />
      <AffiliationNotice
        noticeText={page.affiliation.noticeText}
        variant="compact"
      />
      <ArticleBody
        key={selectedAge}
        sections={variant?.sections ?? page.sections}
        ageFilter={
          <div className="mb-8">
            <p className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold tracking-tight text-[var(--color-text)] sm:text-3xl">
              Elige la edad
            </p>
            <p className="mt-2 text-base text-[var(--color-text-secondary)]">
              El índice y el texto de esta guía cambian para 3, 4 o 5 años. Ahora
              ves los criterios para {selectedAge} años.
            </p>
            <div
              className="mt-3 flex flex-wrap gap-2"
              role="group"
              aria-label="Filtrar la guía por edad"
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
          </div>
        }
      />
      <ContentLinkSection
        id="contenidos-relacionados"
        title="Contenidos relacionados"
        description="Sigue el circuito editorial hacia el hub de esta edad y páginas de apoyo."
        items={variant?.relatedLinks ?? page.relatedLinks}
      />
      <AgeFaq items={variant?.faq ?? page.faq} />
      {(variant?.faq ?? page.faq).length > 0 && (
        <JsonLd data={buildFaqPageSchema(variant?.faq ?? page.faq)} />
      )}
    </>
  );
}
