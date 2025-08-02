import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";

const baseUrl = process.env.GITHUB_REPOSITORY
  ? "/" + process.env.GITHUB_REPOSITORY.split("/")[1]
  : "/TODO";

console.log("Base URL:", baseUrl);

export default defineConfig({
  plugins: [scalaJSPlugin({ cwd: "../", projectID: "web" })],
  server: {
    proxy: {
      // For requests to /api/**, pass to backend
      "/api": {
        target: "http://localhost:8080",
      },
    },
  },
  base: baseUrl,
});
