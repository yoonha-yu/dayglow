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
          <label for="email">이메일</label>
          <div class="verify-row">
            <input
              id="email"
              v-model="email"
              type="email"
              placeholder="example@dayglow.com"
              required
            />
            <button
              class="outline-button"
              :class="{ completed: isEmailCodeSent }"
              type="button"
              @click="sendEmailVerificationCode"
            >
              {{ isEmailCodeSent ? '✓ 인증번호 전송 완료' : '인증번호 전송' }}
            </button>
          </div>
        </div>

        <div class="form-group">
          <label for="emailCode">이메일 인증번호</label>
          <div class="verify-row">
            <input
              id="emailCode"
              v-model="emailVerificationCode"
              type="text"
              placeholder="6자리 인증번호"
            />
            <button
              class="outline-button"
              :class="{ completed: isEmailVerified }"
              type="button"
              @click="verifyEmailCode"
            >
              {{ isEmailVerified ? '✓ 인증 완료' : '인증하기' }}
            </button>
          </div>
        </div>

        <div class="form-group">
          <label for="address">주소</label>
          <div class="address-row">
            <input
              id="address"
              v-model="address"
              class="address-input"
              type="text"
              placeholder="주소를 입력하세요"
              readonly
              required
            />
            <button
              class="outline-button"
              type="button"
              @click="openPostcodeSearch"
            >
              주소 검색
            </button>
          </div>
        </div>

        <div class="form-group">
          <label for="detailAddress">상세주소</label>
          <input
            id="detailAddress"
            v-model="detailAddress"
            type="text"
            placeholder="상세주소를 입력하세요 (예: 101동 1203호)"
          />
        </div>

        <div class="form-group">
          <label for="postcode">우편번호</label>
          <input
            id="postcode"
            v-model="postcode"
            class="postcode-input"
            type="text"
            placeholder="우편번호를 입력하세요"
            readonly
            required
          />
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

    <div
      v-if="showEmailNoticeModal"
      class="email-notice-backdrop"
      @click.self="closeEmailNoticeModal"
    >
      <div class="email-notice-modal">
        <h2>알림</h2>
        <p>{{ emailNoticeMessage }}</p>
        <div class="modal-actions">
          <button type="button" class="primary" @click="closeEmailNoticeModal">
            확인
          </button>
        </div>
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
      email: '',
      emailVerificationCode: '',
      postcode: '',
      address: '',
      detailAddress: '',
      isEmailCodeSent: false,
      isEmailVerified: false,
      showEmailNoticeModal: false,
      emailNoticeMessage: ''
    }
  },
  mounted() {
    this.loadDaumPostcodeScript()
  },
  methods: {
    onSubmit() {
      console.log('회원가입 시도', this.username)
    },
    sendEmailVerificationCode() {
      if (!this.email.trim()) {
        this.openEmailNoticeModal('이메일을 작성해주세요.')
        return
      }

      this.isEmailCodeSent = true
      this.isEmailVerified = false
      console.log('이메일 인증번호 전송 시도', this.email)
      this.openEmailNoticeModal('인증번호 전송이 완료되었습니다.')
    },
    verifyEmailCode() {
      if (!this.isEmailCodeSent) {
        this.openEmailNoticeModal('먼저 인증번호를 전송해주세요.')
        return
      }
      if (!this.emailVerificationCode.trim()) {
        this.openEmailNoticeModal('인증번호를 입력해주세요.')
        return
      }

      this.isEmailVerified = true
      console.log('이메일 인증번호 확인 시도', this.emailVerificationCode)
      this.openEmailNoticeModal('이메일 인증이 완료되었습니다.')
    },
    openEmailNoticeModal(message) {
      this.emailNoticeMessage = message
      this.showEmailNoticeModal = true
    },
    closeEmailNoticeModal() {
      this.showEmailNoticeModal = false
    },
    loadDaumPostcodeScript() {
      if (window.daum && window.daum.Postcode) {
        return
      }

      if (document.getElementById('daum-postcode-script')) {
        return
      }

      const script = document.createElement('script')
      script.id = 'daum-postcode-script'
      script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'
      document.body.appendChild(script)
    },
    openPostcodeSearch() {
      if (!(window.daum && window.daum.Postcode)) {
        alert('주소 검색 스크립트를 불러오는 중입니다. 잠시 후 다시 시도해주세요.')
        return
      }

      new window.daum.Postcode({
        oncomplete: (data) => {
          this.postcode = data.zonecode || ''
          this.address = data.roadAddress || data.jibunAddress || ''
        }
      }).open()
    },
    goToLogin() {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped lang="scss" src="@/assets/styles/page/SignupView.scss"></style>

