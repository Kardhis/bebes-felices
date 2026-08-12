import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { QuickNavigation } from "./QuickNavigation";

const items = [
  { label: "Selección destacada", anchor: "#seleccion-destacada" },
  { label: "Juguetes educativos", anchor: "#para-aprender" },
  { label: "Preguntas frecuentes", anchor: "#faq" },
];

describe("QuickNavigation", () => {
  it("renders one in-page anchor link per item, pointing at the right section", () => {
    render(<QuickNavigation items={items} />);

    for (const item of items) {
      const link = screen.getByRole("link", { name: item.label });
      expect(link).toHaveAttribute("href", item.anchor);
    }
  });

  it("exposes the navigation with an accessible label", () => {
    render(<QuickNavigation items={items} />);

    expect(screen.getByRole("navigation", { name: /navegación rápida/i })).toBeInTheDocument();
  });
});
