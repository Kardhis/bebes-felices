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
  tone?: "default" | "alt";
  /** Renders without full-width chrome so it can sit in a multi-column layout. */
  embedded?: boolean;
};

export function ContentLinkSection({
  id,
  title,
  description,
  items,
  tone = "default",
  embedded = false,
}: ContentLinkSectionProps) {
  if (items.length === 0) {
    return null;
  }

  const isAlt = tone === "alt";
  const cardSurface = isAlt || embedded ? "bg-white" : "bg-[var(--color-bg-alt)]";

  const headingAndList = (
    <>
      <AgeSectionHeading id={`${id}-heading`} title={title} description={description} />
      <ul
        className={
          embedded
            ? "mt-8 grid gap-4 sm:grid-cols-2 md:grid-cols-1"
            : "mt-8 grid gap-4 sm:grid-cols-2"
        }
      >
        {items.map((item) => (
          <li key={item.href}>
            <Link
              href={item.href}
              className={`group flex h-full flex-col rounded-xl border border-[var(--color-border)] p-5 transition hover:border-[var(--color-primary-200)] hover:bg-[var(--color-primary-50)] hover:shadow-sm ${cardSurface}`}
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
    </>
  );

  if (embedded) {
    return (
      <section id={id} className="age-scroll-target min-w-0" aria-labelledby={`${id}-heading`}>
        {headingAndList}
      </section>
    );
  }

  return (
    <section
      id={id}
      className={`age-scroll-target border-t border-[var(--color-border)] ${
        isAlt ? "bg-[var(--color-bg-alt)]" : "bg-white"
      }`}
      aria-labelledby={`${id}-heading`}
    >
      <div className="mx-auto max-w-6xl px-4 py-12 sm:px-6 sm:py-14">{headingAndList}</div>
    </section>
  );
}
