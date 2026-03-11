<template>
  <div class="auth-page">
    <div class="auth-card">
      <h1 class="auth-title">Sign Up</h1>
      <p class="auth-subtitle">Create your DAYGLOW account</p>

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
          <label for="name">이름</label>
          <input
            id="name"
            v-model="name"
            type="text"
            placeholder="이름을 입력하세요"
            required
          />
        </div>

        <div class="form-group">
          <label for="password">비밀번호</label>
          <input
            id="password"
            v-model="password"
            type="password"
            placeholder="비밀번호를 입력하세요"
            required
          />
        </div>

        <div class="form-group">
          <label for="passwordConfirm">비밀번호 확인</label>
          <input
            id="passwordConfirm"
            v-model="passwordConfirm"
            type="password"
            placeholder="비밀번호를 다시 입력하세요"
            required
          />
        </div>

        <div class="form-group">
          <label for="phone">휴대폰 번호</label>
          <div class="phone-row">
            <input
              id="phone"
              v-model="phone"
              type="tel"
              placeholder="010-0000-0000"
              required
            />
            <button
              class="outline-button"
              type="button"
              @click="sendVerificationCode"
            >
              인증번호 전송
            </button>
          </div>
        </div>

        <div class="form-group">
          <label for="code">인증번호</label>
          <div class="phone-row">
            <input
              id="code"
              v-model="verificationCode"
              type="text"
              placeholder="6자리 인증번호"
            />
            <button
              class="outline-button"
              type="button"
              @click="verifyCode"
            >
              인증하기
            </button>
          </div>
        </div>

        <button class="auth-button" type="submit">
          회원가입 완료
        </button>
      </form>

      <div class="auth-footer">
        <span>이미 계정이 있으신가요?</span>
        <button class="link-button" type="button" @click="goToLogin">
          로그인하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SignupView',
  data() {
    return {
      username: '',
      name: '',
      password: '',
      passwordConfirm: '',
      phone: '',
      verificationCode: ''
    }
  },
  methods: {
    onSubmit() {
      console.log('회원가입 시도', this.username)
    },
    sendVerificationCode() {
      console.log('인증번호 전송 시도', this.phone)
    },
    verifyCode() {
      console.log('인증번호 확인 시도', this.verificationCode)
    },
    goToLogin() {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped lang="scss" src="@/assets/styles/page/SignupView.scss"></style>

