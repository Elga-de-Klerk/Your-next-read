<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import type { Book } from '../types/Book';
import { fetchTbrList, removeFromTbr } from '../services/bookService';

const props = defineProps<{ refreshKey: number }>();
const emit = defineEmits<{ tbrUpdated: [] }>();

const books = ref<Book[]>([]);
const loading = ref(true);

async function load() {
  loading.value = true;
  books.value = await fetchTbrList();
  loading.value = false;
}

async function handleRemove(bookId: number) {
  await removeFromTbr(bookId);
  emit('tbrUpdated');
}

onMounted(load);
watch(() => props.refreshKey, load);
</script>

<template>
  <section>
    <h2>📋 Your TBR list</h2>
    <p v-if="loading">Loading...</p>
    <p v-else-if="!books.length">Nothing here yet — search for books above and add some!</p>
    <ul v-else>
      <li v-for="book in books" :key="book.id">
        {{ book.title }} by {{ book.author }}
        <button @click="handleRemove(book.id)">Remove</button>
      </li>
    </ul>
  </section>
</template>