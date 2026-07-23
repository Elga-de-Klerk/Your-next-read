<script setup lang="ts">
import {ref} from "vue";

const isSidebarOpen = ref(true);
</script>

<template>
  <aside :class="{ collapsed: isSidebarOpen }" class="sidebar">
    <nav class="menu">
      <ul>
        <li class="menu-item">
          <span class="material-symbols-outlined">search</span>
          <span class="label">Search</span>
        </li>

        <li class="menu-item">
          <span class="material-symbols-outlined">library_books</span>
          <span class="label">My Shelf</span>
        </li>

        <li class="menu-item">
          <span class="material-symbols-outlined">auto_awesome</span>
          <span class="label">Pick for Me</span>
        </li>
      </ul>
    </nav>

    <button class="menu-item nav-menu-button" @click="isSidebarOpen = !isSidebarOpen">
      <span class="material-symbols-outlined">menu</span>
      <span class="label">Collapse</span>
    </button>
  </aside>
</template>

<style lang="scss" src="./NavBar.scss" scoped></style>