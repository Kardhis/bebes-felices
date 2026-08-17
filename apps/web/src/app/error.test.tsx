import { fireEvent, render, screen } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import ErrorPage from "./error";

describe("Home error boundary", () => {
  it("shows a safe message and retries on request", () => {
    const reset = vi.fn();
    render(<ErrorPage error={new Error("secret internal detail")} reset={reset} />);

    expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent(
      "No hemos podido cargar",
    );
    expect(screen.queryByText("secret internal detail")).not.toBeInTheDocument();
    fireEvent.click(screen.getByRole("button", { name: "Reintentar" }));
    expect(reset).toHaveBeenCalledOnce();
  });
});
