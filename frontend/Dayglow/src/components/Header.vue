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

      <div class="cart" @click="handleCartClick">
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

    <!-- 로그인 안내 모달 -->
    <div
      v-if="showCartLoginModal"
      class="cart-login-modal-backdrop"
      @click.self="closeCartLoginModal"
    >
      <div class="cart-login-modal">
        <h2>로그인이 필요한 서비스입니다</h2>
        <p>장바구니는 로그인 후 이용하실 수 있어요.<br />지금 로그인하시겠어요?</p>
        <div class="modal-actions">
          <button type="button" class="secondary" @click="closeCartLoginModal">
            나중에 할게요
          </button>
          <button type="button" class="primary" @click="goToLoginFromModal">
            로그인하기
          </button>
        </div>
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
      isCategoryOpen: false,
      showCartLoginModal: false
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
    },
    handleCartClick() {
      // TODO: 실제 로그인 여부와 연동 필요
      this.showCartLoginModal = true
    },
    closeCartLoginModal() {
      this.showCartLoginModal = false
    },
    goToLoginFromModal() {
      this.showCartLoginModal = false
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="scss">
@import "@/assets/styles/components/header.scss";
</style>
