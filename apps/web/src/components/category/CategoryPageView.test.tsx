import { fireEvent, render, screen } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import { CategoryPageView } from "./CategoryPageView";
import type { CategoryPageResponse } from "@/lib/category/getCategoryPage";

const navigation = vi.hoisted(() => ({
  replace: vi.fn(),
}));

vi.mock("next/navigation", () => ({
  usePathname: () => "/regalos/",
  useRouter: () => ({ replace: navigation.replace }),
}));

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
      hubAge: 3,
    },
    {
      title: "Ideas de regalo para niños de 4 años",
      href: "/regalos/ideas-regalo-4-anos/",
      description: "Selección por ocasión.",
      hubAge: 4,
    },
    {
      title: "Ideas de regalo para niños de 5 años",
      href: "/regalos/ideas-regalo-5-anos/",
      description: "Selección por ocasión.",
      hubAge: 5,
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
  it("filters child collections by age and keeps the selected pill highlighted", () => {
    navigation.replace.mockClear();
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

    const age3 = screen.getByRole("button", { name: "3 años" });
    const age4 = screen.getByRole("button", { name: "4 años" });
    const age5 = screen.getByRole("button", { name: "5 años" });
    expect(age3).toHaveAttribute("aria-pressed", "true");
    expect(age4).toHaveAttribute("aria-pressed", "false");
    expect(
      screen.queryByRole("link", { name: /Ideas de regalo para niños de 4 años/i }),
    ).not.toBeInTheDocument();

    fireEvent.click(age4);
    expect(navigation.replace).toHaveBeenCalledWith("/regalos/?edad=4", {
      scroll: false,
    });
    expect(age3).toHaveAttribute("aria-pressed", "false");
    expect(age4).toHaveAttribute("aria-pressed", "true");
    expect(
      screen.getByRole("link", { name: /Ideas de regalo para niños de 4 años/i }),
    ).toBeInTheDocument();
    expect(
      screen.queryByRole("link", { name: /Ideas de regalo para niños de 3 años/i }),
    ).not.toBeInTheDocument();

    fireEvent.click(age5);
    expect(age4).toHaveAttribute("aria-pressed", "false");
    expect(age5).toHaveAttribute("aria-pressed", "true");
    expect(
      screen.getByRole("link", { name: /Ideas de regalo para niños de 5 años/i }),
    ).toBeInTheDocument();
    expect(screen.getByText("¿Publicáis precios?")).toBeInTheDocument();
    expect(navigation.replace).toHaveBeenCalledWith("/regalos/?edad=5", {
      scroll: false,
    });
  });

  it("restores the age that was already selected in the URL", () => {
    render(<CategoryPageView page={page} initialAge={4} />);

    expect(screen.getByRole("button", { name: "4 años" })).toHaveAttribute(
      "aria-pressed",
      "true",
    );
    expect(
      screen.getByRole("link", { name: /Ideas de regalo para niños de 4 años/i }),
    ).toBeInTheDocument();
    expect(
      screen.queryByRole("link", { name: /Ideas de regalo para niños de 3 años/i }),
    ).not.toBeInTheDocument();
  });
});
