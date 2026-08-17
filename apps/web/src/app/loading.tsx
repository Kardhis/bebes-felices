export default function Loading() {
  return (
    <main
      id="contenido-principal"
      className="flex min-h-screen items-center justify-center bg-[var(--color-primary-700)] px-4 text-center text-white"
      aria-busy="true"
      aria-live="polite"
    >
      <div>
        <div
          className="mx-auto size-10 animate-spin rounded-full border-4 border-white/30 border-t-white motion-reduce:animate-none"
          aria-hidden
        />
        <p className="mt-4 font-semibold">Preparando Bebes Felices…</p>
      </div>
    </main>
  );
}
