<script setup lang="ts">
import { ref } from 'vue';
import SearchBooks from './components/SearchBooks.vue';
import TbrList from './components/TbrList.vue';
import NextRead from './components/NextRead.vue';

const refreshKey = ref(0);

function handleTbrUpdated() {
  refreshKey.value++;
}
</script>

<template>
  <main>
    <h1>📚 Your Next Read</h1>
    <SearchBooks @tbr-updated="handleTbrUpdated" />
    <TbrList :refresh-key="refreshKey" @tbr-updated="handleTbrUpdated" />
    <NextRead />
  </main>
</template>