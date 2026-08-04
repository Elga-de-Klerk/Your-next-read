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
    <h3>The Catalogue</h3>
    <h2>Find a story <br/><em>to get lost in</em></h2>

    <form @submit.prevent="handleSearch" class="search-form">
      <button type="submit" class="search-icon" :disabled="loading" aria-label="Search">
        <span class="material-symbols-outlined">
          {{ loading ? 'hourglass_empty' : 'search' }}
        </span>
      </button>
      <input v-model="query" placeholder="Try “Dune” or “Murakami”..." />
    </form>

    <p v-if="!error && !results.length">Nothing is here yet, use the input to find books</p>
    <p v-if="error" class="error">Something went wrong, try again later</p>

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