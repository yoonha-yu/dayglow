package com.example.dayglow.Cart;

import com.example.dayglow.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
