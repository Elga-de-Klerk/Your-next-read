<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import type { Book } from '../../types/Book';
import { fetchBookDetails, fetchBookById, addToTbr, removeFromTbr } from '../../services/bookService';

const route = useRoute();

const book = ref<Book | null>(null);
const loading = ref(true);
const saving = ref(false);

const isSaved = computed(() => book.value?.id !== null && book.value?.onTbr);

onMounted(async () => {
  const savedId = route.query.id as string | undefined;
  const externalId = route.query.externalId as string | undefined;

  if (savedId) {
    book.value = await fetchBookById(Number(savedId));
  } else if (externalId) {
    const extra = await fetchBookDetails(externalId);
    book.value = {
      id: null,
      externalId,
      title: route.query.title as string,
      author: route.query.author as string,
      coverImageUrl: (route.query.cover as string) ?? null,
      publishedYear: route.query.year ? Number(route.query.year) : null,
      description: extra.description,
      pageCount: extra.pageCount,
      onTbr: false,
      tags: [],
    };
  }

  loading.value = false;
});

async function handleAdd() {
  if (!book.value) return;
  saving.value = true;
  book.value = await addToTbr(book.value);
  saving.value = false;
}

async function handleRemove() {
  if (!book.value?.id) return;
  saving.value = true;
  await removeFromTbr(book.value.id);
  book.value.onTbr = false;
  book.value.id = null;
  saving.value = false;
}
</script>

<template>
  <section v-if="!loading && book">
    <img v-if="book.coverImageUrl" :src="book.coverImageUrl" :alt="book.title" class="cover" />
    <h2>{{ book.title }}</h2>
    <p class="book-meta">
      {{ book.author }}<span v-if="book.publishedYear"> · {{ book.publishedYear }}</span>
    </p>
    <p v-if="book.pageCount" class="book-meta">{{ book.pageCount }} pages</p>

    <button v-if="!isSaved" @click="handleAdd" :disabled="saving">
      {{ saving ? 'Adding…' : '+ Add to TBR' }}
    </button>
    <button v-else @click="handleRemove" :disabled="saving">
      {{ saving ? 'Removing…' : 'Remove from TBR' }}
    </button>

    <p v-if="book.description">{{ book.description }}</p>
    <p v-else>No description available.</p>
  </section>
  <p v-else-if="!loading">Book not found.</p>
</template>