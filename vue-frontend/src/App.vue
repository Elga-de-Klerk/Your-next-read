<script setup lang="ts">
import { ref, onMounted } from 'vue';
import type { Book } from './types/Book';
import { fetchBooks } from './services/bookService';

const books = ref<Book[]>([]);
const loading = ref(true);
const error = ref<string | null>(null);

onMounted(async () => {
  try {
    books.value = await fetchBooks();
  } catch (e) {
    error.value = e instanceof Error ? e.message : 'Something went wrong';
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <main>
    <h1>📚 Your Next Read</h1>

    <p v-if="loading">Loading books...</p>
    <p v-else-if="error">Error: {{ error }}</p>

    <ul v-else>
      <li v-for="book in books" :key="book.id">
        <strong>{{ book.title }}</strong> by {{ book.author }}
      </li>
    </ul>
  </main>
</template>