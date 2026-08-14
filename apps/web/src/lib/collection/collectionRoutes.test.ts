import { describe, expect, it } from "vitest";
import {
  collectionsForPrefix,
  isCollectionSlug,
  isCollectionSlugForPrefix,
} from "./collectionRoutes";

describe("collectionRoutes", () => {
  it("maps each prefix to the slugs used by the 3-year hub", () => {
    expect(collectionsForPrefix("juguetes-educativos")).toEqual([
      "juegos-montessori",
      "puzles",
    ]);
    expect(collectionsForPrefix("movimiento")).toEqual(["patinetes"]);
    expect(isCollectionSlugForPrefix("autonomia", "vajilla-infantil")).toBe(
      true,
    );
    expect(isCollectionSlug("sostenibles")).toBe(true);
    expect(isCollectionSlug("ideas-regalo-3-anos")).toBe(true);
  });

  it("rejects a valid slug on the wrong prefix", () => {
    expect(isCollectionSlugForPrefix("movimiento", "puzles")).toBe(false);
    expect(isCollectionSlug("bicicletas-sin-pedales")).toBe(false);
  });
});
