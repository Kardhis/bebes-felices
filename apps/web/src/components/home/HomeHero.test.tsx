import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { HomeHero } from "./HomeHero";

describe("HomeHero", () => {
  it("renders one primary heading, local image and both calls to action", () => {
    render(
      <HomeHero
        brand="Bebes Felices"
        h1="Productos para niños"
        valueProposition="Elegir con criterio"
        primaryCtaLabel="Buscar por edad"
        primaryCtaHref="/#por-edad"
        secondaryCtaLabel="Explorar categorías"
        secondaryCtaHref="/#categorias"
        imageUrl="/images/home-hero.jpg"
        imageAlt="Juguetes educativos"
      />,
    );

    expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent(
      "Productos para niños",
    );
    expect(screen.getByAltText("Juguetes educativos")).toHaveAttribute(
      "src",
      expect.stringContaining("home-hero.jpg"),
    );
    expect(screen.getByRole("link", { name: "Buscar por edad" })).toHaveAttribute(
      "href",
      "/#por-edad",
    );
    expect(screen.getByRole("link", { name: "Buscar por edad" })).toHaveClass(
      "bg-[var(--color-accent-500)]",
      "text-[var(--color-text)]",
    );
    expect(screen.getByRole("link", { name: "Explorar categorías" })).toHaveAttribute(
      "href",
      "/#categorias",
    );
    expect(screen.getByRole("link", { name: "Explorar categorías" })).toHaveClass(
      "bg-[var(--color-primary-500)]",
      "text-[var(--color-text)]",
    );
  });
});
