<script setup lang="ts">
import { ref, onMounted } from 'vue';
import type { Book } from '../../types/Book';
import { fetchTbrList, removeFromTbr } from '../../services/bookService';

const books = ref<Book[]>([]);
const loading = ref(true);

async function load() {
  loading.value = true;
  books.value = await fetchTbrList();
  loading.value = false;
}

async function handleRemove(bookId: number) {
  await removeFromTbr(bookId);
  await load();
}

onMounted(load);
</script>

<template>
  <section>
    <span class="title">Reading queue</span>
    <h2>Your TBR shelf</h2>
    <p v-if="loading">Loading…</p>
    <p v-else-if="!books.length" class="empty">Empty shelf. Search above and add something.</p>
    <ul v-else>
      <li v-for="book in books" :key="book.id">
        <span>{{ book.title }} <span class="book-meta">— {{ book.author }}</span></span>
        <button @click="handleRemove(book.id)">Remove</button>
      </li>
    </ul>
  </section>
</template>

<style lang="scss" src="./TbrView.scss" scoped></style>