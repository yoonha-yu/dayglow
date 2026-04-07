package com.example.dayglow.Cart;

import com.example.dayglow.Product.Product;
import com.example.dayglow.Product.ProductRepository;
import com.example.dayglow.User.SiteUser;
import com.example.dayglow.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void create(CartCreateRequestDTO requestDTO, SiteUser siteUser) {
        Product product = productRepository.findById(requestDTO.getProduct_id()).orElseThrow(()-> new RuntimeException("상품을 찾을 수 없습니다."));

        if (product.getQuantity() < requestDTO.getQuantity()) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        // 장바구니 조회 or 생성
        Cart cart = cartRepository.findCartBySiteUser(siteUser)
                .orElseGet(() -> cartRepository.save(new Cart(siteUser)));

        // 기존 상품 있는지 확인
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem != null) {
            // 있으면 수량 증가
            cartItem.setQuantity(cartItem.getQuantity() + requestDTO.getQuantity());
            cartItemRepository.save(cartItem);
        } else {
            // 없으면 새로 생성
            cartItem = new CartItem(cart, product, requestDTO.getQuantity());
            cartItemRepository.save(cartItem);
        }
    }

    // 장바구니 조회
    @Transactional(readOnly = true)
    public List<CartItemResponseDTO> findAll(SiteUser siteUser) {

        Cart cart = cartRepository.findCartBySiteUser(siteUser)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));

        List<CartItem> items = cartItemRepository.findAllByCart(cart);
        List<CartItemResponseDTO> result = new ArrayList<>();

        for (CartItem item : items) {
            Product product = item.getProduct();

            result.add(
                    new CartItemResponseDTO().toDTO(
                            item,
                            product.getName(),
                            product.getPrice()
                    )
            );
        }

        return result;
    }

    // 장바구니 상품 삭제
    @Transactional
    public void deleteById(Long id, SiteUser siteUser) {

        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("장바구니 상품을 찾을 수 없습니다."));

        Cart cart = cartItem.getCart();

        if (!cart.getSiteUser().equals(siteUser)) {
            throw new RuntimeException("사용자가 일치하지 않습니다.");
        }

        cartItemRepository.delete(cartItem);
    }

    // 전체 구매
    @Transactional
    public void buyingAll(SiteUser siteUser) {

        Cart cart = cartRepository.findCartBySiteUser(siteUser)
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));

        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);

        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();

            // 개별 체크
            checkCanBuyEach(product, siteUser, cartItem);

            // 재고 감소
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());

            // 구매자 돈 감소
            siteUser.setMoney(
                    siteUser.getMoney() - product.getPrice() * cartItem.getQuantity()
            );

            // 판매자 돈 증가
            product.getSeller().setMoney(
                    product.getSeller().getMoney() + product.getPrice() * cartItem.getQuantity()
            );
        }

        // 전체 체크
        checkCanBuyAll(siteUser);

        // 장바구니 삭제
        cartRepository.delete(cart);
    }

    // 개별 구매 가능 체크
    public void checkCanBuyEach(Product product, SiteUser siteUser, CartItem cartItem) {

        if (cartItem.getQuantity() > product.getQuantity()) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        if (siteUser.getMoney() < product.getPrice() * cartItem.getQuantity()) {
            throw new RuntimeException("잔액이 부족합니다.");
        }
    }

    // 전체 구매 가능 체크
    public void checkCanBuyAll(SiteUser siteUser) {

        if (siteUser.getMoney() < 0) {
            throw new RuntimeException("잔액이 부족합니다.");
        }
    }
}