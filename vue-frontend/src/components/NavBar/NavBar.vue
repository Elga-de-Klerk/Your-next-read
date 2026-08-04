<template>
  <div class="sidebar">
    <div class="title">
      <h1>The Little Book Nook</h1>
    </div>

    <nav class="menu">
      <p>Wander gently</p>
      <ul>
        <li class="menu-item">
          <router-link to="/search">
            <span class="icon material-symbols-outlined">search</span>
            <span class="label">Search</span>
          </router-link>
        </li>

        <li class="menu-item">
          <router-link to="/tbr">
            <span class="icon material-symbols-outlined">import_contacts</span>
            <span class="label">My Shelf</span>
          </router-link>
        </li>

        <li class="menu-item">
          <router-link to="/next-read">
            <span class="icon material-symbols-outlined">explore</span>
            <span class="label">My Next Read</span>
          </router-link>
        </li>
      </ul>
    </nav>
  </div>
</template>

<style lang="scss" src="./NavBar.scss" scoped></style>