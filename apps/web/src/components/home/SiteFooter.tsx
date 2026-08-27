import Link from "next/link";

import { formatEditorialDate } from "@/lib/editorial/formatEditorialDate";

type LegalLink = {
  label: string;
  href: string;
};

type SiteFooterProps = {
  legalLinks: LegalLink[];
  updatedAt: string;
  discoverLinks?: Array<{ label: string; href: string }>;
};

const defaultDiscoverLinks = [
  { label: "Por edad", href: "/#por-edad" },
  { label: "Sostenibles", href: "/sostenibles/" },
  { label: "Educativos", href: "/juguetes-educativos/" },
  { label: "Movimiento", href: "/movimiento/" },
  { label: "Autonomía", href: "/autonomia/" },
  { label: "Regalos", href: "/regalos/" },
  { label: "Comparativas", href: "/#comparativas" },
  { label: "Guías", href: "/#guias" },
];

export function SiteFooter({
  legalLinks,
  updatedAt,
  discoverLinks = defaultDiscoverLinks,
}: SiteFooterProps) {
  return (
    <footer className="bg-[var(--color-footer-bg)] text-[var(--color-footer-text)]">
      <div className="mx-auto grid max-w-6xl gap-10 px-4 py-12 sm:px-6 md:grid-cols-4">
        <div className="md:col-span-1">
          <p className="font-[family-name:var(--font-nunito-sans)] text-xl font-extrabold text-[var(--color-footer-text)]">
            Bebes Felices
          </p>
          <p className="mt-3 text-sm leading-relaxed text-[var(--color-footer-text-secondary)]">
            Guías y comparativas para elegir productos adecuados para niños de 3
            a 5 años.
          </p>
          <p className="mt-4 text-xs text-[var(--color-footer-text-muted)]">
            Actualizado:{" "}
            <time dateTime={updatedAt}>{formatEditorialDate(updatedAt)}</time>
          </p>
        </div>

        <div>
          <h2 className="font-[family-name:var(--font-nunito-sans)] text-sm font-bold uppercase tracking-wide text-[var(--color-footer-text)]">
            Descubre
          </h2>
          <ul className="mt-3 space-y-2 text-sm">
            {discoverLinks.map((link) => (
              <li key={link.href}>
                <Link
                  className="text-[var(--color-footer-text-secondary)] transition hover:text-[var(--color-accent-500)]"
                  href={link.href}
                >
                  {link.label}
                </Link>
              </li>
            ))}
          </ul>
        </div>

        <div>
          <h2 className="font-[family-name:var(--font-nunito-sans)] text-sm font-bold uppercase tracking-wide text-[var(--color-footer-text)]">
            Sobre Bebes Felices
          </h2>
          <ul className="mt-3 space-y-2 text-sm">
            <li>
              <Link className="text-[var(--color-footer-text-secondary)] transition hover:text-[var(--color-accent-500)]" href="/contacto/">
                Contacto
              </Link>
            </li>
          </ul>
        </div>

        <div>
          <h2 className="font-[family-name:var(--font-nunito-sans)] text-sm font-bold uppercase tracking-wide text-[var(--color-footer-text)]">
            Información legal
          </h2>
          <ul className="mt-3 space-y-2 text-sm">
            {legalLinks.map((l) => (
              <li key={l.href}>
                <Link
                  className="text-[var(--color-footer-text-secondary)] transition hover:text-[var(--color-accent-500)]"
                  href={l.href}
                >
                  {l.label}
                </Link>
              </li>
            ))}
          </ul>
        </div>
      </div>

      <div className="border-t border-[var(--color-footer-border)] px-4 py-4 text-center text-xs text-[var(--color-footer-text-muted)] sm:px-6">
        © {new Date().getFullYear()} Bebes Felices
      </div>
    </footer>
  );
}
