import { AgeSectionHeading } from "@/components/age/AgeSectionHeading";

type Criterion = {
  id: string;
  label: string;
  description: string;
};

type ComparisonMethodologyProps = {
  summary: string;
  criteria: Criterion[];
};

export function ComparisonMethodology({
  summary,
  criteria,
}: ComparisonMethodologyProps) {
  return (
    <section
      id="metodologia"
      className="age-scroll-target bg-[var(--color-bg-alt)]"
      aria-labelledby="metodologia-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="metodologia-heading"
          title="Cómo hemos comparado estas bicicletas"
          description={summary}
        />
        <div className="mt-8 grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {criteria.map((criterion) => (
            <article
              key={criterion.id}
              className="rounded-xl border border-[var(--color-border)] bg-white p-5 shadow-sm"
            >
              <h3 className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-text)]">
                {criterion.label}
              </h3>
              <p className="mt-2 text-sm leading-relaxed text-[var(--color-text-secondary)]">
                {criterion.description}
              </p>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}
