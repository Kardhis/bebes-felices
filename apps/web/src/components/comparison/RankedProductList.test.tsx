import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { RankedProductList } from "./RankedProductList";

const entry = {
  rank: 1,
  productId: "bici-prueba",
  title: "Bicicleta de prueba",
  bestFor: "Mejor opción general",
  editorialSummary: "Una selección equilibrada.",
  pros: ["Ligera"],
  cons: ["Sin freno"],
  ageRange: "3-5 años",
  criteriaNotes: [{ criterion: "Peso", note: "Fácil de manejar" }],
  affiliateHref: null as string | null,
};

describe("RankedProductList", () => {
  it("does not render an Amazon CTA without a validated link", () => {
    render(<RankedProductList entries={[entry]} />);

    expect(screen.getByText(entry.title)).toBeInTheDocument();
    expect(
      screen.queryByRole("link", { name: /ver en amazon/i }),
    ).not.toBeInTheDocument();
  });

  it("renders a sponsored CTA when the backend provides a link", () => {
    render(
      <RankedProductList
        entries={[
          {
            ...entry,
            affiliateHref: "https://www.amazon.es/dp/B004MW55Z2?tag=test-21",
          },
        ]}
      />,
    );

    const link = screen.getByRole("link", { name: /ver en amazon/i });
    expect(link).toHaveAttribute("rel", expect.stringContaining("sponsored"));
    expect(link).toHaveAttribute("target", "_blank");
  });
});
