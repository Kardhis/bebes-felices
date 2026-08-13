import { AffiliateButton } from "@/components/age/AffiliateButton";
import { AgeSectionHeading } from "@/components/age/AgeSectionHeading";
import type { ComparisonPageResponse } from "@/lib/comparison/getComparisonPage";

type RankedEntry = ComparisonPageResponse["entries"][number];

type RankedProductListProps = {
  entries: RankedEntry[];
};

export function RankedProductList({ entries }: RankedProductListProps) {
  return (
    <section
      id="comparativa"
      className="age-scroll-target bg-white"
      aria-labelledby="comparativa-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="comparativa-heading"
          title="Nuestra selección comparada"
          description="El orden responde a criterios editoriales para niños de 3 años. No utilizamos precios, estrellas ni puntuaciones comerciales para decidirlo."
        />
        <ol className="mt-9 space-y-6">
          {entries.map((entry) => (
            <li
              key={entry.productId}
              id={`producto-${entry.productId}`}
              className="age-scroll-target overflow-hidden rounded-2xl border border-[var(--color-border)] bg-white shadow-sm"
            >
              <article className="grid gap-6 p-6 md:grid-cols-[minmax(0,1fr)_15rem] md:p-8">
                <div>
                  <div className="flex flex-wrap items-center gap-3">
                    <span className="inline-flex h-9 w-9 items-center justify-center rounded-full bg-[var(--color-primary-700)] font-bold text-white">
                      {entry.rank}
                    </span>
                    <p className="text-sm font-semibold text-[var(--color-secondary-600)]">
                      {entry.bestFor}
                    </p>
                  </div>
                  <h3 className="mt-4 font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold text-[var(--color-text)]">
                    {entry.title}
                  </h3>
                  <p className="mt-3 leading-relaxed text-[var(--color-text-secondary)]">
                    {entry.editorialSummary}
                  </p>
                  <p className="mt-3 text-sm font-medium text-[var(--color-text-muted)]">
                    Edad orientativa: {entry.ageRange}
                  </p>

                  <dl className="mt-6 grid gap-3 sm:grid-cols-2">
                    {entry.criteriaNotes.map((note) => (
                      <div
                        key={note.criterion}
                        className="rounded-lg bg-[var(--color-bg-alt)] p-3"
                      >
                        <dt className="text-xs font-semibold uppercase tracking-wide text-[var(--color-text-muted)]">
                          {note.criterion}
                        </dt>
                        <dd className="mt-1 text-sm text-[var(--color-text-secondary)]">
                          {note.note}
                        </dd>
                      </div>
                    ))}
                  </dl>
                </div>

                <div className="space-y-5 md:border-l md:border-[var(--color-border)] md:pl-6">
                  <div>
                    <h4 className="font-bold text-[var(--color-text)]">A favor</h4>
                    <ul className="mt-2 list-disc space-y-1.5 pl-5 text-sm text-[var(--color-text-secondary)]">
                      {entry.pros.map((pro) => (
                        <li key={pro}>{pro}</li>
                      ))}
                    </ul>
                  </div>
                  <div>
                    <h4 className="font-bold text-[var(--color-text)]">A tener en cuenta</h4>
                    <ul className="mt-2 list-disc space-y-1.5 pl-5 text-sm text-[var(--color-text-secondary)]">
                      {entry.cons.map((con) => (
                        <li key={con}>{con}</li>
                      ))}
                    </ul>
                  </div>
                  {entry.affiliateHref ? (
                    <AffiliateButton href={entry.affiliateHref} />
                  ) : (
                    <p className="rounded-lg bg-[var(--color-bg-alt)] p-3 text-sm text-[var(--color-text-muted)]">
                      Enlace pendiente de validación editorial.
                    </p>
                  )}
                </div>
              </article>
            </li>
          ))}
        </ol>
      </div>
    </section>
  );
}
