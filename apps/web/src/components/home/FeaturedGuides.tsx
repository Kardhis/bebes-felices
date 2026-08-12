import Link from "next/link";

type LinkItem = {
  title: string;
  href: string;
  description: string;
};

type FeaturedGuidesProps = {
  items: LinkItem[];
};

export function FeaturedGuides({ items }: FeaturedGuidesProps) {
  return (
    <section id="guias" className="scroll-mt-8" aria-labelledby="guias-heading">
      <h2
        id="guias-heading"
        className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold text-[var(--color-text)]"
      >
        Guías destacadas
      </h2>
      <p className="mt-2 text-[var(--color-text-secondary)]">
        Páginas pilar para decidir con más contexto antes de comprar.
      </p>
      <ul className="mt-6 space-y-5">
        {items.map((g) => (
          <li key={g.href} className="border-b border-[var(--color-border)] pb-5 last:border-0">
            <Link
              href={g.href}
              className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-primary-700)] hover:text-[var(--color-primary-600)]"
            >
              {g.title}
            </Link>
            <p className="mt-1 text-sm text-[var(--color-text-secondary)]">
              {g.description}
            </p>
          </li>
        ))}
      </ul>
    </section>
  );
}
