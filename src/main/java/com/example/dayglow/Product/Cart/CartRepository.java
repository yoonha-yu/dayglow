package com.example.dayglow.Product.Cart;

import com.example.dayglow.User.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartBySiteUser(SiteUser siteUser);
}
