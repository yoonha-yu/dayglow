package com.example.dayglow.Cart;

import com.example.dayglow.User.SiteUser;
import com.example.dayglow.User.UserRepository;
import com.example.dayglow.global.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    // 공통 메서드
    private SiteUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 테스트용 처리
        if (username.equals("anonymousUser")) {
            return userRepository.findByUsername("test")
                    .orElseThrow(() -> new RuntimeException("테스트 유저 없음"));
        }

        // 실제 로그인 유저
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }


    @PostMapping("/carts")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CartCreateRequestDTO requestDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        SiteUser siteUser = getCurrentUser();
        cartService.create(requestDTO, siteUser);
        return Response.success();
    }

    @GetMapping("/carts")
    @ResponseStatus(HttpStatus.OK)
    public Response findAll() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        SiteUser siteUser = getCurrentUser();
        return Response.success(cartService.findAll(siteUser));
    }

    @DeleteMapping("/carts/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteById(@PathVariable("cartItemId") Long id) {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    SiteUser siteUser = getCurrentUser();
    cartService.deleteById(id, siteUser);
    return Response.success();
    }

    @PostMapping("/carts/buying")
    @ResponseStatus(HttpStatus.OK)
    public Response buyingAll() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SiteUser siteUser = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        SiteUser siteUser = getCurrentUser();
        cartService.buyingAll(siteUser);
        return Response.success();
    }
}
