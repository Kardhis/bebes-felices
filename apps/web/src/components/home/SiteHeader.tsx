"use client";

import Link from "next/link";
import { useEffect, useId, useMemo, useRef, useState } from "react";

export type NavigationItem = {
  label: string;
  href: string;
};

const defaultCategories: NavigationItem[] = [
  { label: "Juguetes educativos", href: "/juguetes-educativos/" },
  { label: "Movimiento", href: "/movimiento/" },
  { label: "Autonomía", href: "/autonomia/" },
  { label: "Regalos", href: "/regalos/" },
  { label: "Sostenibles", href: "/sostenibles/" },
];

const editorialItems: NavigationItem[] = [
  { label: "Contacto", href: "/contacto/" },
];

const sectionItems: NavigationItem[] = [
  { label: "Por edad", href: "/#por-edad" },
  { label: "Comparativas", href: "/#comparativas" },
  { label: "Guías", href: "/#guias" },
];

type SiteHeaderProps = {
  /**
   * "hero": cabecera transparente sobre una imagen (home).
   * "inner": cabecera sólida para páginas sin hero fotográfico.
   */
  variant?: "hero" | "inner";
  categoryItems?: NavigationItem[];
};

export function SiteHeader({ variant = "hero", categoryItems = defaultCategories }: SiteHeaderProps) {
  const [open, setOpen] = useState(false);
  const panelId = useId();
  const triggerRef = useRef<HTMLButtonElement>(null);
  const panelRef = useRef<HTMLDivElement>(null);
  const isInner = variant === "inner";
  const navItems = useMemo(
    () => [sectionItems[0], ...categoryItems, ...sectionItems.slice(1)],
    [categoryItems],
  );

  useEffect(() => {
    if (!open) return;
    const onKey = (e: KeyboardEvent) => {
      if (e.key === "Escape") {
        setOpen(false);
        triggerRef.current?.focus();
      }
      if (e.key !== "Tab" || !panelRef.current) return;

      const focusable = panelRef.current.querySelectorAll<HTMLElement>("a, button");
      const first = focusable.item(0);
      const last = focusable.item(focusable.length - 1);
      if (e.shiftKey && document.activeElement === first) {
        e.preventDefault();
        last.focus();
      } else if (!e.shiftKey && document.activeElement === last) {
        e.preventDefault();
        first.focus();
      }
    };
    const previousOverflow = document.body.style.overflow;
    document.body.style.overflow = "hidden";
    document.addEventListener("keydown", onKey);
    panelRef.current?.querySelector<HTMLElement>("a")?.focus();
    return () => {
      document.removeEventListener("keydown", onKey);
      document.body.style.overflow = previousOverflow;
    };
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
          {editorialItems.map((item) => (
            <Link
              key={item.href}
              href={item.href}
              className={
                isInner
                  ? "hidden text-sm font-medium text-[var(--color-text-secondary)] transition hover:text-[var(--color-primary-700)] 2xl:inline"
                  : "hidden text-sm font-medium text-white/90 transition hover:text-white 2xl:inline"
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
          ref={triggerRef}
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
            ref={panelRef}
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
              {editorialItems.map((item) => (
                <Link
                  key={item.href}
                  href={item.href}
                  className="rounded-lg px-3 py-2.5 text-sm font-medium text-[var(--color-text-secondary)] hover:bg-[var(--color-bg-alt)]"
                  onClick={() => setOpen(false)}
                >
                  {item.label}
                </Link>
              ))}
            </nav>
          </div>
        </>
      ) : null}
    </header>
  );
}
