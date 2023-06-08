<script setup>
/* PINIA */
import { useCounterStore } from '~/stores/useCounterStore.js'

const store = useCounterStore()
console.log(store.doubleCount)
</script>

<template>
  <button @click="login">
    Login
  </button>
  <nuxt-link to="/protected">
    protected
  </nuxt-link>

  <h1>Pinia</h1>
  <div>
    <button @click="store.increment()">
      +
    </button>
    <p>Count: {{ store.count }}</p>
    <p>Double Count: {{ store.doubleCount }}</p>
  </div>
</template>
