import type { ReactNode } from "react";
import { Breadcrumbs } from "@/components/age/Breadcrumbs";
import { SiteFooter } from "@/components/home/SiteFooter";
import { SiteHeader } from "@/components/home/SiteHeader";

type EditorialPageShellProps = {
  breadcrumbs: Array<{ label: string; href: string }>;
  legalLinks: Array<{ label: string; href: string }>;
  updatedAt: string;
  children: ReactNode;
};

export function EditorialPageShell({
  breadcrumbs,
  legalLinks,
  updatedAt,
  children,
}: EditorialPageShellProps) {
  return (
    <>
      <SiteHeader variant="inner" />
      <main>
        <Breadcrumbs items={breadcrumbs} />
        {children}
      </main>
      <SiteFooter legalLinks={legalLinks} updatedAt={updatedAt} />
    </>
  );
}
