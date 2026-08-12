import Link from "next/link";

export default function NotFound() {
  return (
    <div className="flex min-h-screen flex-col bg-[var(--color-bg-alt)]">
      <header className="border-b border-[var(--color-border)] bg-white px-4 py-4 sm:px-6">
        <Link
          href="/"
          className="font-[family-name:var(--font-nunito-sans)] text-lg font-extrabold text-[var(--color-primary-700)]"
        >
          BebesFelices
        </Link>
      </header>
      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col justify-center px-4 py-16">
        <p className="text-sm font-semibold text-[var(--color-secondary-600)]">404</p>
        <h1 className="mt-2 font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold text-[var(--color-text)]">
          Página no encontrada
        </h1>
        <p className="mt-3 text-[var(--color-text-secondary)]">
          La URL no existe o ha cambiado. Vuelve al inicio para explorar por edad
          o categoría.
        </p>
        <Link
          href="/"
          className="mt-8 inline-flex w-fit rounded-xl bg-[var(--color-primary-700)] px-5 py-3 text-sm font-semibold text-white hover:bg-[var(--color-primary-600)]"
        >
          Volver a la home
        </Link>
      </main>
    </div>
  );
}
