<script setup lang="ts">
import type { Book } from '../../types/Book'

defineProps<{
  book: Book,
  buttonText: string
}>()
</script>

<template>
  <li>
    <span>{{ book.title }} <span class="book-meta">— {{ book.author }}</span></span>
    <button @click="$emit('onClick')">{{ buttonText }}</button>
  </li>
</template>