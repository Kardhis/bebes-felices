import { AgeSectionHeading } from "./AgeSectionHeading";

type QuickSummaryItem = {
  need: string;
  recommendation: string;
  href: string | null;
};

type QuickSummaryProps = {
  items: QuickSummaryItem[];
};

export function QuickSummary({ items }: QuickSummaryProps) {
  return (
    <section
      id="resumen-rapido"
      className="age-scroll-target border-b border-[var(--color-border)] bg-white"
      aria-labelledby="resumen-rapido-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-12 sm:px-6 sm:py-14">
        <AgeSectionHeading
          id="resumen-rapido-heading"
          title="Resumen rápido"
          description="Empieza por la necesidad que quieres resolver. Cada fila te lleva directamente a la sección relevante."
        />
        <ul className="mt-8 grid gap-3 sm:grid-cols-2">
          {items.map((item) => (
            <li key={item.need}>
              {item.href ? (
                <a
                  href={item.href}
                  className="group flex h-full flex-col justify-between rounded-xl border border-[var(--color-border)] bg-[var(--color-bg-alt)] p-4 transition hover:border-[var(--color-primary-200)] hover:bg-[var(--color-primary-50)] hover:shadow-sm"
                >
                  <span className="text-sm font-semibold text-[var(--color-text)]">
                    {item.need}
                  </span>
                  <span className="mt-2 text-sm text-[var(--color-primary-700)] group-hover:underline">
                    {item.recommendation} →
                  </span>
                </a>
              ) : (
                <div className="flex h-full flex-col justify-between rounded-xl border border-[var(--color-border)] bg-[var(--color-bg-alt)] p-4">
                  <span className="text-sm font-semibold text-[var(--color-text)]">
                    {item.need}
                  </span>
                  <span className="mt-2 text-sm text-[var(--color-text-secondary)]">
                    {item.recommendation}
                  </span>
                </div>
              )}
            </li>
          ))}
        </ul>
      </div>
    </section>
  );
}
