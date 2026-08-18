import { fireEvent, render, screen } from "@testing-library/react";
import { beforeEach, describe, expect, it, vi } from "vitest";
import { SiteHeader } from "./SiteHeader";

const { mockPathname } = vi.hoisted(() => ({
  mockPathname: vi.fn(() => "/"),
}));

vi.mock("next/navigation", () => ({
  usePathname: mockPathname,
}));

describe("SiteHeader", () => {
  beforeEach(() => {
    mockPathname.mockReturnValue("/");
  });

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

  it("highlights the menu item for the current page", () => {
    mockPathname.mockReturnValue("/movimiento/");

    render(<SiteHeader variant="inner" />);

    expect(screen.getAllByRole("link", { name: "Movimiento" })[0]).toHaveAttribute(
      "aria-current",
      "page",
    );
    expect(screen.getAllByRole("link", { name: "Autonomía" })[0]).not.toHaveAttribute(
      "aria-current",
    );
  });

  it("highlights Contacto on its page", () => {
    mockPathname.mockReturnValue("/contacto/");

    render(<SiteHeader variant="inner" />);

    expect(screen.getAllByRole("link", { name: "Contacto" })[0]).toHaveAttribute(
      "aria-current",
      "page",
    );
  });
});
