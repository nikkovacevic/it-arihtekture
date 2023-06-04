import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import federation from '@originjs/vite-plugin-federation';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
      react(),
      federation({
        name: 'app',
        remotes: {
          remoteApp: 'http://localhost:5173/assets/remoteEntryAuth.js',
            remoteApps: 'http://localhost:5174/assets/remoteEntryApps.js'
        },
        shared: ['react', 'react-dom']
      })
  ], build: {
    modulePreload: false,
    target: 'esnext',
    minify: false,
    cssCodeSplit: false
  }
})
