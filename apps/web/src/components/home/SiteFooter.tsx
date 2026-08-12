import Link from "next/link";

type LegalLink = {
  label: string;
  href: string;
};

type SiteFooterProps = {
  legalLinks: LegalLink[];
  updatedAt: string;
};

export function SiteFooter({ legalLinks, updatedAt }: SiteFooterProps) {
  return (
    <footer className="border-t border-[var(--color-border)] bg-[var(--color-bg-alt)]">
      <div className="mx-auto grid max-w-6xl gap-10 px-4 py-12 sm:px-6 md:grid-cols-4">
        <div className="md:col-span-1">
          <p className="font-[family-name:var(--font-nunito-sans)] text-xl font-extrabold text-[var(--color-primary-700)]">
            Bebes Felices
          </p>
          <p className="mt-3 text-sm leading-relaxed text-[var(--color-text-secondary)]">
            Guías y comparativas para elegir productos adecuados para niños de 3
            a 5 años.
          </p>
          <p className="mt-4 text-xs text-[var(--color-text-muted)]">
            Actualizado: {updatedAt}
          </p>
        </div>

        <div>
          <h2 className="font-[family-name:var(--font-nunito-sans)] text-sm font-bold uppercase tracking-wide text-[var(--color-text)]">
            Descubre
          </h2>
          <ul className="mt-3 space-y-2 text-sm">
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/por-edad/3-anos/">
                Por edad
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/juguetes-educativos/">
                Juguetes educativos
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/movimiento/">
                Movimiento
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/autonomia/">
                Autonomía
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/regalos/">
                Regalos
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/sostenibles/">
                Sostenibles
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/#comparativas">
                Comparativas
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/#guias">
                Guías
              </Link>
            </li>
          </ul>
        </div>

        <div>
          <h2 className="font-[family-name:var(--font-nunito-sans)] text-sm font-bold uppercase tracking-wide text-[var(--color-text)]">
            Sobre BebesFelices
          </h2>
          <ul className="mt-3 space-y-2 text-sm">
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/quienes-somos/">
                Sobre nosotros
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/como-analizamos/">
                Metodología
              </Link>
            </li>
            <li>
              <Link className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]" href="/contacto/">
                Contacto
              </Link>
            </li>
          </ul>
        </div>

        <div>
          <h2 className="font-[family-name:var(--font-nunito-sans)] text-sm font-bold uppercase tracking-wide text-[var(--color-text)]">
            Información legal
          </h2>
          <ul className="mt-3 space-y-2 text-sm">
            {legalLinks.map((l) => (
              <li key={l.href}>
                <Link
                  className="text-[var(--color-text-secondary)] hover:text-[var(--color-primary-700)]"
                  href={l.href}
                >
                  {l.label}
                </Link>
              </li>
            ))}
          </ul>
        </div>
      </div>

      <div className="border-t border-[var(--color-border)] px-4 py-4 text-center text-xs text-[var(--color-text-muted)] sm:px-6">
        © {new Date().getFullYear()} BebesFelices
      </div>
    </footer>
  );
}
