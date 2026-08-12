import { AgeSectionHeading } from "./AgeSectionHeading";

type DevelopmentSkill = {
  skill: string;
  description: string | null;
};

type DevelopmentSkillsProps = {
  items: DevelopmentSkill[];
};

export function DevelopmentSkills({ items }: DevelopmentSkillsProps) {
  return (
    <section
      id="habilidades"
      className="age-scroll-target bg-[var(--color-bg-alt)]"
      aria-labelledby="habilidades-heading"
    >
      <div className="mx-auto max-w-6xl px-4 py-14 sm:px-6 sm:py-16">
        <AgeSectionHeading
          id="habilidades-heading"
          title="Habilidades que desarrolla"
          description="Qué suele estar aprendiendo un niño o niña a esta edad y cómo encajan los productos recomendados."
        />
        <ul className="mt-10 grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {items.map((item, index) => (
            <li
              key={item.skill}
              className="rounded-2xl border border-[var(--color-border)] bg-white p-5 shadow-sm"
            >
              <div className="flex items-start gap-3">
                <span
                  className="flex h-8 w-8 shrink-0 items-center justify-center rounded-full bg-[var(--color-primary-100)] text-sm font-bold text-[var(--color-primary-700)]"
                  aria-hidden
                >
                  {index + 1}
                </span>
                <div>
                  <p className="font-semibold text-[var(--color-text)]">{item.skill}</p>
                  {item.description && (
                    <p className="mt-1.5 text-sm leading-relaxed text-[var(--color-text-secondary)]">
                      {item.description}
                    </p>
                  )}
                </div>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </section>
  );
}
