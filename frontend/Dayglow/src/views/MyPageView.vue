<template>
  <div class="mypage">
    <!-- 상단 프로필 / 요약 -->
    <section class="mypage-header">
      <div class="user-info">
        <div class="avatar">
          <span>{{ userInitial }}</span>
        </div>
        <div class="user-text">
          <p class="label">마이페이지</p>
          <p class="username">{{ username }}</p>
        </div>
      </div>

      <div class="summary-cards">
        <div class="summary-card summary-card--point">
          <p class="summary-label">적립금</p>
          <p class="summary-value">0 P</p>
        </div>
        <div
          class="summary-card summary-card--coupon"
          @click="goToCoupons"
        >
          <p class="summary-label">쿠폰</p>
          <p class="summary-value">0장</p>
        </div>
      </div>
    </section>

    <!-- 진행중인 주문 -->
    <section class="mypage-section">
      <div class="section-header">
        <h2>진행중인 주문</h2>
        <p class="section-subtitle">최근 3개월 기준</p>
      </div>

      <div class="order-steps">
        <div
          v-for="step in orderSteps"
          :key="step.key"
          class="order-step"
        >
          <span class="step-label">{{ step.label }}</span>
          <span class="step-count">0</span>
        </div>
      </div>
    </section>

    <!-- 주문 / 배송 내역 조회 -->
    <section class="mypage-section">
      <div class="section-header">
        <h2>주문 / 배송 내역 조회</h2>
      </div>
      <div class="card">
        <p class="empty-text">최근 주문 내역이 없습니다.</p>
      </div>
    </section>

    <!-- 최근 본 상품 -->
    <section class="mypage-section">
      <div class="section-header">
        <h2>최근 본 상품</h2>
      </div>
      <div class="card">
        <div class="recent-products" v-if="recentProducts.length">
          <div
            v-for="item in recentProducts"
            :key="item.id"
            class="recent-card"
          >
            <div class="thumb" />
            <div class="info">
              <p class="name">{{ item.name }}</p>
              <p class="price">{{ item.price }}</p>
            </div>
          </div>
        </div>
        <p v-else class="empty-text">최근 본 상품이 없습니다.</p>
      </div>
    </section>

    <!-- 회원 정보 / 탈퇴 -->
    <section class="mypage-section">
      <div class="section-header">
        <h2>회원 정보</h2>
      </div>
      <div class="card account-card">
        <div class="account-actions">
          <button class="primary-button" type="button">
            회원정보 수정
          </button>
          <button class="ghost-button" type="button">
            비밀번호 변경
          </button>
        </div>

        <div class="withdrawal">
          <p>더 이상 DAYGLOW 서비스를 이용하지 않으신가요?</p>
          <button class="danger-button" type="button">
            회원 탈퇴
          </button>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'MyPageView',
  data() {
    return {
      username: 'dayglow_user',
      orderSteps: [
        { key: 'waiting', label: '입금대기' },
        { key: 'paid', label: '결제완료' },
        { key: 'preparing', label: '배송준비' },
        { key: 'shipping', label: '배송중' },
        { key: 'delivered', label: '배송완료' },
        { key: 'review', label: '리뷰' }
      ],
      recentProducts: []
    }
  },
  computed: {
    userInitial() {
      return this.username ? this.username.charAt(0).toUpperCase() : 'U'
    }
  },
  methods: {
    goToCoupons() {
      this.$router.push('/mypage/coupons')
    }
  }
}
</script>

<style scoped lang="scss" src="@/assets/styles/page/MyPageView.scss"></style>

