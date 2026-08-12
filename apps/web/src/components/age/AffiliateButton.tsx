type AffiliateButtonProps = {
  href: string;
  label?: string;
};

/**
 * Botón de afiliación hacia Amazon. Solo debe renderizarse cuando exista un
 * enlace ya validado en el backend; este componente no decide si el enlace
 * es válido, solo aplica los atributos de transparencia requeridos.
 */
export function AffiliateButton({ href, label = "Ver en Amazon" }: AffiliateButtonProps) {
  return (
    <a
      href={href}
      target="_blank"
      rel="sponsored nofollow noopener"
      className="inline-flex items-center justify-center rounded-lg bg-[var(--color-secondary-500)] px-4 py-2 text-sm font-semibold text-white transition hover:bg-[var(--color-secondary-600)]"
    >
      {label}
    </a>
  );
}
