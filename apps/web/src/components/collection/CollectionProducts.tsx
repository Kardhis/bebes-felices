import { AgeSectionHeading } from "@/components/age/AgeSectionHeading";
import { ProductCard } from "@/components/age/ProductCard";
import type { CollectionProduct } from "@/lib/collection/getCollectionPage";

type CollectionProductsProps = {
  items: CollectionProduct[];
};

export function CollectionProducts({ items }: CollectionProductsProps) {
  if (items.length === 0) {
    return null;
  }

  return (
    <section
      id="productos"
      className="age-scroll-target bg-white"
      aria-labelledby="productos-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="productos-heading"
          title="Opciones revisadas"
          description="Fichas editoriales del catálogo. El botón de Amazon solo aparece cuando el enlace está validado."
        />
        <div className="mt-10 grid gap-5 sm:grid-cols-2 lg:grid-cols-3">
          {items.map((item) => (
            <ProductCard key={item.href} product={item} />
          ))}
        </div>
      </div>
    </section>
  );
}
