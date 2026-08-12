import { AgeSectionHeading } from "./AgeSectionHeading";

type FaqItem = {
  question: string;
  answer: string;
};

type AgeFaqProps = {
  items: FaqItem[];
};

export function AgeFaq({ items }: AgeFaqProps) {
  return (
    <section
      id="faq"
      className="age-scroll-target bg-[var(--color-bg-alt)]"
      aria-labelledby="faq-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="faq-heading"
          title="Preguntas frecuentes"
          description="Respuestas directas a las dudas más habituales antes de elegir un producto."
        />
        <dl className="mt-8 space-y-3">
          {items.map((item) => (
            <div
              key={item.question}
              className="rounded-xl border border-[var(--color-border)] bg-white p-5 shadow-sm"
            >
              <dt className="font-[family-name:var(--font-nunito-sans)] text-base font-bold text-[var(--color-text)]">
                {item.question}
              </dt>
              <dd className="mt-2 text-sm leading-relaxed text-[var(--color-text-secondary)]">
                {item.answer}
              </dd>
            </div>
          ))}
        </dl>
      </div>
    </section>
  );
}
