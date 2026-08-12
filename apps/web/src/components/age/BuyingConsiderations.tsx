import { AgeSectionHeading } from "./AgeSectionHeading";

type BuyingConsiderationsProps = {
  items: string[];
};

export function BuyingConsiderations({ items }: BuyingConsiderationsProps) {
  return (
    <section
      id="como-elegir"
      className="age-scroll-target bg-white"
      aria-labelledby="como-elegir-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="como-elegir-heading"
          title="Qué tener en cuenta antes de comprar"
          description="Checklist práctico para decidir con criterio, sin depender solo del diseño o el precio."
        />
        <ul className="mt-8 grid gap-3 sm:grid-cols-2">
          {items.map((item) => (
            <li
              key={item}
              className="flex gap-3 rounded-xl border border-[var(--color-border)] bg-[var(--color-bg-alt)] px-4 py-3.5"
            >
              <span
                className="mt-0.5 flex h-5 w-5 shrink-0 items-center justify-center rounded-full bg-[var(--color-primary-700)] text-xs font-bold text-white"
                aria-hidden
              >
                ✓
              </span>
              <span className="text-sm leading-relaxed text-[var(--color-text-secondary)]">
                {item}
              </span>
            </li>
          ))}
        </ul>
      </div>
    </section>
  );
}
