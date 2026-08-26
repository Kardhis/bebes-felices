import Image from "next/image";
import Link from "next/link";

type HomeHeroProps = {
  brand: string;
  h1: string;
  valueProposition: string;
  primaryCtaLabel: string;
  primaryCtaHref: string;
  secondaryCtaLabel: string;
  secondaryCtaHref: string;
  imageUrl: string;
  imageAlt: string;
};

export function HomeHero({
  brand,
  h1,
  valueProposition,
  primaryCtaLabel,
  primaryCtaHref,
  secondaryCtaLabel,
  secondaryCtaHref,
  imageUrl,
  imageAlt,
}: HomeHeroProps) {
  return (
    <section className="relative min-h-[88vh] overflow-hidden bg-[var(--color-primary-700)]">
      <Image
        src={imageUrl}
        alt={imageAlt}
        fill
        priority
        sizes="100vw"
        className="animate-kenburns object-cover"
      />
      <div
        className="absolute inset-0 bg-gradient-to-r from-[rgba(15,45,48,0.82)] via-[rgba(15,45,48,0.55)] to-[rgba(15,45,48,0.25)]"
        aria-hidden
      />

      <div className="relative mx-auto flex min-h-[88vh] max-w-6xl flex-col justify-end px-4 pb-16 pt-28 sm:px-6 sm:pb-20">
        <p className="animate-fade-up text-sm font-bold uppercase tracking-[0.18em] text-white/85">
          {brand}
        </p>
        <h1 className="animate-fade-up-delay mt-5 max-w-3xl font-[family-name:var(--font-nunito-sans)] text-4xl font-extrabold leading-tight tracking-tight text-white sm:text-5xl md:text-6xl">
          {h1}
        </h1>
        <p className="animate-fade-up-delay-2 mt-4 max-w-xl text-base leading-relaxed text-white/90 sm:text-lg">
          {valueProposition}
        </p>

        <div className="animate-fade-up-delay-2 mt-8 flex flex-wrap gap-3">
          <Link
            href={primaryCtaHref}
            className="rounded-xl bg-[var(--color-accent-500)] px-5 py-3 text-sm font-semibold text-[var(--color-text)] shadow-md transition hover:bg-[var(--color-accent-600)]"
          >
            {primaryCtaLabel}
          </Link>
          <Link
            href={secondaryCtaHref}
            className="rounded-xl bg-[var(--color-primary-500)] px-5 py-3 text-sm font-semibold text-[var(--color-text)] shadow-md transition hover:bg-[var(--color-primary-600)]"
          >
            {secondaryCtaLabel}
          </Link>
        </div>
      </div>
    </section>
  );
}
