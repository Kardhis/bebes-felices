import { AgeSectionHeading } from "./AgeSectionHeading";
import { ProductCard } from "./ProductCard";

type FeaturedProduct = {
  title: string;
  category: string;
  reason: string;
  ageRange: string;
  href: string;
  affiliateHref: string | null;
};

type FeaturedSelectionProps = {
  items: FeaturedProduct[];
};

export function FeaturedSelection({ items }: FeaturedSelectionProps) {
  return (
    <section
      id="seleccion-destacada"
      className="age-scroll-target bg-white"
      aria-labelledby="seleccion-destacada-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="seleccion-destacada-heading"
          title="Selección destacada"
          description="Opciones revisadas editorialmente para esta edad. Cuando un enlace de Amazon esté validado, aparecerá el botón de compra."
        />
        <div className="mt-10 grid gap-5 sm:grid-cols-2 lg:grid-cols-3">
          {items.map((item) => (
            <ProductCard key={item.title} product={item} />
          ))}
        </div>
      </div>
    </section>
  );
}
