<script setup lang="ts">
import { ref } from 'vue';
import type { Book } from '../../types/Book.ts';
import { fetchNextRead } from '../../services/bookService.ts';

const book = ref<Book | null>(null);
const error = ref<string | null>(null);
const loading = ref(false);
const revealed = ref(false);

async function handleFindNextRead() {
  loading.value = true;
  error.value = null;
  revealed.value = false;
  book.value = null;
  try {
    book.value = await fetchNextRead();
    requestAnimationFrame(() => (revealed.value = true));
  } catch (e) {
    error.value = e instanceof Error ? e.message : 'Could not find a book';
  } finally {
    loading.value = false;
  }
}
</script>

<template>
  <section class="pocket">
    <h3>Random draw</h3>
    <h2>Find my next read</h2>
    <button @click="handleFindNextRead" :disabled="loading">
      {{ loading ? 'Shuffling…' : 'Pick for me' }}
    </button>

    <p v-if="error" class="error">{{ error }}</p>

    <div v-if="book" class="card" :class="{ 'card--out': revealed }">
      <h3>{{ book.title }}</h3>
      <p class="book-meta">{{ book.author }}<span v-if="book.publishedYear"> · {{ book.publishedYear }}</span></p>
      <p v-if="book.description" class="card__desc">{{ book.description }}</p>
    </div>
  </section>
</template>

<style lang="scss" src="./NextReadView.scss" scoped></style>