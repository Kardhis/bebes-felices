import Link from "next/link";

import {
  formatEditorialDate,
  isUpdateDateTransparencyItem,
} from "@/lib/editorial/formatEditorialDate";

type TrustAuthorityProps = {
  howWeSelect: string;
  analysisCriteria: string[];
  editorialTransparency: string[];
  updatedAt: string;
};

export function TrustAuthority({
  howWeSelect,
  analysisCriteria,
  editorialTransparency,
  updatedAt,
}: TrustAuthorityProps) {
  return (
    <section
      id="confianza"
      className="scroll-mt-8 bg-[var(--color-primary-50)]"
      aria-labelledby="confianza-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6">
        <h2
          id="confianza-heading"
          className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold text-[var(--color-text)] sm:text-3xl"
        >
          Cómo seleccionamos los productos
        </h2>
        <p className="mt-3 max-w-3xl text-[var(--color-text-secondary)]">
          {howWeSelect}
        </p>
        <p className="mt-2 text-sm text-[var(--color-text-muted)]">
          Última actualización de esta página:{" "}
          <time dateTime={updatedAt}>{formatEditorialDate(updatedAt)}</time>
        </p>

        <div className="mt-10 grid gap-10 md:grid-cols-2">
          <div>
            <h3 className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-text)]">
              Criterios de análisis
            </h3>
            <ul className="mt-3 list-disc space-y-2 pl-5 text-[var(--color-text-secondary)]">
              {analysisCriteria.map((t) => (
                <li key={t}>{t}</li>
              ))}
            </ul>
          </div>
          <div>
            <h3 className="font-[family-name:var(--font-nunito-sans)] text-lg font-bold text-[var(--color-text)]">
              Transparencia editorial
            </h3>
            <ul className="mt-3 list-disc space-y-2 pl-5 text-[var(--color-text-secondary)]">
              {editorialTransparency.map((t) => (
                <li key={t}>
                  {isUpdateDateTransparencyItem(t) ? (
                    <span className="group/date relative inline cursor-help font-semibold text-[var(--color-primary-700)] underline decoration-dotted underline-offset-2">
                      {t}
                      <span
                        role="tooltip"
                        className="pointer-events-none absolute bottom-full left-0 z-10 mb-1 whitespace-nowrap rounded-md bg-[var(--color-text)] px-2 py-1 text-xs font-normal text-white opacity-0 shadow-sm group-hover/date:opacity-100"
                      >
                        {formatEditorialDate(updatedAt)}
                      </span>
                    </span>
                  ) : (
                    t
                  )}
                </li>
              ))}
            </ul>
            <Link
              href="/como-analizamos/"
              className="mt-4 inline-block text-sm font-semibold text-[var(--color-primary-700)] hover:underline"
            >
              Leer metodología completa
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
}
