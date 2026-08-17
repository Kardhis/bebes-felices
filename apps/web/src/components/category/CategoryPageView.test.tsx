import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import { CategoryPageView } from "./CategoryPageView";
import type { CategoryPageResponse } from "@/lib/category/getCategoryPage";

const page: CategoryPageResponse = {
  seo: {
    canonicalUrl: "https://bebesfelices.es/regalos/",
    title: "Ideas de regalo por edad | Bebes Felices",
    metaDescription: "Selecciones por edad y ocasión.",
  },
  status: "PUBLISHED",
  slug: "regalos",
  canonicalPath: "/regalos/",
  breadcrumbs: [
    { label: "Inicio", href: "/" },
    { label: "Regalos", href: "/regalos/" },
  ],
  header: {
    kicker: "Regalos",
    h1: "Ideas de regalo por edad para niños de 3 a 5 años",
    introductionParagraphs: ["Introducción de prueba."],
  },
  childCollections: [
    {
      title: "Ideas de regalo para niños de 3 años",
      href: "/regalos/ideas-regalo-3-anos/",
      description: "Selección por ocasión.",
    },
  ],
  faq: [
    {
      question: "¿Publicáis precios?",
      answer: "No.",
    },
  ],
  relatedLinks: [
    {
      title: "Cómo elegir juguetes según la edad",
      href: "/guias/como-elegir-juguetes-por-edad/",
      description: "Criterios prácticos.",
    },
  ],
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

describe("CategoryPageView", () => {
  it("renders child collections and related content", () => {
    render(<CategoryPageView page={page} />);

    expect(screen.getByRole("navigation", { name: "Migas de pan" })).toBeInTheDocument();
    expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent(
      "Ideas de regalo por edad para niños de 3 a 5 años",
    );
    expect(
      screen.getByRole("link", { name: /Ideas de regalo para niños de 3 años/i }),
    ).toHaveAttribute("href", "/regalos/ideas-regalo-3-anos");
    expect(
      screen.getByRole("link", { name: /Cómo elegir juguetes según la edad/i }),
    ).toHaveAttribute("href", "/guias/como-elegir-juguetes-por-edad");
    expect(screen.getByText("¿Publicáis precios?")).toBeInTheDocument();
  });
});
