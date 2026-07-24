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
  <div class="book-item">
    <router-link :to="{ name: 'book-detail', query }">

      <img v-if="book.coverImageUrl" class="cover" alt="book cover" :src="book.coverImageUrl" />
      <span v-else class="cover no-result">No image found</span>

      <div class="details">
        <div class="book-data">
          <span class="title">{{ book.title }}</span>
          <span class="author">{{ book.author }}</span>
        </div>

        <button v-if="!book.onTbr" @click="handleAdd" :disabled="saving">
          {{ saving ? 'Adding…' : '+ Add' }}
        </button>
        <button v-else @click="handleRemove" :disabled="saving">
          {{ saving ? 'Removing…' : 'Remove' }}
        </button>
      </div>


    </router-link>
  </div>
</template>

<style lang="scss" src="./BookItem.scss" scoped></style>