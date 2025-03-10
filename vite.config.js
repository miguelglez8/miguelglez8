import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import {BASE_PATH} from "./src/routes/routes.js";

export default defineConfig({
  resolve: {
    alias: {
      crypto: 'crypto-browserify'
    }
  },
  base: BASE_PATH,
  plugins: [react()],
});
