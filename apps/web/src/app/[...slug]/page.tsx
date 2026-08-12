import type { Metadata } from "next";
import Link from "next/link";

type Props = {
  params: Promise<{ slug: string[] }>;
};

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { slug } = await params;
  const path = `/${slug.join("/")}/`;
  return {
    title: `En construcción | BebesFelices`,
    description: `La página ${path} todavía está en construcción.`,
    robots: {
      index: false,
      follow: false,
    },
  };
}

export default async function UnderConstructionPage({ params }: Props) {
  const { slug } = await params;
  const path = `/${slug.join("/")}/`;

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

      <main className="mx-auto flex w-full max-w-xl flex-1 flex-col justify-center px-4 py-16 sm:px-6">
        <p className="text-sm font-semibold uppercase tracking-wide text-[var(--color-secondary-600)]">
          En construcción
        </p>
        <h1 className="mt-3 font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold text-[var(--color-text)]">
          Esta página aún no está lista
        </h1>
        <p className="mt-4 text-[var(--color-text-secondary)]">
          Estamos preparando el contenido de{" "}
          <span className="font-medium text-[var(--color-text)]">{path}</span>.
          Mientras tanto, puedes volver al inicio para explorar por edad o
          categoría.
        </p>
        <div className="mt-8">
          <Link
            href="/"
            className="inline-flex rounded-xl bg-[var(--color-primary-700)] px-5 py-3 text-sm font-semibold text-white transition hover:bg-[var(--color-primary-600)]"
          >
            Volver a la home
          </Link>
        </div>
      </main>
    </div>
  );
}
