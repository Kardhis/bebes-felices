import Link from "next/link";
import { AgeSectionHeading } from "./AgeSectionHeading";

type LinkItem = {
  title: string;
  href: string;
  description: string;
};

type NeedGroup = {
  title: string;
  anchor: string;
  items: LinkItem[];
};

type OptionsByNeedProps = {
  groups: NeedGroup[];
};

const accents = [
  "border-l-[var(--color-primary-600)]",
  "border-l-[var(--color-secondary-500)]",
  "border-l-[#059669]",
  "border-l-[var(--color-accent-500)]",
];

export function OptionsByNeed({ groups }: OptionsByNeedProps) {
  return (
    <section
      className="bg-[var(--color-bg-alt)]"
      aria-labelledby="opciones-por-necesidad-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="opciones-por-necesidad-heading"
          title="Mejores opciones por necesidad"
          description="Explora por tipo de necesidad. Cada bloque enlaza a categorías y contenidos específicos para esta edad."
        />
        <div className="mt-10 grid gap-6 md:grid-cols-2">
          {groups.map((group, index) => (
            <article
              key={group.anchor}
              id={group.anchor.replace("#", "")}
              className={`age-scroll-target rounded-2xl border border-[var(--color-border)] border-l-4 ${accents[index % accents.length]} bg-white p-6 shadow-sm`}
            >
              <h3 className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-text)]">
                {group.title}
              </h3>
              <ul className="mt-4 space-y-4">
                {group.items.map((item) => (
                  <li key={item.href} className="border-t border-[var(--color-border)] pt-4 first:border-0 first:pt-0">
                    <Link
                      href={item.href}
                      className="font-semibold text-[var(--color-primary-700)] transition hover:text-[var(--color-primary-600)] hover:underline"
                    >
                      {item.title}
                    </Link>
                    <p className="mt-1 text-sm leading-relaxed text-[var(--color-text-secondary)]">
                      {item.description}
                    </p>
                  </li>
                ))}
              </ul>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}
