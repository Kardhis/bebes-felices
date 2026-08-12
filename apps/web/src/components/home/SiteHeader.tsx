"use client";

import Link from "next/link";
import { useEffect, useId, useState } from "react";

const navItems = [
  { label: "Por edad", href: "/#por-edad" },
  { label: "Juguetes educativos", href: "/juguetes-educativos/" },
  { label: "Movimiento", href: "/movimiento/" },
  { label: "Autonomía", href: "/autonomia/" },
  { label: "Regalos", href: "/regalos/" },
  { label: "Sostenibles", href: "/sostenibles/" },
  { label: "Comparativas", href: "/#comparativas" },
  { label: "Guías", href: "/#guias" },
];

type SiteHeaderProps = {
  /**
   * "hero": cabecera transparente sobre una imagen (home).
   * "inner": cabecera sólida para páginas sin hero fotográfico.
   */
  variant?: "hero" | "inner";
};

export function SiteHeader({ variant = "hero" }: SiteHeaderProps) {
  const [open, setOpen] = useState(false);
  const panelId = useId();
  const isInner = variant === "inner";

  useEffect(() => {
    if (!open) return;
    const onKey = (e: KeyboardEvent) => {
      if (e.key === "Escape") setOpen(false);
    };
    document.addEventListener("keydown", onKey);
    return () => document.removeEventListener("keydown", onKey);
  }, [open]);

  return (
    <header
      className={
        isInner
          ? "sticky top-0 z-30 border-b border-[var(--color-border)] bg-white"
          : "absolute inset-x-0 top-0 z-30"
      }
    >
      <div className="mx-auto flex max-w-6xl items-center justify-between gap-4 px-4 py-4 sm:px-6">
        <Link
          href="/"
          className={
            isInner
              ? "font-[family-name:var(--font-nunito-sans)] text-lg font-extrabold tracking-tight text-[var(--color-primary-700)]"
              : "font-[family-name:var(--font-nunito-sans)] text-lg font-extrabold tracking-tight text-white drop-shadow-sm"
          }
        >
          Bebes Felices
        </Link>

        <nav
          className="hidden items-center gap-4 lg:flex"
          aria-label="Navegación principal"
        >
          {navItems.map((item) => (
            <Link
              key={item.href + item.label}
              href={item.href}
              className={
                isInner
                  ? "text-sm font-medium text-[var(--color-text-secondary)] transition hover:text-[var(--color-primary-700)]"
                  : "text-sm font-medium text-white/90 transition hover:text-white"
              }
            >
              {item.label}
            </Link>
          ))}
          <Link
            href="/#por-edad"
            className={
              isInner
                ? "rounded-lg bg-[var(--color-primary-700)] px-3 py-1.5 text-sm font-semibold text-white shadow-sm transition hover:bg-[var(--color-primary-600)]"
                : "rounded-lg bg-white px-3 py-1.5 text-sm font-semibold text-[var(--color-primary-700)] shadow-sm transition hover:bg-[var(--color-primary-50)]"
            }
          >
            Buscar
          </Link>
        </nav>

        <button
          type="button"
          className={
            isInner
              ? "inline-flex items-center justify-center rounded-lg border border-[var(--color-border)] bg-white px-3 py-2 text-sm font-semibold text-[var(--color-text)] lg:hidden"
              : "inline-flex items-center justify-center rounded-lg border border-white/40 bg-white/10 px-3 py-2 text-sm font-semibold text-white backdrop-blur lg:hidden"
          }
          aria-expanded={open}
          aria-controls={panelId}
          onClick={() => setOpen((v) => !v)}
        >
          {open ? "Cerrar" : "Menú"}
        </button>
      </div>

      {open ? (
        <>
          <button
            type="button"
            className="fixed inset-0 z-40 bg-black/40 lg:hidden"
            aria-label="Cerrar menú"
            onClick={() => setOpen(false)}
          />
          <div
            id={panelId}
            className="absolute inset-x-3 top-16 z-50 rounded-2xl border border-[var(--color-border)] bg-white p-4 shadow-lg lg:hidden"
          >
            <nav className="flex flex-col gap-1" aria-label="Menú móvil">
              {navItems.map((item) => (
                <Link
                  key={item.href + item.label}
                  href={item.href}
                  className="rounded-lg px-3 py-2.5 text-sm font-medium text-[var(--color-text)] hover:bg-[var(--color-primary-50)]"
                  onClick={() => setOpen(false)}
                >
                  {item.label}
                </Link>
              ))}
              <Link
                href="/como-analizamos/"
                className="rounded-lg px-3 py-2.5 text-sm font-medium text-[var(--color-text-secondary)] hover:bg-[var(--color-bg-alt)]"
                onClick={() => setOpen(false)}
              >
                Metodología
              </Link>
              <Link
                href="/quienes-somos/"
                className="rounded-lg px-3 py-2.5 text-sm font-medium text-[var(--color-text-secondary)] hover:bg-[var(--color-bg-alt)]"
                onClick={() => setOpen(false)}
              >
                Sobre BebesFelices
              </Link>
            </nav>
          </div>
        </>
      ) : null}
    </header>
  );
}
