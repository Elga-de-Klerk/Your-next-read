<script setup lang="ts">
import { ref } from 'vue';
import type { Book } from '../types/Book';
import { searchAndImportBooks, addToTbr } from '../services/bookService';

const emit = defineEmits<{ tbrUpdated: [] }>();

const query = ref('');
const results = ref<Book[]>([]);
const loading = ref(false);
const error = ref<string | null>(null);

async function handleSearch() {
  if (!query.value.trim()) return;
  loading.value = true;
  error.value = null;
  try {
    results.value = await searchAndImportBooks(query.value);
  } catch (e) {
    error.value = e instanceof Error ? e.message : 'Search failed';
  } finally {
    loading.value = false;
  }
}

async function handleAdd(bookId: number) {
  await addToTbr(bookId);
  emit('tbrUpdated');
}
</script>

<template>
  <section>
    <h2>🔍 Search for books</h2>
    <form @submit.prevent="handleSearch">
      <input v-model="query" placeholder="e.g. Dune, Harry Potter..." />
      <button type="submit" :disabled="loading">Search</button>
    </form>

    <p v-if="loading">Searching...</p>
    <p v-if="error">{{ error }}</p>

    <ul v-if="results.length">
      <li v-for="book in results" :key="book.id">
        {{ book.title }} by {{ book.author }}
        <button @click="handleAdd(book.id)">+ Add to TBR</button>
      </li>
    </ul>
  </section>
</template>