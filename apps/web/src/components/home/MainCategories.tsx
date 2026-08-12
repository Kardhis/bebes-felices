import Link from "next/link";

type LinkItem = {
  title: string;
  href: string;
  description: string;
};

type MainCategoriesProps = {
  items: LinkItem[];
};

const accents = [
  "border-l-[var(--color-primary-600)]",
  "border-l-[var(--color-secondary-500)]",
  "border-l-[#059669]",
  "border-l-[var(--color-accent-500)]",
  "border-l-[#0f766e]",
];

export function MainCategories({ items }: MainCategoriesProps) {
  return (
    <section
      id="categorias"
      className="scroll-mt-8 bg-[var(--color-bg-alt)]"
      aria-labelledby="categorias-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6">
        <h2
          id="categorias-heading"
          className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold text-[var(--color-text)] sm:text-3xl"
        >
          Categorías principales
        </h2>
        <p className="mt-2 max-w-2xl text-[var(--color-text-secondary)]">
          Navega por necesidad o tipo de producto. Cada categoría actúa como
          página pilar hacia comparativas y guías.
        </p>
        <ul className="mt-8 grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {items.map((c, i) => (
            <li key={c.href}>
              <Link
                href={c.href}
                className={`block border-l-4 ${accents[i % accents.length]} bg-white px-5 py-5 transition hover:bg-[var(--color-primary-50)]`}
              >
                <span className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-text)]">
                  {c.title}
                </span>
                <span className="mt-2 block text-sm leading-relaxed text-[var(--color-text-secondary)]">
                  {c.description}
                </span>
              </Link>
            </li>
          ))}
        </ul>
      </div>
    </section>
  );
}
