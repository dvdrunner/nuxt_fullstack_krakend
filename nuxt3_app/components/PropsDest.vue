<script setup lang="ts">
const { msg = 'Hello World' } = defineProps<{
  msg?: string
}>()
watchEffect(() => {
  console.log(msg)
})
</script>

<template>
  <div class="box">
    <h1>Props Dest Component</h1>
    <p>{{ msg }}</p>
  </div>
</template>
