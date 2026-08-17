import { SITE_URL } from "@/lib/seo/metadata";
import { LEGAL_LINKS } from "@/lib/legal/legalPages";

export const INFO_UPDATED_AT = "2026-08-17";

export const INFO_PAGE_SLUGS = ["quienes-somos", "contacto"] as const;

export type InfoPageSlug = (typeof INFO_PAGE_SLUGS)[number];

export type InfoTextPart = {
  text: string;
  href?: string;
  external?: boolean;
};

export type InfoContentBlock =
  | {
      type: "paragraph";
      parts: InfoTextPart[];
    }
  | {
      type: "list";
      items: InfoTextPart[][];
    };

export type InfoSection = {
  id: string;
  title: string;
  blocks: InfoContentBlock[];
};

export type InfoPage = {
  slug: InfoPageSlug;
  label: string;
  title: string;
  metaDescription: string;
  canonicalUrl: string;
  kicker: string;
  introduction: string;
  updatedAt: string;
  sections: InfoSection[];
};

const canonicalBase = SITE_URL.replace(/\/$/, "");

const text = (value: string): InfoTextPart[] => [{ text: value }];

const paragraph = (...parts: InfoTextPart[]): InfoContentBlock => ({
  type: "paragraph",
  parts,
});

const list = (...items: InfoTextPart[][]): InfoContentBlock => ({
  type: "list",
  items,
});

const infoPages: Record<InfoPage["slug"], InfoPage> = {
  "quienes-somos": {
    slug: "quienes-somos",
    label: "Quiénes somos",
    title: "Quiénes somos | BebesFelices",
    metaDescription:
      "Propósito editorial, responsable y criterios de BebesFelices para elegir juguetes y regalos para niños de 3 a 5 años.",
    canonicalUrl: `${canonicalBase}/quienes-somos/`,
    kicker: "Sobre BebesFelices",
    introduction:
      "BebesFelices es una web editorial para personas adultas que buscan elegir juguetes, regalos y productos para niños de 3 a 5 años con criterios claros y transparencia sobre afiliación.",
    updatedAt: INFO_UPDATED_AT,
    sections: [
      {
        id: "proposito",
        title: "Qué hacemos",
        blocks: [
          paragraph(
            ...text(
              "Publicamos guías, comparativas y análisis sobre productos para la infancia. No vendemos directamente: algunos enlaces llevan a Amazon España, donde se tramita la compra.",
            ),
          ),
          paragraph(
            ...text(
              "Priorizamos edad recomendada, seguridad, utilidad en casa y durabilidad. No inventamos puntuaciones, precios ni valoraciones que no podamos sostener.",
            ),
          ),
        ],
      },
      {
        id: "responsable",
        title: "Responsable editorial",
        blocks: [
          list(
            text("Titular: David Castel Castells."),
            text("NIF: 44204902Y."),
            text(
              "Domicilio: Carrer Mossèn Amadeu Oller, 36, 08014 Barcelona, España.",
            ),
          ),
        ],
      },
      {
        id: "metodologia",
        title: "Cómo trabajamos",
        blocks: [
          paragraph(
            ...text("Las selecciones se basan en criterios explícitos descritos en "),
            {
              text: "Cómo analizamos en Bebes Felices",
              href: "/como-analizamos/",
            },
            ...text(
              ". Distinguimos investigación editorial, opiniones de compradores y límites de lo que podemos verificar.",
            ),
          ),
          paragraph(
            ...text(
              "Las páginas por edad actúan como hub: desde ahí puedes ir a categorías, comparativas, regalos o análisis con el contexto adecuado.",
            ),
          ),
        ],
      },
      {
        id: "afiliacion",
        title: "Transparencia de afiliación",
        blocks: [
          paragraph(
            ...text(
              "BebesFelices participa en el Programa de Afiliados de Amazon. Algunos enlaces pueden ser de afiliado; eso no cambia el precio para ti. Puedes leer el detalle en ",
            ),
            {
              text: "Información sobre afiliación",
              href: "/informacion-afiliacion/",
            },
            ...text("."),
          ),
        ],
      },
    ],
  },
  contacto: {
    slug: "contacto",
    label: "Contacto",
    title: "Contacto | BebesFelices",
    metaDescription:
      "Correo de contacto de BebesFelices para consultas editoriales, legales o técnicas sobre la web.",
    canonicalUrl: `${canonicalBase}/contacto/`,
    kicker: "Contacto",
    introduction:
      "Puedes escribirnos por correo electrónico para consultas sobre el contenido editorial, avisos legales o el funcionamiento de la web. No usamos formularios ni recogemos mensajes en esta página.",
    updatedAt: INFO_UPDATED_AT,
    sections: [
      {
        id: "correo",
        title: "Correo electrónico",
        blocks: [
          list([
            { text: "Correo: " },
            {
              text: "davidcastelcastells@gmail.com",
              href: "mailto:davidcastelcastells@gmail.com",
            },
            { text: "." },
          ]),
        ],
      },
      {
        id: "finalidad",
        title: "Para qué puedes escribir",
        blocks: [
          list(
            text("Consultas sobre guías, comparativas o análisis publicados."),
            text("Avisos sobre enlaces rotos, errores editoriales o accesibilidad."),
            text("Consultas relacionadas con privacidad, cookies o condiciones de uso."),
          ),
        ],
      },
      {
        id: "privacidad",
        title: "Privacidad",
        blocks: [
          paragraph(
            ...text(
              "Si nos escribes por correo, trataremos tu dirección y el contenido del mensaje para responder. No usamos esta página para crear perfiles ni enviar newsletters. Consulta la ",
            ),
            {
              text: "Política de privacidad",
              href: "/politica-privacidad/",
            },
            ...text(" para más detalle."),
          ),
        ],
      },
    ],
  },
};

export function getInfoPage(slug: InfoPageSlug): InfoPage {
  return infoPages[slug];
}

export const INFO_LINKS = INFO_PAGE_SLUGS.map((slug) => ({
  label: infoPages[slug].label,
  href: `/${slug}/`,
}));

export { LEGAL_LINKS };
