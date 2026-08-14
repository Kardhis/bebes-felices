import { AffiliateButton } from "@/components/age/AffiliateButton";
import { AgeSectionHeading } from "@/components/age/AgeSectionHeading";

type ProductAnalysisBodyProps = {
  category: string;
  ageRange: string;
  forWhom: string;
  editorialSummary: string;
  pros: string[];
  cons: string[];
  safetyNotes: string[];
  buyingChecks: string[];
  affiliateHref: string | null;
};

export function ProductAnalysisBody({
  category,
  ageRange,
  forWhom,
  editorialSummary,
  pros,
  cons,
  safetyNotes,
  buyingChecks,
  affiliateHref,
}: ProductAnalysisBodyProps) {
  return (
    <>
      <section className="border-y border-[var(--color-border)] bg-white">
        <div className="mx-auto flex max-w-6xl flex-col gap-6 px-4 py-10 sm:flex-row sm:items-center sm:justify-between sm:px-6">
          <div>
            <div className="flex flex-wrap items-center gap-2">
              <span className="rounded-md bg-[var(--color-secondary-100)] px-2 py-0.5 text-xs font-semibold text-[var(--color-secondary-600)]">
                {category}
              </span>
              <span className="text-xs text-[var(--color-text-muted)]">
                {ageRange}
              </span>
            </div>
            <p className="mt-3 max-w-2xl text-base leading-7 text-[var(--color-text-secondary)]">
              <span className="font-semibold text-[var(--color-text)]">
                Para quién:{" "}
              </span>
              {forWhom}
            </p>
            <p className="mt-2 max-w-2xl text-base leading-7 text-[var(--color-text-secondary)]">
              {editorialSummary}
            </p>
          </div>
          <div className="shrink-0">
            {affiliateHref ? (
              <AffiliateButton href={affiliateHref} />
            ) : (
              <p className="max-w-xs text-sm text-[var(--color-text-muted)]">
                Enlace a Amazon disponible próximamente.
              </p>
            )}
          </div>
        </div>
      </section>

      <section className="bg-[var(--color-bg-alt)]">
        <div className="mx-auto grid max-w-6xl gap-6 px-4 py-14 sm:grid-cols-2 sm:px-6">
          <div className="rounded-2xl border border-[var(--color-border)] bg-white p-6 shadow-sm">
            <h2 className="font-[family-name:var(--font-nunito-sans)] text-xl font-extrabold text-[var(--color-text)]">
              A favor
            </h2>
            <ul className="mt-4 list-disc space-y-2 pl-5 text-sm leading-relaxed text-[var(--color-text-secondary)]">
              {pros.map((item) => (
                <li key={item}>{item}</li>
              ))}
            </ul>
          </div>
          <div className="rounded-2xl border border-[var(--color-border)] bg-white p-6 shadow-sm">
            <h2 className="font-[family-name:var(--font-nunito-sans)] text-xl font-extrabold text-[var(--color-text)]">
              A tener en cuenta
            </h2>
            <ul className="mt-4 list-disc space-y-2 pl-5 text-sm leading-relaxed text-[var(--color-text-secondary)]">
              {cons.map((item) => (
                <li key={item}>{item}</li>
              ))}
            </ul>
          </div>
        </div>
      </section>

      <section
        id="seguridad"
        className="age-scroll-target bg-white"
        aria-labelledby="seguridad-heading"
      >
        <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6">
          <AgeSectionHeading
            id="seguridad-heading"
            title="Seguridad"
            description="Condiciones de uso que no sustituye el producto."
          />
          <ul className="mt-8 grid gap-3 sm:grid-cols-2">
            {safetyNotes.map((item) => (
              <li
                key={item}
                className="rounded-xl border border-[var(--color-border)] bg-[var(--color-bg-alt)] px-4 py-3.5 text-sm leading-relaxed text-[var(--color-text-secondary)]"
              >
                {item}
              </li>
            ))}
          </ul>
        </div>
      </section>

      <section
        id="antes-de-comprar"
        className="age-scroll-target border-t border-[var(--color-border)] bg-[var(--color-bg-alt)]"
        aria-labelledby="antes-de-comprar-heading"
      >
        <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6">
          <AgeSectionHeading
            id="antes-de-comprar-heading"
            title="Qué revisar antes de comprar"
            description="Checklist breve, sin importes ni valoraciones."
          />
          <ul className="mt-8 grid gap-3 sm:grid-cols-2">
            {buyingChecks.map((item) => (
              <li
                key={item}
                className="flex gap-3 rounded-xl border border-[var(--color-border)] bg-white px-4 py-3.5"
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
    </>
  );
}
