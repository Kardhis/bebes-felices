import Link from "next/link";

type LinkItem = {
  title: string;
  href: string;
  description: string;
};

type RecentComparisonsProps = {
  items: LinkItem[];
};

export function RecentComparisons({ items }: RecentComparisonsProps) {
  return (
    <section
      id="comparativas"
      className="scroll-mt-8"
      aria-labelledby="comparativas-heading"
    >
      <h2
        id="comparativas-heading"
        className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold text-[var(--color-text)]"
      >
        Comparativas recientes
      </h2>
      <p className="mt-2 text-[var(--color-text-secondary)]">
        Contenidos con intención comercial y selección razonada.
      </p>
      <ul className="mt-6 space-y-5">
        {items.map((c) => (
          <li key={c.href} className="border-b border-[var(--color-border)] pb-5 last:border-0">
            <Link
              href={c.href}
              className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-primary-700)] hover:text-[var(--color-primary-600)]"
            >
              {c.title}
            </Link>
            <p className="mt-1 text-sm text-[var(--color-text-secondary)]">
              {c.description}
            </p>
          </li>
        ))}
      </ul>
    </section>
  );
}
