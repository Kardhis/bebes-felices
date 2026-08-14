const cardAccents = [
  "border-l-[var(--color-primary-600)]",
  "border-l-[var(--color-secondary-500)]",
  "border-l-[#059669]",
  "border-l-[var(--color-accent-500)]",
];

type EditorialHeroProps = {
  kicker: string;
  h1: string;
  introductionParagraphs: string[];
};

export function EditorialHero({
  kicker,
  h1,
  introductionParagraphs,
}: EditorialHeroProps) {
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
      <div className="relative mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-20">
        <span className="inline-flex items-center gap-2 rounded-full bg-[var(--color-primary-700)] px-3.5 py-1.5 text-xs font-semibold uppercase tracking-wide text-white shadow-sm">
          <span
            className="h-1.5 w-1.5 rounded-full bg-[var(--color-accent-500)]"
            aria-hidden
          />
          {kicker}
        </span>
        <h1 className="mt-5 max-w-4xl font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold leading-[1.15] tracking-tight text-[var(--color-text)] sm:text-4xl lg:text-[2.75rem]">
          {h1}
        </h1>
        {lead && (
          <p className="mt-8 max-w-3xl border-l-4 border-[var(--color-secondary-500)] pl-5 text-lg font-medium leading-8 text-[var(--color-text)] sm:mt-10 sm:pl-6 sm:text-xl sm:leading-9">
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
