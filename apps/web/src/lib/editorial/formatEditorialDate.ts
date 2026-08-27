export function formatEditorialDate(updatedAt: string) {
  const [year, month, day] = updatedAt.split("-");

  if (!year || !month || !day) {
    return updatedAt;
  }

  return `${day.padStart(2, "0")}/${month.padStart(2, "0")}/${year}`;
}

export const UPDATE_DATE_TRANSPARENCY_PREFIX =
  "Fecha de actualización en la página";

export function isUpdateDateTransparencyItem(text: string) {
  return text.startsWith(UPDATE_DATE_TRANSPARENCY_PREFIX);
}
