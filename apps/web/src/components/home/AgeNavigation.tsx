import Link from "next/link";

type AgeLink = {
  ageLabel: string;
  href: string;
};

type AgeNavigationProps = {
  items: AgeLink[];
};

export function AgeNavigation({ items }: AgeNavigationProps) {
  return (
    <section
      id="por-edad"
      className="scroll-mt-8 border-b border-[var(--color-border)] bg-[var(--color-bg)]"
      aria-labelledby="age-nav-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6">
        <h2
          id="age-nav-heading"
          className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold text-[var(--color-text)] sm:text-3xl"
        >
          Explora por edad
        </h2>
        <p className="mt-2 max-w-2xl text-[var(--color-text-secondary)]">
          Empieza por la edad del niño o la niña. Cada página reúne guías,
          comparativas e ideas de regalo adaptadas.
        </p>
        <div className="mt-8 grid gap-4 sm:grid-cols-3">
          {items.map((age) => (
            <Link
              key={age.href}
              href={age.href}
              className="group rounded-2xl border border-[var(--color-primary-100)] bg-[var(--color-primary-50)] px-5 py-8 text-center transition hover:border-[var(--color-primary-500)] hover:shadow-sm"
            >
              <span className="block font-[family-name:var(--font-nunito-sans)] text-4xl font-extrabold text-[var(--color-primary-700)] group-hover:text-[var(--color-primary-600)]">
                {age.ageLabel}
              </span>
              <span className="mt-2 block text-sm font-medium text-[var(--color-text-secondary)]">
                Ver productos y guías
              </span>
            </Link>
          ))}
        </div>
      </div>
    </section>
  );
}
