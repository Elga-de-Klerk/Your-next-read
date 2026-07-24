<script setup lang="ts">
import NavBar from "./components/NavBar/NavBar.vue";
import {ref} from "vue";

const sidebarOpen = ref(true);

</script>

<template>
  <NavBar @sideBarOpen="open => sidebarOpen = open"/>

  <main :class="{ expanded: sidebarOpen }" >
    <header class="shelf-nav">
      <h1>📚 Your Next Read</h1>
    </header>
    <router-view />
  </main>
</template>

<style lang="scss" src="./App.scss" scoped></style>

@removed="id => books = books.filter(b => b.id !== id)" />