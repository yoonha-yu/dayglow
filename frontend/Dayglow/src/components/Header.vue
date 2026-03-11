<template>
  <header class="header">

    <div class="header-left">
      <div class="logo">
        <router-link to="/">DAYGLOW</router-link>
      </div>

      <div
        class="category-menu"
        :class="{ 'is-open': isCategoryOpen }"
        @click="toggleCategory"
      >
        <div class="category-label">
          Category
          <span class="caret-icon">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 8" fill="none" stroke="currentColor" stroke-width="1.6">
              <polyline points="1 1 6 6 11 1" />
            </svg>
          </span>
        </div>

        <div class="dropdown">
          <router-link to="/category/chair">Chair</router-link>
          <router-link to="/category/table">Table</router-link>
          <router-link to="/category/sofa">Sofa</router-link>
          <router-link to="/category/lighting">Lighting</router-link>
        </div>
      </div>
    </div>

    <div class="menu-right">

      <router-link class="login" to="/login">
        Login
      </router-link>

      <div class="cart">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <circle cx="9" cy="20" r="1.8" />
          <circle cx="18" cy="20" r="1.8" />
          <path d="M3 4h2l2.2 11h11.3l1.5-7.5H7.1" />
        </svg>
      </div>

      <div class="search">
        <input v-model="keyword" placeholder="Search furniture" />
        <button @click="search">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="11" cy="11" r="6" />
            <line x1="16" y1="16" x2="21" y2="21" />
          </svg>
        </button>
      </div>

    </div>

  </header>
</template>

<script>
export default {
  name: 'AppHeader',
  data() {
    return {
      keyword: '',
      isCategoryOpen: false
    }
  },
  mounted() {
    document.addEventListener('click', this.handleClickOutside)
  },
  beforeUnmount() {
    document.removeEventListener('click', this.handleClickOutside)
  },
  methods: {
    search() {
      this.$router.push(`/search?keyword=${this.keyword}`)
    },
    toggleCategory() {
      this.isCategoryOpen = !this.isCategoryOpen
    },
    handleClickOutside(event) {
      const menu = this.$el.querySelector('.category-menu')
      if (menu && !menu.contains(event.target)) {
        this.isCategoryOpen = false
      }
    }
  }
}
</script>

<style lang="scss">
@import "@/assets/styles/components/header.scss";
</style>
