import { expect, test } from "@playwright/test";

test("renders the Home and navigates through its main sections", async ({ page }) => {
  await page.goto("/");

  await expect(
    page.getByRole("heading", {
      level: 1,
      name: /productos, juguetes y regalos/i,
    }),
  ).toBeVisible();
  await expect(page.getByRole("main")).toHaveAttribute("id", "contenido-principal");
  await expect(page.locator("#por-edad")).toBeVisible();
  await expect(page.locator("#categorias")).toBeVisible();
  await expect(page.locator("#guias")).toBeVisible();
  await expect(page.locator("#comparativas")).toBeVisible();

  await page.getByRole("link", { name: "Educativos" }).first().click();
  await expect(page).toHaveURL(/\/juguetes-educativos\/$/);
  await expect(page.getByRole("heading", { level: 1 })).toBeVisible();
});

test("mobile menu is keyboard accessible", async ({ page, isMobile }) => {
  test.skip(!isMobile, "Mobile navigation is only rendered below the desktop breakpoint.");
  await page.goto("/");
  await page.waitForLoadState("networkidle");

  const trigger = page.getByRole("button", { name: "Menú" });
  await trigger.click();
  await expect(page.getByRole("navigation", { name: "Menú móvil" })).toBeVisible();
  await page.keyboard.press("Escape");
  await expect(page.getByRole("navigation", { name: "Menú móvil" })).toBeHidden();
  await expect(trigger).toBeFocused();
});
