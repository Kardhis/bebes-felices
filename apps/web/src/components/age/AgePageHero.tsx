type AgePageHeroProps = {
  h1: string;
  ageLabel: string;
  introductionParagraphs: string[];
  shortAffiliationNotice: string;
};

export function AgePageHero({
  h1,
  ageLabel,
  introductionParagraphs,
  shortAffiliationNotice,
}: AgePageHeroProps) {
  const [lead, ...body] = introductionParagraphs;

  return (
    <section className="relative overflow-hidden bg-gradient-to-br from-[var(--color-primary-50)] via-white to-[var(--color-accent-100)]/40">
      <div
        className="pointer-events-none absolute -right-16 -top-16 h-64 w-64 rounded-full bg-[var(--color-primary-100)]/60 blur-3xl"
        aria-hidden
      />
      <div className="relative mx-auto max-w-6xl px-4 py-12 sm:px-6 sm:py-16">
        <span className="inline-flex items-center rounded-full bg-[var(--color-primary-700)] px-3 py-1 text-xs font-semibold uppercase tracking-wide text-white">
          Guía por edad · {ageLabel}
        </span>
        <h1 className="mt-4 max-w-3xl font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold leading-tight tracking-tight text-[var(--color-text)] sm:text-4xl lg:text-[2.75rem]">
          {h1}
        </h1>

        {lead && (
          <div className="mt-6 max-w-3xl rounded-2xl border border-[var(--color-border)] bg-white/80 p-6 shadow-sm backdrop-blur-sm sm:p-8">
            <p className="text-lg font-medium leading-8 text-[var(--color-text)] sm:text-xl sm:leading-9">
              {lead}
            </p>
            {body.length > 0 && (
              <div className="mt-5 space-y-4 border-t border-[var(--color-border)] pt-5">
                {body.map((paragraph) => (
                  <p
                    key={paragraph}
                    className="text-base leading-7 text-[var(--color-text-secondary)] sm:leading-8"
                  >
                    {paragraph}
                  </p>
                ))}
              </div>
            )}
          </div>
        )}

        <p className="mt-5 inline-flex max-w-3xl items-start gap-2 rounded-lg border border-[var(--color-border)] bg-white/80 px-3 py-2 text-xs leading-relaxed text-[var(--color-text-muted)] backdrop-blur-sm">
          <span aria-hidden className="mt-0.5 text-[var(--color-secondary-500)]">
            ℹ
          </span>
          {shortAffiliationNotice}
        </p>
      </div>
    </section>
  );
}
