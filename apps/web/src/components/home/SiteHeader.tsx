"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
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
  const [activeSection, setActiveSection] = useState("");
  const pathname = usePathname();
  const panelId = useId();
  const triggerRef = useRef<HTMLButtonElement>(null);
  const panelRef = useRef<HTMLDivElement>(null);
  const isInner = variant === "inner";
  const navItems = useMemo(
    () => [sectionItems[0], ...categoryItems, ...sectionItems.slice(1)],
    [categoryItems],
  );

  useEffect(() => {
    if (pathname !== "/") {
      setActiveSection("");
      return;
    }

    const sectionIds = sectionItems.map((item) => item.href.split("#")[1]).filter(Boolean);
    const updateFromHash = () => setActiveSection(window.location.hash.slice(1));
    const Observer = window.IntersectionObserver as typeof IntersectionObserver | undefined;
    updateFromHash();

    if (!Observer) {
      window.addEventListener("hashchange", updateFromHash);
      return () => window.removeEventListener("hashchange", updateFromHash);
    }

    const observer = new Observer(
      (entries) => {
        const visible = entries
          .filter((entry) => entry.isIntersecting)
          .sort((a, b) => a.boundingClientRect.top - b.boundingClientRect.top);
        if (visible[0]) setActiveSection(visible[0].target.id);
      },
      { rootMargin: "-20% 0px -65% 0px" },
    );

    sectionIds.forEach((id) => {
      const section = document.getElementById(id);
      if (section) observer.observe(section);
    });
    window.addEventListener("hashchange", updateFromHash);

    return () => {
      observer.disconnect();
      window.removeEventListener("hashchange", updateFromHash);
    };
  }, [pathname]);

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

  const isActive = (href: string) => {
    const [itemPath, hash] = href.split("#");
    const normalizedItemPath = itemPath.replace(/\/+$/, "") || "/";
    const normalizedPathname = pathname.replace(/\/+$/, "") || "/";
    return hash
      ? normalizedPathname === normalizedItemPath && activeSection === hash
      : normalizedPathname === normalizedItemPath;
  };

  return (
    <header
      className={
        isInner
          ? "sticky top-0 z-30 bg-[var(--color-primary-700)] shadow-sm"
          : "absolute inset-x-0 top-0 z-30"
      }
    >
      <div className="mx-auto flex max-w-6xl items-center justify-between gap-4 px-4 py-4 sm:px-6">
        <Link
          href="/"
          className="shrink-0 whitespace-nowrap font-[family-name:var(--font-nunito-sans)] text-lg font-extrabold tracking-tight text-white drop-shadow-sm"
        >
          Bebes Felices
        </Link>

        <nav
          className="hidden items-center gap-3 lg:flex"
          aria-label="Navegación principal"
        >
          {navItems.map((item) => {
            const active = isActive(item.href);
            return (
              <Link
                key={item.href + item.label}
                href={item.href}
                aria-current={active ? (item.href.includes("#") ? "location" : "page") : undefined}
                className={
                  active
                    ? "border-b-2 border-white px-1 py-1 text-sm font-bold text-white"
                    : "px-1 py-1 text-sm font-medium text-white/90 transition hover:text-white"
                }
              >
                {item.label}
              </Link>
            );
          })}
          {editorialItems.map((item) => {
            const active = isActive(item.href);
            return (
              <Link
                key={item.href}
                href={item.href}
                aria-current={active ? "page" : undefined}
                className={
                  active
                    ? "hidden border-b-2 border-white px-1 py-1 text-sm font-bold text-white 2xl:inline"
                    : "hidden px-1 py-1 text-sm font-medium text-white/90 transition hover:text-white 2xl:inline"
                }
              >
                {item.label}
              </Link>
            );
          })}
          <Link
            href="/#por-edad"
            className="rounded-lg bg-white px-3 py-1.5 text-sm font-semibold text-[var(--color-primary-700)] shadow-sm transition hover:bg-[var(--color-primary-50)]"
          >
            Buscar
          </Link>
        </nav>

        <button
          ref={triggerRef}
          type="button"
          className="inline-flex items-center justify-center rounded-lg border border-white/40 bg-white/10 px-3 py-2 text-sm font-semibold text-white backdrop-blur lg:hidden"
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
              {navItems.map((item) => {
                const active = isActive(item.href);
                return (
                  <Link
                    key={item.href + item.label}
                    href={item.href}
                    aria-current={
                      active ? (item.href.includes("#") ? "location" : "page") : undefined
                    }
                    className={
                      active
                        ? "rounded-lg bg-[var(--color-primary-50)] px-3 py-2.5 text-sm font-bold text-[var(--color-primary-700)]"
                        : "rounded-lg px-3 py-2.5 text-sm font-medium text-[var(--color-text)] hover:bg-[var(--color-primary-50)]"
                    }
                    onClick={() => setOpen(false)}
                  >
                    {item.label}
                  </Link>
                );
              })}
              {editorialItems.map((item) => {
                const active = isActive(item.href);
                return (
                  <Link
                    key={item.href}
                    href={item.href}
                    aria-current={active ? "page" : undefined}
                    className={
                      active
                        ? "rounded-lg bg-[var(--color-primary-50)] px-3 py-2.5 text-sm font-bold text-[var(--color-primary-700)]"
                        : "rounded-lg px-3 py-2.5 text-sm font-medium text-[var(--color-text-secondary)] hover:bg-[var(--color-bg-alt)]"
                    }
                    onClick={() => setOpen(false)}
                  >
                    {item.label}
                  </Link>
                );
              })}
            </nav>
          </div>
        </>
      ) : null}
    </header>
  );
}
