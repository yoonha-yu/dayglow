<template>
  <div class="mypage mypage-coupons">
    <section class="mypage-header">
      <div class="user-info">
        <div class="avatar small">
          <span>{{ userInitial }}</span>
        </div>
        <div class="user-text">
          <p class="label">나의 쿠폰</p>
          <p class="username">{{ username }}</p>
        </div>
      </div>
    </section>

    <section class="mypage-section">
      <div class="section-header">
        <h2>보유 쿠폰</h2>
        <p class="section-subtitle">사용 가능 쿠폰 목록입니다.</p>
      </div>

      <div class="card coupon-card">
        <table class="coupon-table" v-if="coupons.length">
          <thead>
            <tr>
              <th>쿠폰명</th>
              <th>할인 혜택</th>
              <th>사용기간</th>
              <th>상태</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="coupon in coupons" :key="coupon.id">
              <td>{{ coupon.name }}</td>
              <td>{{ coupon.benefit }}</td>
              <td>{{ coupon.period }}</td>
              <td>{{ coupon.status }}</td>
            </tr>
          </tbody>
        </table>

        <p v-else class="empty-text">
          보유하신 쿠폰이 없습니다.
        </p>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'MyCouponsView',
  data() {
    return {
      username: 'dayglow_user',
      coupons: []
    }
  },
  computed: {
    userInitial() {
      return this.username ? this.username.charAt(0).toUpperCase() : 'U'
    }
  }
}
</script>

<style scoped lang="scss" src="@/assets/styles/page/MyCouponsView.scss"></style>

