<script setup lang="ts">
import {ref} from "vue";

const emit = defineEmits<{ sideBarOpen: [boolean] }>()
const isSidebarOpen = ref(true);

function toggleSidebar() {
  isSidebarOpen.value = !isSidebarOpen.value
  emit("sideBarOpen", isSidebarOpen.value);
}
</script>

<template>
  <aside :class="{ collapsed: isSidebarOpen }" class="sidebar">
    <nav class="menu">
      <ul>
        <router-link to="/search">
          <li class="menu-item">
            <span class="material-symbols-outlined">search</span>
            <span class="label">Search</span>
          </li>
        </router-link>

        <router-link to="/tbr">
          <li class="menu-item">
            <span class="material-symbols-outlined">library_books</span>
            <span class="label">My Shelf</span>
          </li>
        </router-link>

        <router-link to="/next-read">
          <li class="menu-item">
            <span class="material-symbols-outlined">auto_awesome</span>
            <span class="label">Pick for Me</span>
          </li>
        </router-link>
      </ul>
    </nav>

    <button class="menu-item nav-menu-button" @click="toggleSidebar()">
      <span class="material-symbols-outlined">menu</span>
      <span class="label">Collapse</span>
    </button>
  </aside>
</template>

<style lang="scss" src="./NavBar.scss" scoped></style>