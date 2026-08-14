import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { ProductAnalysisBody } from "./ProductAnalysisBody";

const baseProps = {
  category: "Movimiento",
  ageRange: "3-4 años",
  forWhom: "Primer movimiento de pie",
  editorialSummary: "Un patinete estable para iniciarse.",
  pros: ["Base de tres ruedas"],
  cons: ["No sustituye a la bici sin pedales"],
  safetyNotes: ["Casco homologado"],
  buyingChecks: ["Altura de manillar"],
  affiliateHref: null as string | null,
};

describe("ProductAnalysisBody", () => {
  it("does not render a fake Amazon button when the affiliate link is missing", () => {
    render(<ProductAnalysisBody {...baseProps} />);

    expect(
      screen.getByText(/enlace a amazon disponible próximamente/i),
    ).toBeInTheDocument();
    expect(
      screen.queryByRole("link", { name: /ver en amazon/i }),
    ).not.toBeInTheDocument();
  });

  it("renders a sponsored Amazon button when the affiliate link is validated", () => {
    render(
      <ProductAnalysisBody
        {...baseProps}
        affiliateHref="https://www.amazon.es/dp/EXAMPLE"
      />,
    );

    const button = screen.getByRole("link", { name: /ver en amazon/i });
    expect(button).toHaveAttribute("href", "https://www.amazon.es/dp/EXAMPLE");
    expect(button).toHaveAttribute("rel", expect.stringContaining("sponsored"));
  });
});
