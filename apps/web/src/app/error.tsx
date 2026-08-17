"use client";

import { SiteHeader } from "@/components/home/SiteHeader";

export default function ErrorPage({
  reset,
}: {
  error: Error & { digest?: string };
  reset: () => void;
}) {
  return (
    <>
      <SiteHeader variant="inner" />
      <main
        id="contenido-principal"
        className="mx-auto flex min-h-[70vh] max-w-2xl flex-col items-center justify-center px-4 py-16 text-center sm:px-6"
      >
        <p className="text-sm font-semibold uppercase tracking-wide text-[var(--color-primary-700)]">
          Conexión temporalmente no disponible
        </p>
        <h1 className="mt-3 font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold text-[var(--color-text)]">
          No hemos podido cargar esta página
        </h1>
        <p className="mt-4 text-[var(--color-text-secondary)]">
          Vuelve a intentarlo en unos instantes. Tus datos y tu navegación están seguros.
        </p>
        <button
          type="button"
          onClick={reset}
          className="mt-8 rounded-xl bg-[var(--color-primary-700)] px-5 py-3 font-semibold text-white transition hover:bg-[var(--color-primary-600)] focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[var(--color-primary-700)]"
        >
          Reintentar
        </button>
      </main>
    </>
  );
}
