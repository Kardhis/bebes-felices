export const ANALYSIS_PRODUCT_IDS = [
  "juego-montessori-formas",
  "puzle-madera-animales",
  "patinete-3-ruedas",
  "torre-aprendizaje-madera",
  "set-vajilla-infantil",
  "kit-manualidades-natural",
  "set-construccion-magnetico",
  "bici-sin-pedales-basica",
  "juego-mesa-cooperativo",
] as const;

export type AnalysisProductId = (typeof ANALYSIS_PRODUCT_IDS)[number];

export function isAnalysisProductId(id: string): id is AnalysisProductId {
  return ANALYSIS_PRODUCT_IDS.includes(id as AnalysisProductId);
}
