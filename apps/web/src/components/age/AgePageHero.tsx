type AgePageHeroProps = {
  h1: string;
  age: number;
  ageLabel: string;
  introductionParagraphs: string[];
};

const cardAccents = [
  "border-l-[var(--color-primary-600)]",
  "border-l-[var(--color-secondary-500)]",
  "border-l-[#059669]",
  "border-l-[var(--color-accent-500)]",
];

export function AgePageHero({
  h1,
  age,
  ageLabel,
  introductionParagraphs,
}: AgePageHeroProps) {
  const [lead, ...body] = introductionParagraphs;

  return (
    <section className="relative overflow-hidden bg-gradient-to-br from-[var(--color-primary-50)] via-white to-[var(--color-accent-100)]/50">
      <div
        className="pointer-events-none absolute -right-20 -top-24 h-80 w-80 rounded-full bg-[var(--color-primary-100)]/80 blur-3xl"
        aria-hidden
      />
      <div
        className="pointer-events-none absolute -bottom-28 -left-16 h-72 w-72 rounded-full bg-[var(--color-secondary-100)]/60 blur-3xl"
        aria-hidden
      />
      <div
        className="pointer-events-none absolute bottom-8 right-1/3 h-40 w-40 rounded-full bg-[var(--color-accent-100)] blur-2xl"
        aria-hidden
      />

      <div className="relative mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-20">
        <div className="flex items-start justify-between gap-4 sm:gap-8">
          <div className="min-w-0 flex-1">
            <span className="inline-flex items-center gap-2 rounded-full bg-[var(--color-primary-700)] px-3.5 py-1.5 text-xs font-semibold uppercase tracking-wide text-white shadow-sm">
              <span
                className="h-1.5 w-1.5 rounded-full bg-[var(--color-accent-500)]"
                aria-hidden
              />
              Guía por edad · {ageLabel}
            </span>
            <h1 className="mt-5 font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold leading-[1.15] tracking-tight text-[var(--color-text)] sm:text-4xl lg:text-[2.85rem]">
              {h1}
            </h1>
          </div>

          <div
            className="flex h-20 w-20 shrink-0 flex-col items-center justify-center rounded-3xl bg-[var(--color-primary-700)] text-white shadow-lg shadow-[var(--color-primary-700)]/25 sm:h-28 sm:w-28 sm:rounded-[1.75rem]"
            aria-hidden
          >
            <span className="font-[family-name:var(--font-nunito-sans)] text-4xl font-extrabold leading-none sm:text-5xl">
              {age}
            </span>
            <span className="mt-0.5 text-[10px] font-semibold uppercase tracking-[0.18em] text-white/80 sm:mt-1 sm:text-[11px]">
              años
            </span>
          </div>
        </div>

        {lead && (
          <p className="mt-8 border-l-4 border-[var(--color-secondary-500)] pl-5 text-lg font-medium leading-8 text-[var(--color-text)] sm:mt-10 sm:pl-6 sm:text-xl sm:leading-9">
            {lead}
          </p>
        )}

        {body.length > 0 && (
          <ul className="mt-8 grid gap-4 sm:grid-cols-2">
            {body.map((paragraph, index) => (
              <li
                key={paragraph}
                className={`rounded-2xl border border-[var(--color-border)] border-l-4 bg-white/90 p-5 shadow-sm backdrop-blur-sm sm:p-6 ${cardAccents[index % cardAccents.length]}`}
              >
                <p className="text-sm leading-relaxed text-[var(--color-text-secondary)] sm:text-base sm:leading-7">
                  {paragraph}
                </p>
              </li>
            ))}
          </ul>
        )}
      </div>
    </section>
  );
}
