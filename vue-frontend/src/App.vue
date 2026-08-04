<script setup lang="ts">
import NavBar from "./components/NavBar/NavBar.vue";
</script>

<template>
  <NavBar />

  <main>
    <router-view />
  </main>
</template>

<style lang="scss" src="./App.scss" scoped></style>