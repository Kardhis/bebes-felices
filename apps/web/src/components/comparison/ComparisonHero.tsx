type ComparisonHeroProps = {
  h1: string;
  targetAge: number;
  introductionParagraphs: string[];
  isDraft: boolean;
};

export function ComparisonHero({
  h1,
  targetAge,
  introductionParagraphs,
  isDraft,
}: ComparisonHeroProps) {
  return (
    <section className="bg-white">
      <div className="mx-auto max-w-6xl px-4 py-12 sm:px-6 sm:py-16">
        <div className="max-w-4xl">
          <div className="flex flex-wrap items-center gap-2">
            <span className="rounded-full bg-[var(--color-primary-50)] px-3 py-1 text-sm font-semibold text-[var(--color-primary-700)]">
              Comparativa · {targetAge} años
            </span>
            {isDraft && (
              <span className="rounded-full bg-amber-50 px-3 py-1 text-sm font-semibold text-amber-800">
                Borrador editorial
              </span>
            )}
          </div>
          <h1 className="mt-5 font-[family-name:var(--font-nunito-sans)] text-4xl font-extrabold leading-tight text-[var(--color-text)] sm:text-5xl">
            {h1}
          </h1>
          <div className="mt-6 space-y-4 text-lg leading-relaxed text-[var(--color-text-secondary)]">
            {introductionParagraphs.map((paragraph) => (
              <p key={paragraph}>{paragraph}</p>
            ))}
          </div>
        </div>
      </div>
    </section>
  );
}
