import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  resolve: {
    alias: {
      crypto: 'crypto-browserify'
    }
  },
  base: "/miguelglez8",
  plugins: [react()],
});
