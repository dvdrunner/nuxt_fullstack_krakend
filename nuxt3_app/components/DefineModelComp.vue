<script setup lang="ts">
const modelValue = defineModel<number>({ default: 0 })
</script>

<template>
  <div class="box">
    <h1>Define Model Component</h1>
    <p>Model Value: {{ modelValue }}</p>
    <button @click="modelValue++">
      Increment
    </button>
  </div>
</template>

<style scoped></style>
