import { describe, expect, it } from "vitest";
import {
  getInfoPage,
  INFO_LINKS,
  INFO_UPDATED_AT,
} from "./infoPages";
import { buildPageMetadata } from "@/lib/seo/metadata";

describe("infoPages", () => {
  it("defines about and contact routes with canonical metadata", () => {
    expect(INFO_LINKS).toEqual([
      { label: "Quiénes somos", href: "/quienes-somos/" },
      { label: "Contacto", href: "/contacto/" },
    ]);

    for (const slug of ["quienes-somos", "contacto"] as const) {
      const page = getInfoPage(slug);
      expect(page.title).toContain("BebesFelices");
      expect(page.metaDescription.length).toBeGreaterThan(40);
      expect(page.updatedAt).toBe(INFO_UPDATED_AT);
      expect(page.canonicalUrl).toBe(`https://bebesfelices.es/${slug}/`);
      expect(buildPageMetadata(page).alternates?.canonical).toBe(page.canonicalUrl);
    }
  });

  it("does not include a contact form in the contact page content", () => {
    const page = getInfoPage("contacto");
    const text = page.sections
      .flatMap((section) => section.blocks)
      .flatMap((block) =>
        block.type === "paragraph" ? block.parts : block.items.flat(),
      )
      .map((part) => part.text)
      .join(" ");

    expect(text).toContain("davidcastelcastells@gmail.com");
    expect(text.toLowerCase()).not.toContain("formulario");
  });
});
