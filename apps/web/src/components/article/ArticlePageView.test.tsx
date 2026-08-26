import { fireEvent, render, screen, within } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import { ArticlePageView } from "./ArticlePageView";
import type { ArticlePageResponse } from "@/lib/article/getArticlePage";

const navigation = vi.hoisted(() => ({
  replace: vi.fn(),
}));

vi.mock("next/navigation", () => ({
  usePathname: () => "/guias/como-elegir-juguetes-por-edad/",
  useRouter: () => ({ replace: navigation.replace }),
}));

const page: ArticlePageResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/guias/como-elegir-juguetes-por-edad/",
    title: "Cómo elegir juguetes según la edad | Bebes Felices",
    metaDescription: "Criterios prácticos.",
  },
  status: "PUBLISHED",
  slug: "como-elegir-juguetes-por-edad",
  canonicalPath: "/guias/como-elegir-juguetes-por-edad/",
  breadcrumbs: [
    { label: "Inicio", href: "/" },
    { label: "Guías", href: "/guias/" },
    { label: "Cómo elegir juguetes según la edad", href: "/guias/como-elegir-juguetes-por-edad/" },
  ],
  header: {
    kicker: "Guía de compra",
    h1: "Cómo elegir juguetes según la edad",
    introductionParagraphs: ["Introducción de 3 años."],
  },
  sections: [
    {
      id: "edad-real",
      title: "Empieza por lo que ya hace, no solo por los años",
      paragraphs: ["Texto de 3 años sobre lo que ya hace."],
    },
  ],
  ageVariants: [
    {
      hubAge: 3,
      introductionParagraphs: ["Introducción de 3 años."],
      sections: [
        {
          id: "edad-real",
          title: "Empieza por lo que ya hace, no solo por los años",
          paragraphs: ["Texto de 3 años sobre lo que ya hace."],
        },
      ],
      faq: [{ question: "¿Qué debo evitar a los 3 años?", answer: "Piezas pequeñas." }],
      relatedLinks: [
        {
          title: "Juguetes y regalos para niños de 3 años",
          href: "/por-edad/3-anos/",
          description: "Hub de 3 años.",
        },
      ],
    },
    {
      hubAge: 4,
      introductionParagraphs: ["Introducción de 4 años."],
      sections: [
        {
          id: "edad-real",
          title: "Empieza por lo que ya hace, no solo por los años",
          paragraphs: ["Texto de 4 años sobre turnos cortos."],
        },
      ],
      faq: [{ question: "¿Qué debo evitar a los 4 años?", answer: "Reglamentos largos." }],
      relatedLinks: [
        {
          title: "Juguetes y regalos para niños de 4 años",
          href: "/por-edad/4-anos/",
          description: "Hub de 4 años.",
        },
      ],
    },
    {
      hubAge: 5,
      introductionParagraphs: ["Introducción de 5 años."],
      sections: [
        {
          id: "edad-real",
          title: "Empieza por lo que ya hace, no solo por los años",
          paragraphs: ["Texto de 5 años sobre planificar un reto."],
        },
      ],
      faq: [{ question: "¿Qué debo evitar a los 5 años?", answer: "Proyectos interminables." }],
      relatedLinks: [
        {
          title: "Juguetes y regalos para niños de 5 años",
          href: "/por-edad/5-anos/",
          description: "Hub de 5 años.",
        },
      ],
    },
  ],
  faq: [{ question: "¿Qué debo evitar a los 3 años?", answer: "Piezas pequeñas." }],
  relatedLinks: [],
  trustAuthority: {
    howWeSelect: "Criterios explícitos.",
    analysisCriteria: ["Edad"],
    editorialTransparency: ["Metodología"],
  },
  affiliation: {
    noticeText: "Aviso de afiliación.",
    shortNoticeText: "Afiliado Amazon.",
  },
  legalLinks: [{ label: "Aviso legal", href: "/aviso-legal/" }],
  author: { name: "Equipo", role: "Redacción" },
  publishedAt: "2026-08-14",
  updatedAt: "2026-08-14",
};

describe("ArticlePageView", () => {
  it("uses Guías breadcrumbs and switches En esta guía by age", () => {
    navigation.replace.mockClear();
    render(<ArticlePageView page={page} />);

    const breadcrumbs = screen.getByRole("navigation", { name: "Migas de pan" });
    expect(breadcrumbs).toBeInTheDocument();
    expect(
      within(breadcrumbs).getByRole("link", { name: "Guías" }),
    ).toHaveAttribute("href", "/guias");
    expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent(
      "Cómo elegir juguetes según la edad",
    );
    expect(screen.getByText("En esta guía")).toBeInTheDocument();
    expect(screen.getByText("Elige la edad")).toBeInTheDocument();
    expect(screen.getByRole("button", { name: "3 años" })).toHaveAttribute(
      "aria-pressed",
      "true",
    );
    expect(screen.getByRole("button", { name: "4 años" })).toHaveAttribute(
      "aria-pressed",
      "false",
    );
    expect(screen.getByText(/ves los criterios para 3 años/)).toBeInTheDocument();
    expect(
      screen.getByRole("link", {
        name: /1\. Empieza por lo que ya hace, no solo por los años/,
      }),
    ).toBeInTheDocument();
    expect(screen.getByText("Texto de 3 años sobre lo que ya hace.")).toBeInTheDocument();

    fireEvent.click(screen.getByRole("button", { name: "4 años" }));
    expect(navigation.replace).toHaveBeenCalledWith(
      "/guias/como-elegir-juguetes-por-edad/?edad=4",
      { scroll: false },
    );
    expect(screen.getByText("Texto de 4 años sobre turnos cortos.")).toBeInTheDocument();
    expect(screen.getByText(/ves los criterios para 4 años/)).toBeInTheDocument();
    expect(
      screen.queryByText("Texto de 3 años sobre lo que ya hace."),
    ).not.toBeInTheDocument();
    expect(screen.getByText("¿Qué debo evitar a los 4 años?")).toBeInTheDocument();
  });

  it("restores the age already selected in the URL", () => {
    render(<ArticlePageView page={page} initialAge={5} />);

    expect(screen.getByRole("button", { name: "5 años" })).toHaveAttribute(
      "aria-pressed",
      "true",
    );
    expect(
      screen.getByText("Texto de 5 años sobre planificar un reto."),
    ).toBeInTheDocument();
  });
});
