import { describe, expect, it } from "vitest";

import {
  formatEditorialDate,
  isUpdateDateTransparencyItem,
} from "./formatEditorialDate";

describe("formatEditorialDate", () => {
  it("formats ISO dates as dd/MM/yyyy", () => {
    expect(formatEditorialDate("2026-08-14")).toBe("14/08/2026");
    expect(formatEditorialDate("2026-01-05")).toBe("05/01/2026");
  });

  it("identifies the update date transparency bullet", () => {
    expect(
      isUpdateDateTransparencyItem(
        "Fecha de actualización en la página (ver en el pie de página).",
      ),
    ).toBe(true);
    expect(isUpdateDateTransparencyItem("Aviso visible de Amazon Afiliados.")).toBe(
      false,
    );
  });
});
