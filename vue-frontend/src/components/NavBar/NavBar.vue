<template>
  <div class="sidebar">
    <div class="title">
      <h1>Your Next Read</h1>
    </div>

    <nav class="menu">
      <ul>
        <li class="menu-item">
          <router-link to="/search">
            <span class="material-symbols-outlined">search</span>
            <span class="label">Search</span>
          </router-link>
        </li>

        <li class="menu-item">
          <router-link to="/tbr">
            <span class="material-symbols-outlined">library_books</span>
            <span class="label">My Shelf</span>
          </router-link>
        </li>

        <li class="menu-item">
          <router-link to="/next-read">
            <span class="material-symbols-outlined">auto_awesome</span>
            <span class="label">Pick for Me</span>
          </router-link>
        </li>
      </ul>
    </nav>
  </div>
</template>

<style lang="scss" src="./NavBar.scss" scoped></style>