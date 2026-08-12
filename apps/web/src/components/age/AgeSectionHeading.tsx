type AgeSectionHeadingProps = {
  id: string;
  title: string;
  description?: string;
};

export function AgeSectionHeading({ id, title, description }: AgeSectionHeadingProps) {
  return (
    <header className="max-w-3xl">
      <h2
        id={id}
        className="font-[family-name:var(--font-nunito-sans)] text-2xl font-extrabold tracking-tight text-[var(--color-text)] sm:text-3xl"
      >
        {title}
      </h2>
      {description && (
        <p className="mt-2 text-base leading-relaxed text-[var(--color-text-secondary)]">
          {description}
        </p>
      )}
    </header>
  );
}
