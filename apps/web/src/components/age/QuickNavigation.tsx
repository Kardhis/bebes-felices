type QuickNavItem = {
  label: string;
  anchor: string;
};

type QuickNavigationProps = {
  items: QuickNavItem[];
};

export function QuickNavigation({ items }: QuickNavigationProps) {
  return (
    <nav
      aria-label="Navegación rápida de la página"
      className="sticky top-0 z-20 border-b border-[var(--color-border)] bg-white/95 shadow-sm backdrop-blur-md"
    >
      <div className="mx-auto max-w-6xl px-4 sm:px-6">
        <p className="pt-3 text-xs font-semibold uppercase tracking-wide text-[var(--color-text-muted)]">
          Ir a la sección
        </p>
        <ul className="-mx-1 flex gap-2 overflow-x-auto pb-3 pt-2 [scrollbar-width:none] [&::-webkit-scrollbar]:hidden">
          {items.map((item) => (
            <li key={item.anchor} className="shrink-0">
              <a
                href={item.anchor}
                className="inline-flex rounded-full border border-[var(--color-primary-100)] bg-[var(--color-primary-50)] px-3.5 py-1.5 text-sm font-medium text-[var(--color-primary-700)] transition hover:border-[var(--color-primary-500)] hover:bg-white hover:shadow-sm"
              >
                {item.label}
              </a>
            </li>
          ))}
        </ul>
      </div>
    </nav>
  );
}
