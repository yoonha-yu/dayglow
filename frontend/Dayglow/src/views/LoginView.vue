<template>
  <div class="auth-page">
    <div class="auth-card">
      <h1 class="auth-title">Login</h1>
      <p class="auth-subtitle">Welcome back to DAYGLOW</p>

      <form class="auth-form" @submit.prevent="onSubmit">
        <div class="form-group">
          <label for="username">아이디</label>
          <input
            id="username"
            v-model="username"
            type="text"
            placeholder="아이디를 입력하세요"
            required
          />
        </div>

        <div class="form-group">
          <label for="password">비밀번호</label>
          <input
            id="password"
            v-model="password"
            type="password"
            placeholder="••••••••"
            required
          />
        </div>

        <div class="auth-links">
          <button class="link-button small" type="button" @click="onFindId">
            아이디를 잊으셨나요?
          </button>
        </div>

        <button class="auth-button" type="submit">
          로그인
        </button>
      </form>

      <div class="auth-footer">
        <span>아직 회원이 아니신가요?</span>
        <button class="link-button" type="button" @click="goToSignup">
          회원가입하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginView',
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    onSubmit() {
      console.log('로그인 시도', this.username)
    },
    goToSignup() {
      this.$router.push('/signup')
    },
    onFindId() {
      console.log('아이디 찾기 기능은 추후 구현 예정')
    }
  }
}
</script>

<style scoped lang="scss" src="@/assets/styles/page/LoginView.scss"></style>

