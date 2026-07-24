<script setup lang="ts">
import { ref, onMounted } from 'vue';
import BookItem from "../../components/BookItem/BookItem.vue";
import type { Book } from '../../types/Book';
import {fetchTbrList, removeFromTbr} from "../../services/bookService.ts";

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
    <div class="grid" v-else>
      <BookItem
          v-for="book in books"
          :key="book.id!"
          :book="book"
          :buttonText="'Remove'"
          @onClick="handleRemove(book.id!)"
      />
    </div>
  </section>
</template>

<style lang="scss" src="./TbrView.scss" scoped></style>