<script setup lang="ts">
import type {BookSearchResult} from "../../types/BookSearchResult.ts";

defineProps<{
  book: BookSearchResult,
  buttonText: string
}>()
</script>

<template>
  <li>
<!--    <img src="${{ book.coverImageUrl}}" />-->
    <span>{{ book.title }} <span class="book-meta">— {{ book.author }}</span></span>
    <button @click="$emit('onClick')">{{ buttonText }}</button>
  </li>
</template>