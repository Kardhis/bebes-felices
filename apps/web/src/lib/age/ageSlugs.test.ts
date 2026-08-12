import { describe, expect, it } from "vitest";
import { AGE_SLUGS, isAgeSlug } from "./ageSlugs";

describe("ageSlugs", () => {
  it("exposes exactly the three supported ages", () => {
    expect(AGE_SLUGS).toEqual(["3-anos", "4-anos", "5-anos"]);
  });

  it.each(AGE_SLUGS)("treats %s as a valid age slug", (slug) => {
    expect(isAgeSlug(slug)).toBe(true);
  });

  it.each(["2-anos", "6-anos", "3-anios", "", "3-Anos"])(
    "rejects %s as an invalid age slug",
    (value) => {
      expect(isAgeSlug(value)).toBe(false);
    },
  );
});
