import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  trailingSlash: true,
  // Compatibilidad durante despliegues escalonados con respuestas antiguas de la API.
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "images.unsplash.com",
      },
    ],
  },
};

export default nextConfig;
