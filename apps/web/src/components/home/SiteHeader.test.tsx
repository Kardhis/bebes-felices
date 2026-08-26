import { fireEvent, render, screen, within } from "@testing-library/react";
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

  it("does not render a Buscar button in the navigation", () => {
    render(<SiteHeader />);

    expect(screen.queryByRole("link", { name: "Buscar" })).not.toBeInTheDocument();
  });

  it("puts Sostenibles second in desktop and mobile navigation", () => {
    render(<SiteHeader />);

    const desktopLinks = within(
      screen.getByRole("navigation", { name: "Navegación principal" }),
    ).getAllByRole("link");
    expect(desktopLinks[0]).toHaveAccessibleName("Por edad");
    expect(desktopLinks[1]).toHaveAccessibleName("Sostenibles");

    fireEvent.click(screen.getByRole("button", { name: "Menú" }));
    const mobileLinks = within(
      screen.getByRole("navigation", { name: "Menú móvil" }),
    ).getAllByRole("link");
    expect(mobileLinks[0]).toHaveAccessibleName("Por edad");
    expect(mobileLinks[1]).toHaveAccessibleName("Sostenibles");
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
