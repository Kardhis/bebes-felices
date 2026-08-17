import { Fragment } from "react";
import Link from "next/link";
import { Breadcrumbs } from "@/components/age/Breadcrumbs";
import { SiteFooter } from "@/components/home/SiteFooter";
import { SiteHeader } from "@/components/home/SiteHeader";
import {
  LEGAL_LINKS,
  type LegalContentBlock,
  type LegalPage,
  type LegalTextPart,
} from "@/lib/legal/legalPages";

type LegalPageViewProps = {
  page: LegalPage;
};

function LegalText({ parts }: { parts: LegalTextPart[] }) {
  return parts.map((part, index) => {
    if (!part.href) {
      return <Fragment key={`${part.text}-${index}`}>{part.text}</Fragment>;
    }

    if (part.external) {
      return (
        <a
          key={`${part.href}-${index}`}
          href={part.href}
          target="_blank"
          rel="noopener noreferrer"
          className="font-medium text-[var(--color-primary-700)] underline decoration-[var(--color-primary-100)] decoration-2 underline-offset-2 hover:decoration-[var(--color-primary-600)]"
        >
          {part.text}
        </a>
      );
    }

    if (part.href.startsWith("mailto:")) {
      return (
        <a
          key={`${part.href}-${index}`}
          href={part.href}
          className="font-medium text-[var(--color-primary-700)] underline decoration-[var(--color-primary-100)] decoration-2 underline-offset-2 hover:decoration-[var(--color-primary-600)]"
        >
          {part.text}
        </a>
      );
    }

    return (
      <Link
        key={`${part.href}-${index}`}
        href={part.href}
        className="font-medium text-[var(--color-primary-700)] underline decoration-[var(--color-primary-100)] decoration-2 underline-offset-2 hover:decoration-[var(--color-primary-600)]"
      >
        {part.text}
      </Link>
    );
  });
}

function LegalBlock({ block }: { block: LegalContentBlock }) {
  if (block.type === "list") {
    return (
      <ul className="list-disc space-y-2 pl-6 marker:text-[var(--color-primary-600)]">
        {block.items.map((item, index) => (
          <li key={`${item.map((part) => part.text).join("")}-${index}`}>
            <LegalText parts={item} />
          </li>
        ))}
      </ul>
    );
  }

  return (
    <p>
      <LegalText parts={block.parts} />
    </p>
  );
}

function formatUpdatedAt(updatedAt: string) {
  return new Intl.DateTimeFormat("es-ES", {
    day: "numeric",
    month: "long",
    year: "numeric",
    timeZone: "UTC",
  }).format(new Date(`${updatedAt}T00:00:00Z`));
}

export function LegalPageView({ page }: LegalPageViewProps) {
  return (
    <>
      <SiteHeader variant="inner" />
      <main>
        <Breadcrumbs
          items={[
            { label: "Inicio", href: "/" },
            { label: page.label, href: `/${page.slug}/` },
          ]}
        />

        <header className="relative overflow-hidden bg-gradient-to-br from-[var(--color-primary-50)] via-white to-[var(--color-accent-100)]/50">
          <div
            className="pointer-events-none absolute -right-20 -top-24 h-72 w-72 rounded-full bg-[var(--color-primary-100)]/70 blur-3xl"
            aria-hidden
          />
          <div className="relative mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-18">
            <p className="text-xs font-semibold uppercase tracking-[0.16em] text-[var(--color-primary-700)]">
              {page.kicker}
            </p>
            <h1 className="mt-4 max-w-4xl font-[family-name:var(--font-nunito-sans)] text-3xl font-extrabold tracking-tight text-[var(--color-text)] sm:text-4xl">
              {page.label}
            </h1>
            <p className="mt-5 max-w-3xl text-base leading-7 text-[var(--color-text-secondary)] sm:text-lg">
              {page.introduction}
            </p>
            <p className="mt-6 text-sm text-[var(--color-text-muted)]">
              Última actualización:{" "}
              <time dateTime={page.updatedAt}>{formatUpdatedAt(page.updatedAt)}</time>
            </p>
          </div>
        </header>

        <article className="bg-white">
          <div className="mx-auto grid max-w-6xl gap-10 px-4 py-12 sm:px-6 lg:grid-cols-[16rem_minmax(0,1fr)] lg:py-16">
            <nav aria-label="Índice de la página" className="lg:sticky lg:top-24 lg:self-start">
              <p className="text-xs font-semibold uppercase tracking-wide text-[var(--color-text-muted)]">
                En esta página
              </p>
              <ol className="mt-4 space-y-2">
                {page.sections.map((section, index) => (
                  <li key={section.id}>
                    <a
                      href={`#${section.id}`}
                      className="text-sm font-medium leading-5 text-[var(--color-primary-700)] hover:underline"
                    >
                      {index + 1}. {section.title}
                    </a>
                  </li>
                ))}
              </ol>
            </nav>

            <div className="min-w-0 space-y-12">
              {page.sections.map((section) => (
                <section
                  key={section.id}
                  id={section.id}
                  className="age-scroll-target scroll-mt-24"
                >
                  <h2 className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold tracking-tight text-[var(--color-text)]">
                    {section.title}
                  </h2>
                  <div className="mt-4 space-y-4 text-base leading-7 text-[var(--color-text-secondary)]">
                    {section.blocks.map((block, index) => (
                      <LegalBlock
                        key={`${section.id}-${block.type}-${index}`}
                        block={block}
                      />
                    ))}
                  </div>
                </section>
              ))}
            </div>
          </div>
        </article>
      </main>
      <SiteFooter legalLinks={LEGAL_LINKS} updatedAt={page.updatedAt} />
    </>
  );
}
