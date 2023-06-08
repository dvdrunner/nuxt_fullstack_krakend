// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  runtimeConfig: {
    // Esta variable solo se puede acceder desde el servidor
    SECRET: process.env.SECRET_KEY || 'default',
  },
  dev: true,
  nitro: {
    devProxy: {
      '/api/': {
        target: 'http://localhost:8080/api/',
        changeOrigin: true,
      },
    },
  },

  hooks: {},
  alias: {
    assets: '/<rootDir>/assets',
  },
  modules: [
    '@nuxt/content',
    '@pinia/nuxt',
    '@unocss/nuxt',
    'nuxt-icon',
    '@nuxtjs/tailwindcss',
    '@formkit/nuxt',
    'nuxt-security',
    '@sidebase/nuxt-session',
  ],

  security: {
    // rateLimiter: {
    //   tokensPerInterval: 2,
    //   interval: "day",
    //   fireImmediately: false,
    //   throwError: false, // optional
    // },
    // basicAuth: {
    //   name: 'test',
    //   pass: 'test',
    //   enabled: true,
    //   message: 'test',
    //   route: '/secret'
    // }
  },

  css: [
    '/assets/css/main.scss',
    'primevue/resources/themes/lara-light-blue/theme.css',
    'primevue/resources/primevue.css',
    'primeicons/primeicons.css',
  ],
  postcss: {
    plugins: {
      autoprefixer: {},
    },
  },
  content: {
    highlight: {
      theme: 'one-dark-pro',
      preload: ['json', 'js', 'ts', 'html', 'css', 'vue'],
    },
    // Options
  },
  ssr: true,
  build: {
    transpile: ['primevue'],

  },
  // vite: {

  //   vue: {
  //     script: {
  //       defineModel: true,
  //       propsDestructure: true,
  //     },
  //   },
  // },

  // formkit: {
  //  configFile: './formkit.config.js'
  // },
})
