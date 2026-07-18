<script setup lang="ts">
import { ref } from 'vue';
import type { Book } from '../types/Book';
import { fetchNextRead } from '../services/bookService';

const book = ref<Book | null>(null);
const error = ref<string | null>(null);
const loading = ref(false);

async function handleFindNextRead() {
  loading.value = true;
  error.value = null;
  book.value = null;
  try {
    book.value = await fetchNextRead();
  } catch (e) {
    error.value = e instanceof Error ? e.message : 'Could not find a book';
  } finally {
    loading.value = false;
  }
}
</script>

<template>
  <section>
    <h2>🎲 Find my next read</h2>
    <button @click="handleFindNextRead" :disabled="loading">
      {{ loading ? 'Thinking...' : 'Pick for me!' }}
    </button>

    <p v-if="error">{{ error }}</p>

    <div v-if="book">
      <h3>{{ book.title }}</h3>
      <p>by {{ book.author }}</p>
      <p v-if="book.description">{{ book.description }}</p>
    </div>
  </section>
</template>