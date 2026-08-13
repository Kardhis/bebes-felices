import { AgeSectionHeading } from "@/components/age/AgeSectionHeading";

type BuyingGuideSection = {
  title: string;
  paragraphs: string[];
};

type ComparisonBuyingGuideProps = {
  sections: BuyingGuideSection[];
};

export function ComparisonBuyingGuide({
  sections,
}: ComparisonBuyingGuideProps) {
  return (
    <section
      id="guia-de-compra"
      className="age-scroll-target bg-white"
      aria-labelledby="guia-compra-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="guia-compra-heading"
          title="Qué revisar antes de comprar"
          description="La talla adecuada y el control del niño importan más que la edad indicada en la caja."
        />
        <div className="mt-8 grid gap-5 md:grid-cols-2">
          {sections.map((section) => (
            <article
              key={section.title}
              className="rounded-xl border border-[var(--color-border)] p-5"
            >
              <h3 className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-text)]">
                {section.title}
              </h3>
              <div className="mt-3 space-y-3 text-sm leading-relaxed text-[var(--color-text-secondary)]">
                {section.paragraphs.map((paragraph) => (
                  <p key={paragraph}>{paragraph}</p>
                ))}
              </div>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}
