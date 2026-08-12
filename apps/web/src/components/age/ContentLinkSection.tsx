import Link from "next/link";
import { AgeSectionHeading } from "./AgeSectionHeading";

type LinkItem = {
  title: string;
  href: string;
  description: string;
};

type ContentLinkSectionProps = {
  id: string;
  title: string;
  description?: string;
  items: LinkItem[];
};

export function ContentLinkSection({ id, title, description, items }: ContentLinkSectionProps) {
  if (items.length === 0) {
    return null;
  }

  return (
    <section
      id={id}
      className="age-scroll-target border-t border-[var(--color-border)] bg-white"
      aria-labelledby={`${id}-heading`}
    >
      <div className="mx-auto max-w-6xl px-4 py-12 sm:px-6 sm:py-14">
        <AgeSectionHeading id={`${id}-heading`} title={title} description={description} />
        <ul className="mt-8 grid gap-4 sm:grid-cols-2">
          {items.map((item) => (
            <li key={item.href}>
              <Link
                href={item.href}
                className="group flex h-full flex-col rounded-xl border border-[var(--color-border)] bg-[var(--color-bg-alt)] p-5 transition hover:border-[var(--color-primary-200)] hover:bg-[var(--color-primary-50)] hover:shadow-sm"
              >
                <span className="font-[family-name:var(--font-nunito-sans)] text-base font-bold text-[var(--color-primary-700)] group-hover:underline">
                  {item.title}
                </span>
                <span className="mt-2 flex-1 text-sm leading-relaxed text-[var(--color-text-secondary)]">
                  {item.description}
                </span>
                <span className="mt-3 text-xs font-semibold text-[var(--color-primary-600)]">
                  Leer más →
                </span>
              </Link>
            </li>
          ))}
        </ul>
      </div>
    </section>
  );
}
