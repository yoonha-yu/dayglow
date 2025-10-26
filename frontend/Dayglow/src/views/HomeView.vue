<template>
  <div class="home-page">
    <!-- 배너 -->
    <section class="main-banner">
      <img :src="bannerImage" alt="배너" class="banner-img" />
      <div class="banner-overlay">
        <h1>The Art of Modern<br>Interior Living</h1>
      </div>
    </section>

    <!-- 아이콘 섹션 -->
    <section class="features-section">
      <div class="feature-item">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="1" y="3" width="15" height="13"></rect>
            <polygon points="16 8 20 8 23 11 23 16 16 16 16 8"></polygon>
            <circle cx="5.5" cy="18.5" r="2.5"></circle>
            <circle cx="18.5" cy="18.5" r="2.5"></circle>
          </svg>
        </div>
        <p>Free Shipping Over $50</p>
      </div>

      <div class="feature-item">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path>
          </svg>
        </div>
        <p>Quality Assurance</p>
      </div>

      <div class="feature-item">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"></path>
            <polyline points="7.5 4.21 12 6.81 16.5 4.21"></polyline>
            <polyline points="7.5 19.79 7.5 14.6 3 12"></polyline>
            <polyline points="21 12 16.5 14.6 16.5 19.79"></polyline>
            <polyline points="3.27 6.96 12 12.01 20.73 6.96"></polyline>
            <line x1="12" y1="22.08" x2="12" y2="12"></line>
          </svg>
        </div>
        <p>Return Within 14 days</p>
      </div>

      <div class="feature-item">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path>
          </svg>
        </div>
        <p>Support 24/7</p>
      </div>
    </section>

    <!-- 카테고리 섹션 -->
    <section class="category-section">
      <div class="category-carousel">
        <button class="carousel-btn prev" @click="scrollCategories('left')">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="15 18 9 12 15 6"></polyline>
          </svg>
        </button>

        <div class="category-scroll" ref="categoryScroll">
          <div class="category-card" v-for="category in categories" :key="category.id">
            <img :src="category.image" :alt="category.name" />
            <div class="category-info">
              <h3>{{ category.name }}</h3>
              <p>{{ category.itemCount }} items</p>
            </div>
          </div>
        </div>

        <button class="carousel-btn next" @click="scrollCategories('right')">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="9 18 15 12 9 6"></polyline>
          </svg>
        </button>
      </div>
    </section>

    <!-- 베스트셀러 섹션 -->
    <section class="bestseller-section">
      <div class="section-header">
        <h2>Bestseller</h2>
        <button class="view-all-btn" @click="goToAllProducts">
          View All
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="9 18 15 12 9 6"></polyline>
          </svg>
        </button>
      </div>
      <p class="section-subtitle">Experience the best products at our store!</p>

      <div class="product-grid">
        <div class="product-card" v-for="product in bestsellerProducts" :key="product.id">
          <div class="product-badge" v-if="product.badge">{{ product.badge }}</div>
          <div class="product-image">
            <img :src="product.image" :alt="product.name" />
          </div>
          <div class="product-info">
            <h4>{{ product.name }}</h4>
            <p class="price">${{ product.price }}</p>
          </div>
        </div>
      </div>
    </section>

  </div>
</template>

<script>
import bannerImg from '@/assets/images/banner/banner.jpg'
import bathroomImg from '@/assets/images/categories/bathroom.jpg'
import chairImg from '@/assets/images/categories/chair.jpg'
import decorImg from '@/assets/images/categories/decor.jpg'
import lampImg from '@/assets/images/categories/lamp.jpg'
import chair1Img from '@/assets/images/products/chair1.jpg'
import chair2Img from '@/assets/images/products/chair2.jpg'
import tableImg from '@/assets/images/products/product5.jpg'
import elephantImg from '@/assets/images/products/product6.jpg'

export default {
  name: 'HomeView',
  data() {
    return {
      bannerImage: bannerImg,
      categories: [
        { id: 1, name: 'Bathroom', itemCount: 9, image: bathroomImg },
        { id: 2, name: 'Chair', itemCount: 17, image: chairImg },
        { id: 3, name: 'Decor', itemCount: 17, image: decorImg },
        { id: 4, name: 'Lamp', itemCount: 3, image: lampImg },
      ],
      bestsellerProducts: [
        { id: 1, name: 'Modern Lounge Chair', price: 299, image: chair1Img, badge: '-51%' },
        { id: 2, name: 'Green Accent Chair', price: 249, image: chair2Img },
        { id: 3, name: 'Round Side Table', price: 189, image: tableImg, badge: 'Out of stock' },
        { id: 4, name: 'Decor', price: 79, image: elephantImg },
      ]
    }
  },
  methods: {
    scrollCategories(direction) {
      const container = this.$refs.categoryScroll;
      const scrollAmount = 300;

      if (direction === 'left') {
        container.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
      } else {
        container.scrollBy({ left: scrollAmount, behavior: 'smooth' });
      }
    },
    goToAllProducts() {
      console.log('View All 클릭!');
    }
  }
}
</script>
