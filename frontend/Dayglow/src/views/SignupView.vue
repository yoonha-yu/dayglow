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

<style scoped lang="scss">
.auth-page {
  min-height: calc(100vh - 80px);
  display: flex;
  align-items: center;
  justify-content: center;
  background: #111;
  color: #fff;
}

.auth-card {
  width: 100%;
  max-width: 480px;
  padding: 40px 36px;
  border-radius: 16px;
  background: #181818;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.6);
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.auth-title {
  margin: 0;
  font-size: 28px;
  font-weight: 600;
}

.auth-subtitle {
  margin: 4px 0 0;
  font-size: 14px;
  color: #aaa;
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 6px;

  label {
    font-size: 13px;
    color: #ddd;
  }

  input {
    padding: 10px 12px;
    border-radius: 8px;
    border: 1px solid #333;
    background: #111;
    color: #fff;
    font-size: 14px;
    outline: none;

    &:focus {
      border-color: #fff;
    }
  }
}

.phone-row {
  display: flex;
  gap: 8px;
}

.outline-button {
  padding: 8px 12px;
  border-radius: 999px;
  border: 1px solid #555;
  background: transparent;
  color: #fff;
  font-size: 12px;
  cursor: pointer;
  white-space: nowrap;

  &:hover {
    border-color: #fff;
  }
}

.auth-button {
  margin-top: 8px;
  width: 100%;
  padding: 10px 0;
  border-radius: 999px;
  border: none;
  background: #fff;
  color: #111;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
  transition: background 0.25s ease, color 0.25s ease;

  &:hover {
    background: #e5e5e5;
  }
}

.auth-footer {
  margin-top: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 6px;
  font-size: 13px;
  color: #bbb;
}

.link-button {
  background: none;
  border: none;
  padding: 0;
  font-size: 13px;
  font-weight: 500;
  color: #fff;
  text-decoration: underline;
  cursor: pointer;
}
</style>

