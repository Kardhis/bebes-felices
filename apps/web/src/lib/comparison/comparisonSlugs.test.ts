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
  });

  it("rejects unknown comparisons", () => {
    expect(isComparisonSlug("mejores-patinetes-3-anos")).toBe(false);
  });
});
