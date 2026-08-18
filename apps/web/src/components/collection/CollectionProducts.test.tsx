import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { CollectionProducts } from "./CollectionProducts";

describe("CollectionProducts", () => {
  it("shows six reviewed products with their Amazon links", () => {
    const items = Array.from({ length: 6 }, (_, index) => ({
      title: `Producto ${index + 1}`,
      category: "Juego simbólico",
      reason: "Opción revisada de la categoría.",
      ageRange: "3-8 años",
      href: null as string | null,
      affiliateHref: `https://www.amazon.es/dp/B00000000${index}`,
      ctaLabel: null as string | null,
    }));

    render(<CollectionProducts items={items} />);

    expect(screen.getByRole("heading", { name: "Opciones revisadas" })).toBeInTheDocument();
    expect(screen.getAllByRole("article")).toHaveLength(6);

    const amazonLinks = screen.getAllByRole("link", { name: /ver en amazon/i });
    expect(amazonLinks).toHaveLength(6);
    amazonLinks.forEach((link, index) => {
      expect(link).toHaveAttribute("href", items[index].affiliateHref);
      expect(link).toHaveAttribute("rel", expect.stringContaining("sponsored"));
    });
  });
});
