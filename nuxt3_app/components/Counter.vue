<script setup>
import { useCounterStore } from '~/stores/useCounterStore.js'

const store = useCounterStore()
const counter = useCounter()
</script>

<template>
  <div>
    <h1>Pinia</h1>
    <button @click="store.increment()">
      +
    </button>
    <p>Count: {{ store.count }}</p>
    <p>Double Count: {{ store.doubleCount }}</p>
  </div>
</template>
