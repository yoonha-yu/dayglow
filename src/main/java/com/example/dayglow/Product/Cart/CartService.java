package com.example.dayglow.Product.Cart;

import com.example.dayglow.Product.ProductRepository;
import com.example.dayglow.User.SiteUser;
import com.example.dayglow.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void create(CartCreateRequestDTO requestDTO, SiteUser siteUser) {
        product product = productRepository.findById(requestDTO.getProduct_id()).orElseThrow(ProductNotFoundException::new);
    }
}
