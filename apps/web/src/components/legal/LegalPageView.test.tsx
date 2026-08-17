import { render, screen, within } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { getLegalPage, LEGAL_LINKS } from "@/lib/legal/legalPages";
import { LegalPageView } from "./LegalPageView";

describe("LegalPageView", () => {
  it("renders an accessible legal document with shared navigation", () => {
    const page = getLegalPage("aviso-legal");
    render(<LegalPageView page={page} />);

    expect(screen.getByRole("main")).toBeInTheDocument();
    expect(screen.getByRole("article")).toBeInTheDocument();
    expect(screen.getByRole("navigation", { name: "Migas de pan" })).toBeInTheDocument();

    const index = screen.getByRole("navigation", { name: "Índice de la página" });
    expect(within(index).getAllByRole("link")).toHaveLength(page.sections.length);

    expect(screen.getAllByRole("heading", { level: 1 })).toHaveLength(1);
    expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent(page.label);
    expect(screen.getAllByRole("heading", { level: 2 })).toHaveLength(
      page.sections.length + 3,
    );

    expect(screen.getByText(/última actualización:/i)).toBeInTheDocument();
    expect(screen.getByText("17 de agosto de 2026")).toHaveAttribute(
      "datetime",
      page.updatedAt,
    );
    expect(
      screen.getAllByRole("link", { name: "davidcastelcastells@gmail.com" })[0],
    ).toHaveAttribute("href", "mailto:davidcastelcastells@gmail.com");

    for (const legalLink of LEGAL_LINKS) {
      expect(
        screen.getAllByRole("link", { name: legalLink.label }).at(-1),
      ).toHaveAttribute("href", legalLink.href.replace(/\/$/, ""));
    }
  });

  it("marks external legal references safely", () => {
    render(<LegalPageView page={getLegalPage("politica-privacidad")} />);

    const aepdLink = screen.getByRole("link", {
      name: "Agencia Española de Protección de Datos",
    });
    expect(aepdLink).toHaveAttribute("target", "_blank");
    expect(aepdLink).toHaveAttribute("rel", "noopener noreferrer");
  });
});
