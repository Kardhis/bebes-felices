import { describe, expect, it } from "vitest";
import { buildPageMetadata } from "./metadata";

describe("buildPageMetadata", () => {
  it("includes canonical URL and Open Graph fields", () => {
    const metadata = buildPageMetadata({
      title: "Puzles de piezas grandes | Bebes Felices",
      metaDescription: "Motricidad fina para 3 años.",
      canonicalUrl: "https://bebesfelices.es/juguetes-educativos/puzles/",
    });

    expect(metadata.alternates?.canonical).toBe(
      "https://bebesfelices.es/juguetes-educativos/puzles/",
    );
    expect(metadata.openGraph?.url).toBe(
      "https://bebesfelices.es/juguetes-educativos/puzles/",
    );
    expect(metadata.openGraph?.locale).toBe("es_ES");
    expect(metadata.openGraph?.images).toBeDefined();
    expect(metadata.twitter?.card).toBe("summary_large_image");
    expect(metadata.openGraph).not.toHaveProperty("offers");
  });
});
