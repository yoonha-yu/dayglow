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
  max-width: 420px;
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

.auth-links {
  display: flex;
  justify-content: flex-end;
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

  &.small {
    font-size: 12px;
    color: #aaa;
  }
}
</style>

