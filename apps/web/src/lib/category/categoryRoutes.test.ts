import { describe, expect, it } from "vitest";
import { collectionsForPrefix } from "@/lib/collection/collectionRoutes";
import {
  CATEGORY_PAGE_SLUGS,
  CATEGORY_PREFIXES,
  categoryPath,
  isCategoryPageSlug,
} from "./categoryRoutes";

describe("categoryRoutes", () => {
  it("defines the four published category indexes", () => {
    expect(CATEGORY_PAGE_SLUGS).toEqual([
      "juguetes-educativos",
      "movimiento",
      "autonomia",
      "regalos",
    ]);
    expect(CATEGORY_PREFIXES).toEqual(CATEGORY_PAGE_SLUGS);
  });

  it("maps each prefix to the same collection slugs as collectionRoutes", () => {
    expect(collectionsForPrefix("juguetes-educativos")).toEqual([
      "juegos-montessori",
      "puzles",
      "juegos-stem",
      "juegos-de-mesa",
      "juego-simbolico",
      "juguetes-sensoriales",
      "munecos-figuras-pequenos-mundos",
      "juguetes-musicales",
      "juguetes-construccion",
      "arte-manualidades",
      "causa-efecto-experimentacion",
      "lenguaje-lectoescritura",
      "matematicas-logica",
      "juegos-cooperativos-socioemocionales",
    ]);
    expect(collectionsForPrefix("movimiento")).toEqual([
      "patinetes",
      "bicicletas-sin-pedales",
    ]);
    expect(collectionsForPrefix("autonomia")).toEqual([
      "torres-de-aprendizaje",
      "vajilla-infantil",
    ]);
    expect(collectionsForPrefix("regalos")).toEqual([
      "ideas-regalo-3-anos",
      "ideas-regalo-4-anos",
      "ideas-regalo-5-anos",
    ]);
  });

  it("builds canonical paths with trailing slash", () => {
    expect(categoryPath("regalos")).toBe("/regalos/");
    expect(isCategoryPageSlug("regalos")).toBe(true);
    expect(isCategoryPageSlug("sostenibles")).toBe(false);
  });
});
