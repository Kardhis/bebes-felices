type AffiliationNoticeProps = {
  noticeText: string;
  variant?: "default" | "compact";
};

export function AffiliationNotice({ noticeText, variant = "default" }: AffiliationNoticeProps) {
  if (variant === "compact") {
    return (
      <aside
        className="border-b border-[var(--color-border)] bg-[var(--color-accent-100)]/50"
        aria-label="Aviso de afiliación"
      >
        <div className="mx-auto max-w-6xl px-4 py-3 sm:px-6">
          <p className="text-xs leading-relaxed text-[var(--color-text-secondary)]">
            <span className="font-semibold text-[var(--color-text)]">Aviso de afiliación:</span>{" "}
            {noticeText}
          </p>
        </div>
      </aside>
    );
  }

  return (
    <section
      className="border-y border-[var(--color-border)] bg-white"
      aria-labelledby="afiliacion-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-10 sm:px-6">
        <h2
          id="afiliacion-heading"
          className="font-[family-name:var(--font-nunito-sans)] text-xl font-extrabold text-[var(--color-text)]"
        >
          Aviso de afiliación
        </h2>
        <p className="mt-3 max-w-3xl text-sm leading-relaxed text-[var(--color-text-secondary)]">
          {noticeText}
        </p>
      </div>
    </section>
  );
}
