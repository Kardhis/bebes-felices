import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { ProductCard } from "./ProductCard";

const baseProduct = {
  title: "Bicicleta sin pedales básica",
  category: "Movimiento",
  reason: "Cuadro ligero y sillín regulable.",
  ageRange: "3-5 años",
  href: "/analisis/bici-sin-pedales-basica/",
  affiliateHref: null as string | null,
};

describe("ProductCard", () => {
  it("shows the internal analysis link and no Amazon CTA when there is no validated affiliate link", () => {
    render(<ProductCard product={baseProduct} />);

    expect(screen.getByText(baseProduct.title)).toBeInTheDocument();
    expect(screen.queryByRole("link", { name: /ver en amazon/i })).not.toBeInTheDocument();

    const analysisLink = screen.getByRole("link", { name: /ver análisis completo/i });
    // next/link normaliza la barra final fuera del contexto de la app real.
    expect(analysisLink.getAttribute("href")).toBe(baseProduct.href.replace(/\/$/, ""));
  });

  it("renders the sponsored Amazon button when a validated affiliate link is present", () => {
    render(
      <ProductCard
        product={{ ...baseProduct, affiliateHref: "https://www.amazon.es/dp/EXAMPLE" }}
      />,
    );

    const affiliateButton = screen.getByRole("link", { name: /ver en amazon/i });
    expect(affiliateButton).toHaveAttribute("href", "https://www.amazon.es/dp/EXAMPLE");
    expect(affiliateButton).toHaveAttribute("rel", expect.stringContaining("sponsored"));
    expect(affiliateButton).toHaveAttribute("rel", expect.stringContaining("nofollow"));
    expect(affiliateButton).toHaveAttribute("target", "_blank");
  });
});
