<script setup lang="ts">
import { ref } from 'vue';
import { searchBooks, addToTbr } from '../../services/bookService';
import BookItem from "../../components/BookItem/BookItem.vue";
import type {Book} from "../../types/Book.ts";

const query = ref('');
const results = ref<Book[]>([]);
const loading = ref(false);
const error = ref<string | null>(null);
const addedIds = ref<Set<string>>(new Set());

async function handleSearch() {
  if (!query.value.trim()) return;
  loading.value = true;
  error.value = null;
  try {
    results.value = await searchBooks(query.value);
  } catch (e) {
    error.value = e instanceof Error ? e.message : 'Search failed';
  } finally {
    loading.value = false;
  }
}

async function handleAdd(book: Book) {
  await addToTbr(book);
  if (book.externalId) {
    addedIds.value.add(book.externalId);
  }
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

    <div class="grid" v-if="results.length">
      <BookItem
          v-for="book in results"
          :key="book.externalId ?? book.title"
          :book="book"
          :buttonText="'Add'"
          @onClick="handleAdd(book)" />
    </div>
  </section>
</template>

<style lang="scss" src="./SearchView.scss" scoped></style>