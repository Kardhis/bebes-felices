import Link from "next/link";

type AgeLink = {
  ageLabel: string;
  href: string;
};

type OtherAgesProps = {
  items: AgeLink[];
  currentAgeLabel: string;
};

export function OtherAges({ items, currentAgeLabel }: OtherAgesProps) {
  return (
    <section
      id="otras-edades"
      className="age-scroll-target border-t border-[var(--color-border)] bg-white"
      aria-labelledby="otras-edades-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-12 sm:px-6 sm:py-14">
        <h2
          id="otras-edades-heading"
          className="font-[family-name:var(--font-nunito-sans)] text-xl font-extrabold text-[var(--color-text)] sm:text-2xl"
        >
          También puedes consultar
        </h2>
        <p className="mt-2 text-sm text-[var(--color-text-secondary)]">
          Estás viendo recomendaciones para {currentAgeLabel}.
        </p>
        <div className="mt-6 grid gap-4 sm:grid-cols-2">
          {items.map((item) => (
            <Link
              key={item.href}
              href={item.href}
              className="group rounded-2xl border border-[var(--color-primary-100)] bg-[var(--color-primary-50)] px-6 py-8 text-center transition hover:border-[var(--color-primary-500)] hover:shadow-sm"
            >
              <span className="block font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold text-[var(--color-primary-700)] group-hover:text-[var(--color-primary-600)]">
                {item.ageLabel}
              </span>
              <span className="mt-2 block text-sm font-medium text-[var(--color-text-secondary)]">
                Ver guía completa
              </span>
            </Link>
          ))}
        </div>
      </div>
    </section>
  );
}
