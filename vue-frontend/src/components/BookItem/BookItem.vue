<script setup lang="ts">
import { ref } from 'vue';
import type { Book } from '../../types/Book';
import { addToTbr, removeFromTbr } from '../../services/bookService';

const props = defineProps<{ book: Book }>();

const saving = ref(false);

const query = props.book.id !== null
    ? { id: props.book.id }
    : {
      externalId: props.book.externalId,
      title: props.book.title,
      author: props.book.author,
      cover: props.book.coverImageUrl,
      year: props.book.publishedYear,
    };

async function handleAdd() {
  saving.value = true;
  await addToTbr(props.book);
  saving.value = false;
}

async function handleRemove() {
  if (!props.book.id) return;
  saving.value = true;
  await removeFromTbr(props.book.id);
  saving.value = false;
}
</script>

<template>
  <li>
    <router-link :to="{ name: 'book-detail', query }">{{ book.title }}</router-link>
    <span class="book-meta">— {{ book.author }}</span>

    <button v-if="!book.onTbr" @click="handleAdd" :disabled="saving">
      {{ saving ? 'Adding…' : '+ Add' }}
    </button>
    <button v-else @click="handleRemove" :disabled="saving">
      {{ saving ? 'Removing…' : 'Remove' }}
    </button>
  </li>
</template>