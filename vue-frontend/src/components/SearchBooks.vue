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
    <span class="title">Catalog search</span>
    <h2>Find something new</h2>
    <form @submit.prevent="handleSearch">
      <input v-model="query" placeholder="Try “dune” or “murakami”..." />
      <button type="submit" :disabled="loading">{{ loading ? 'Searching…' : 'Search' }}</button>
    </form>

    <p v-if="error" class="error">{{ error }}</p>

    <ul v-if="results.length">
      <li v-for="book in results" :key="book.id">
        <span>{{ book.title }} <span class="book-meta">— {{ book.author }}</span></span>
        <button @click="handleAdd(book.id)">+ Add</button>
      </li>
    </ul>
  </section>
</template>

<style lang="scss" src="./SearchBooks.scss" scoped></style>