import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { getInfoPage, LEGAL_LINKS } from "@/lib/info/infoPages";
import { InfoPageView } from "./InfoPageView";

describe("InfoPageView", () => {
  it("renders the about page with shared navigation", () => {
    const page = getInfoPage("quienes-somos");
    render(<InfoPageView page={page} />);

    expect(screen.getByRole("main")).toBeInTheDocument();
    expect(screen.getByRole("navigation", { name: "Migas de pan" })).toBeInTheDocument();
    expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent("Quiénes somos");
    expect(
      screen.getByRole("link", { name: "Cómo analizamos en Bebes Felices" }),
    ).toHaveAttribute("href", "/como-analizamos");
  });

  it("renders the contact page with a mailto link and no form", () => {
    const page = getInfoPage("contacto");
    render(<InfoPageView page={page} />);

    expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent("Contacto");
    expect(
      screen.getByRole("link", { name: "davidcastelcastells@gmail.com" }),
    ).toHaveAttribute("href", "mailto:davidcastelcastells@gmail.com");
    expect(screen.queryByRole("form")).not.toBeInTheDocument();

    for (const legalLink of LEGAL_LINKS) {
      expect(
        screen.getAllByRole("link", { name: legalLink.label }).at(-1),
      ).toHaveAttribute("href", legalLink.href.replace(/\/$/, ""));
    }
  });
});
