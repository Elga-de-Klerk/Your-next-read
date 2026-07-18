<script setup lang="ts">
import NavBar from "./components/NavBar/NavBar.vue";
</script>

<template>
  <header class="shelf-nav">
    <h1>📚 Your Next Read</h1>
    <NavBar/>
  </header>

  <main>
    <router-view />
  </main>
</template>