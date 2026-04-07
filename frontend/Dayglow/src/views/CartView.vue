<template>
  <div class="cart-page">
    <h1 class="cart-title">장바구니</h1>

    <div v-if="!cartItems.length" class="cart-empty">
      <p>장바구니에 담긴 상품이 없습니다.</p>
    </div>

    <div v-else class="cart-layout">
      <section class="cart-list">
        <div class="cart-list-header">
          <label class="checkbox-all">
            <input
              type="checkbox"
              :checked="isAllSelected"
              @change="toggleAll"
            />
            <span>전체 선택 ({{ selectedCount }}/{{ cartItems.length }})</span>
          </label>
        </div>

        <div
          v-for="item in cartItems"
          :key="item.id"
          class="cart-item"
        >
          <div class="item-check">
            <input
              type="checkbox"
              v-model="item.selected"
            />
          </div>
          <div class="thumb" />
          <div class="info">
            <p class="name">{{ item.name }}</p>
            <p class="option">{{ item.option }}</p>
          </div>
          <div class="qty">
            <button type="button" @click="changeQty(item, -1)">-</button>
            <input
              :id="`qty-${item.id}`"
              class="qty-input"
              type="text"
              inputmode="numeric"
              :value="item.qtyText"
              @mousedown="selectQty($event)"
              @focus="onQtyFocus(item, $event)"
              @click="selectQty($event)"
              @keydown="preventInvalidQtyKeys"
              @input="onQtyInput(item, $event.target.value)"
              @blur="commitQty(item)"
            />
            <button type="button" @click="changeQty(item, 1)">+</button>
          </div>
          <div class="price">
            {{ formatPrice(item.price * item.quantity) }}원
          </div>
          <button
            type="button"
            class="remove-btn"
            @click="removeItem(item)"
          >
            삭제
          </button>
        </div>
      </section>

      <aside class="cart-summary">
        <div class="row">
          <span>상품 금액</span>
          <span>{{ formatPrice(selectedSubtotal) }}원</span>
        </div>
        <div class="row">
          <span>배송비</span>
          <span>0원</span>
        </div>
        <div class="row total">
          <span>결제 예상 금액</span>
          <span>{{ formatPrice(selectedSubtotal) }}원</span>
        </div>

        <button
          type="button"
          class="checkout-btn"
          @click="checkout"
        >
          선택 상품 주문하기
        </button>
      </aside>
    </div>

    <!-- 수량 과다 입력 안내 모달 -->
    <div
      v-if="showQtyWarningModal"
      class="qty-warning-backdrop"
      @click.self="closeQtyWarningModal"
    >
      <div class="qty-warning-modal">
        <h2>수량 확인</h2>
        <p>
          선택하신 수량이 너무 큽니다. ({{ pendingQtyValue }}개)<br />
          최대 구매 가능 수량은 99개입니다. 수량을 다시 확인해주세요.
        </p>
        <div class="modal-actions">
          <button type="button" class="primary" @click="confirmQtyWarning">
            확인
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CartView',
  data() {
    return {
      cartItems: [
        // TODO: 백엔드 /carts 연동 시 이 부분을 API 응답으로 대체
        { id: 1, name: 'Modern Lounge Chair', option: '베이지 / 1EA', price: 299000, quantity: 1, qtyText: '1', selected: true },
        { id: 2, name: 'Green Accent Chair', option: '그린 / 1EA', price: 249000, quantity: 1, qtyText: '1', selected: true }
      ],
      showQtyWarningModal: false,
      pendingQtyItem: null,
      pendingQtyValue: null
    }
  },
  computed: {
    subtotal() {
      return this.cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0)
    },
    selectedSubtotal() {
      return this.cartItems
        .filter(item => item.selected)
        .reduce((sum, item) => sum + item.price * item.quantity, 0)
    },
    selectedCount() {
      return this.cartItems.filter(item => item.selected).length
    },
    isAllSelected() {
      return this.cartItems.length > 0 && this.selectedCount === this.cartItems.length
    }
  },
  methods: {
    formatPrice(value) {
      return value.toLocaleString()
    },
    changeQty(item, diff) {
      const next = (Number(item.quantity) || 1) + diff
      this.applyQty(item, next)
      // TODO: 백엔드에 수량 변경을 반영하려면 /carts PATCH/POST 호출 추가
    },
    onQtyFocus(item, event) {
      item._prevQty = Number(item.quantity) || 1
      this.selectQty(event)
    },
    selectQty(event) {
      if (!event || !event.target || typeof event.target.select !== 'function') return
      // type="number"는 브라우저별로 focus 타이밍에 select가 씹히는 경우가 있어 지연 선택
      setTimeout(() => {
        try {
          event.target.select()
        } catch (_) {
          // ignore
        }
      }, 0)
    },
    preventInvalidQtyKeys(e) {
      const blocked = ['e', 'E', '+', '-', '.']
      if (blocked.includes(e.key)) e.preventDefault()
    },
    onQtyInput(item, raw) {
      const nextRaw = String(raw).replace(/[^\d]/g, '')
      item.qtyText = nextRaw
      if (nextRaw === '') return
      this.applyQty(item, Number(nextRaw), { silent: true })
    },
    commitQty(item) {
      if (item.qtyText === '') {
        this.applyQty(item, 1)
        return
      }
      this.applyQty(item, Number(item.qtyText))
    },
    applyQty(item, nextQty, opts = {}) {
      const MAX_QTY = 99
      const next = Math.floor(Number(nextQty))
      if (!Number.isFinite(next)) return

      if (next < 1) {
        item.quantity = 1
        item.qtyText = '1'
        return
      }

      if (next > MAX_QTY) {
        this.pendingQtyItem = item
        this.pendingQtyValue = next
        this.showQtyWarningModal = true
        // 입력창에는 이전 값 유지
        item.quantity = item._prevQty || 1
        item.qtyText = String(item.quantity)
        return
      }

      item.quantity = next
      item.qtyText = String(next)
    },
    removeItem(item) {
      this.cartItems = this.cartItems.filter(i => i.id !== item.id)
      // TODO: 백엔드 삭제 연동: DELETE /carts/{cartItemId}
    },
    toggleAll(event) {
      const checked = event.target.checked
      this.cartItems.forEach(item => {
        item.selected = checked
      })
    },
    checkout() {
      // TODO: 백엔드 구매 연동: POST /carts/buying
      const selectedItems = this.cartItems.filter(item => item.selected)
      console.log('장바구니 구매 요청', selectedItems)
    },
    closeQtyWarningModal() {
      this.showQtyWarningModal = false
      this.pendingQtyItem = null
      this.pendingQtyValue = null
    },
    confirmQtyWarning() {
      this.closeQtyWarningModal()
    }
  }
}
</script>

<style scoped lang="scss" src="@/assets/styles/page/CartView.scss"></style>


