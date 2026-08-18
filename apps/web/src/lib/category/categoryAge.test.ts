import { describe, expect, it } from "vitest";
import { categoryAgeHref, parseCategoryAge } from "./categoryAge";

describe("categoryAge", () => {
  it("reads 3, 4 and 5 from the query and ignores other values", () => {
    expect(parseCategoryAge(undefined)).toBe(3);
    expect(parseCategoryAge("3")).toBe(3);
    expect(parseCategoryAge("4")).toBe(4);
    expect(parseCategoryAge("5")).toBe(5);
    expect(parseCategoryAge("6")).toBe(3);
  });

  it("builds a category URL that keeps the selected age", () => {
    expect(categoryAgeHref("/juguetes-educativos/", 4)).toBe(
      "/juguetes-educativos/?edad=4",
    );
    expect(categoryAgeHref("/regalos", 5)).toBe("/regalos/?edad=5");
  });
});
