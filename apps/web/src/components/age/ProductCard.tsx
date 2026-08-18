import Link from "next/link";
import { AffiliateButton } from "./AffiliateButton";

type FeaturedProduct = {
  title: string;
  category: string;
  reason: string;
  ageRange: string;
  href: string | null;
  affiliateHref: string | null;
  ctaLabel: string | null;
};

type ProductCardProps = {
  product: FeaturedProduct;
};

export function ProductCard({ product }: ProductCardProps) {
  return (
    <article className="group flex h-full flex-col justify-between rounded-2xl border border-[var(--color-border)] bg-white p-5 shadow-sm transition hover:border-[var(--color-primary-200)] hover:shadow-md">
      <div>
        <div className="flex flex-wrap items-center gap-2">
          <span className="rounded-md bg-[var(--color-secondary-100)] px-2 py-0.5 text-xs font-semibold text-[var(--color-secondary-600)]">
            {product.category}
          </span>
          <span className="text-xs text-[var(--color-text-muted)]">{product.ageRange}</span>
        </div>
        <h3 className="mt-3 font-[family-name:var(--font-nunito-sans)] text-lg font-bold leading-snug text-[var(--color-text)]">
          {product.title}
        </h3>
        <p className="mt-2 text-sm leading-relaxed text-[var(--color-text-secondary)]">
          {product.reason}
        </p>
      </div>
      <div className="mt-5 flex flex-col gap-2 border-t border-[var(--color-border)] pt-4 sm:flex-row sm:flex-wrap sm:items-center sm:gap-3">
        {product.affiliateHref ? (
          <AffiliateButton href={product.affiliateHref} />
        ) : (
          <span className="text-xs leading-relaxed text-[var(--color-text-muted)]">
            Enlace a Amazon disponible próximamente.
          </span>
        )}
        {product.href && product.ctaLabel && (
          <Link
            href={product.href}
            className="text-sm font-semibold text-[var(--color-primary-700)] transition group-hover:underline"
          >
            {product.ctaLabel} →
          </Link>
        )}
      </div>
    </article>
  );
}
