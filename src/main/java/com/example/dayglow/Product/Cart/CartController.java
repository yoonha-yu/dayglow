package com.example.dayglow.Product.Cart;

import com.example.dayglow.User.SiteUser;
import com.example.dayglow.User.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @PostMapping("/carts")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CartCreateRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(UsernameNotFoundException::new);
        cartService.create(requestDto, siteUser);
        return Response.success();
    }

    @GetMapping("/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(UsernameNotFoundException::new);
        return Response.success(cartService.findAll(siteUser));
    }

    @DeleteMapping("/carts/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteById(@PathVariable("cartItemId") Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(UsernameNotFoundException::new);
    cartService.deleteById(id, user);
    return Response.success();
    }

    @PostMapping("/carts/buying")
    @ResponseStatus(HttpStatus.OK)
    public Response buyingAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(UsernameNotFoundException::new);
        cartService.buyingAll(siteUser);
        return Response.success();
    }
}
