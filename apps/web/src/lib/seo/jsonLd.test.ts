import { describe, expect, it } from "vitest";
import {
  buildArticleSchema,
  buildBreadcrumbListSchema,
  buildCollectionPageSchema,
  buildFaqPageSchema,
  buildItemListSchema,
} from "./jsonLd";

const SITE_URL = "https://bebesfelices.es";

describe("buildBreadcrumbListSchema", () => {
  it("builds a BreadcrumbList with absolute URLs and 1-based positions", () => {
    const schema = buildBreadcrumbListSchema(
      [
        { label: "Inicio", href: "/" },
        { label: "Por edad", href: "/#por-edad" },
        { label: "3 años", href: `${SITE_URL}/por-edad/3-anos/` },
      ],
      SITE_URL,
    );

    expect(schema["@type"]).toBe("BreadcrumbList");
    expect(schema.itemListElement).toHaveLength(3);
    expect(schema.itemListElement[0]).toEqual({
      "@type": "ListItem",
      position: 1,
      name: "Inicio",
      item: `${SITE_URL}/`,
    });
    expect(schema.itemListElement[2].item).toBe(`${SITE_URL}/por-edad/3-anos/`);
  });
});

describe("buildCollectionPageSchema", () => {
  it("only includes visible, real fields without inventing ratings or offers", () => {
    const schema = buildCollectionPageSchema({
      url: `${SITE_URL}/por-edad/3-anos/`,
      name: "Mejores juguetes para niños de 3 años",
      description: "Descripción real de la página.",
      dateModified: "2026-08-11",
    });

    expect(schema["@type"]).toBe("CollectionPage");
    expect(schema).not.toHaveProperty("aggregateRating");
    expect(schema).not.toHaveProperty("offers");
  });
});

describe("buildItemListSchema", () => {
  it("maps internal links to ListItem entries without price or availability", () => {
    const schema = buildItemListSchema(
      [{ title: "Bicicleta sin pedales básica", href: "/analisis/bici-sin-pedales-basica/" }],
      SITE_URL,
    );

    expect(schema["@type"]).toBe("ItemList");
    expect(schema.itemListElement[0]).toEqual({
      "@type": "ListItem",
      position: 1,
      name: "Bicicleta sin pedales básica",
      url: `${SITE_URL}/analisis/bici-sin-pedales-basica/`,
    });
  });
});

describe("buildFaqPageSchema", () => {
  it("builds an FAQPage with one Question/Answer pair per item", () => {
    const schema = buildFaqPageSchema([
      { question: "¿Es adecuado a los 3 años?", answer: "Sí, con supervisión." },
    ]);

    expect(schema["@type"]).toBe("FAQPage");
    expect(schema.mainEntity).toEqual([
      {
        "@type": "Question",
        name: "¿Es adecuado a los 3 años?",
        acceptedAnswer: { "@type": "Answer", text: "Sí, con supervisión." },
      },
    ]);
  });
});

describe("buildArticleSchema", () => {
  it("builds an Article without offers or ratings", () => {
    const schema = buildArticleSchema({
      url: `${SITE_URL}/guias/habilidades-3-anos/`,
      name: "Qué habilidades desarrolla un niño de 3 años",
      description: "Desarrollo esperable a esta edad.",
      datePublished: "2026-08-14",
      dateModified: "2026-08-14",
    });

    expect(schema["@type"]).toBe("Article");
    expect(schema.headline).toContain("habilidades");
    expect(schema).not.toHaveProperty("aggregateRating");
    expect(schema).not.toHaveProperty("offers");
  });
});
