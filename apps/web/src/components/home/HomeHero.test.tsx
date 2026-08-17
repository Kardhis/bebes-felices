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
        affiliationShortNotice="Algunos enlaces son de afiliado."
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
  });
});
