import { fireEvent, render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { SiteHeader } from "./SiteHeader";

describe("SiteHeader", () => {
  it("uses supplied category navigation", () => {
    render(
      <SiteHeader
        variant="inner"
        categoryItems={[{ label: "Categoría dinámica", href: "/dinamica/" }]}
      />,
    );

    expect(screen.getAllByRole("link", { name: "Categoría dinámica" })[0]).toHaveAttribute(
      "href",
      "/dinamica",
    );
    expect(screen.queryByRole("link", { name: "Metodología" })).not.toBeInTheDocument();
    expect(screen.queryByRole("link", { name: "Quiénes somos" })).not.toBeInTheDocument();
  });

  it("opens with focus inside, closes with Escape and restores focus", () => {
    render(<SiteHeader variant="inner" />);
    const trigger = screen.getByRole("button", { name: "Menú" });

    fireEvent.click(trigger);
    expect(screen.getByRole("navigation", { name: "Menú móvil" })).toBeInTheDocument();
    expect(document.activeElement).toHaveAttribute("href", "/#por-edad");

    fireEvent.keyDown(document, { key: "Escape" });
    expect(screen.queryByRole("navigation", { name: "Menú móvil" })).not.toBeInTheDocument();
    expect(trigger).toHaveFocus();
  });
});
