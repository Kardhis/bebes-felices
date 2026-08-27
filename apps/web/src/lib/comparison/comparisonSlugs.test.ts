import { describe, expect, it } from "vitest";
import {
  COMPARISON_SLUGS,
  isComparisonSlug,
} from "./comparisonSlugs";

describe("comparisonSlugs", () => {
  it("accepts every configured comparison", () => {
    for (const slug of COMPARISON_SLUGS) {
      expect(isComparisonSlug(slug)).toBe(true);
    }
    expect(isComparisonSlug("mejores-juguetes-stem-5-anos")).toBe(true);
  });

  it("rejects unknown comparisons", () => {
    expect(isComparisonSlug("mejores-patinetes-3-anos")).toBe(true);
    expect(isComparisonSlug("mejores-juegos-montessori-3-anos")).toBe(true);
    expect(isComparisonSlug("mejores-regalos-duraderos-3-anos")).toBe(true);
    expect(isComparisonSlug("mejores-juegos-simbolicos-3-anos")).toBe(true);
    expect(isComparisonSlug("mejores-elegir-regalo-edad-3-anos")).toBe(true);
    expect(isComparisonSlug("mejores-puzles-2-anos")).toBe(false);
  });
});
