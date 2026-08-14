import type { ArticleSection } from "@/lib/article/getArticlePage";

type ArticleBodyProps = {
  sections: ArticleSection[];
};

export function ArticleBody({ sections }: ArticleBodyProps) {
  if (sections.length === 0) {
    return null;
  }

  return (
    <div className="bg-white">
      <div className="mx-auto grid max-w-6xl gap-10 px-4 py-12 sm:px-6 lg:grid-cols-[16rem_minmax(0,1fr)] lg:py-16">
        <nav aria-label="Índice del artículo" className="lg:pt-2">
          <p className="text-xs font-semibold uppercase tracking-wide text-[var(--color-text-muted)]">
            En esta guía
          </p>
          <ol className="mt-4 space-y-2">
            {sections.map((section, index) => (
              <li key={section.id}>
                <a
                  href={`#${section.id}`}
                  className="text-sm font-medium text-[var(--color-primary-700)] hover:underline"
                >
                  {index + 1}. {section.title}
                </a>
              </li>
            ))}
          </ol>
        </nav>
        <div className="space-y-12">
          {sections.map((section) => (
            <section
              key={section.id}
              id={section.id}
              className="age-scroll-target scroll-mt-24"
            >
              <h2 className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold tracking-tight text-[var(--color-text)]">
                {section.title}
              </h2>
              <div className="mt-4 space-y-4 text-base leading-7 text-[var(--color-text-secondary)]">
                {section.paragraphs.map((paragraph) => (
                  <p key={paragraph}>{paragraph}</p>
                ))}
              </div>
            </section>
          ))}
        </div>
      </div>
    </div>
  );
}
