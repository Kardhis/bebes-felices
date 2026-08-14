import { notFound } from "next/navigation";

export async function loadOrNotFound<T>(
  loader: () => Promise<T>,
  isNotFound: (error: unknown) => boolean,
): Promise<T> {
  try {
    return await loader();
  } catch (error) {
    if (isNotFound(error)) {
      notFound();
    }
    throw error;
  }
}
