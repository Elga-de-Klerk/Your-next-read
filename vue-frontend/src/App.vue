<script setup lang="ts">
import NavBar from "./components/NavBar/NavBar.vue";
</script>

<template>
  <NavBar/>

  <main>
    <header class="shelf-nav">
      <h1>📚 Your Next Read</h1>
    </header>
    <router-view />
  </main>
</template>