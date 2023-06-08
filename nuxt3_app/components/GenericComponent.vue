<script setup lang="ts" generic="T">
import type { GenericComp } from './types'

const properties = defineProps<GenericComp<T>>()
console.log(properties)
</script>

<template>
  <div class="box">
    <h1>Generic Component</h1>
    <ul>
      <li v-for="(g, idx) in items" :key="idx">
        {{ item }}

        <span v-if="item === selectedItem" style="font-size: 1.5rem; color: red">
          {{ item }}
        </span>
        <span v-else>{{ item }}</span>
      </li>
    </ul>
  </div>
</template>
