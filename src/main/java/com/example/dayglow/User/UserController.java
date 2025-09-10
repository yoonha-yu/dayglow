package com.example.dayglow.User;

import com.example.dayglow.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SiteUser siteUser) {
        userService.create(siteUser);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SiteUser siteUser) {
        boolean ok = userService.login(siteUser.getUsername(), siteUser.getPassword());
        if (ok) {
            String token = jwtUtil.createToken(siteUser.getUsername());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            Map<String, String> body = new HashMap<>();
            body.put("message", "로그인 성공");
            body.put("token", token);

            return new ResponseEntity<>(body, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }

    @PostMapping("/find-id")
    public ResponseEntity<?> findUsername(@RequestBody EmailRequestDTO request) {
        try {
            String username = userService.findUsernameByEmail(request.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> restPassword(@RequestBody ResetPasswordRequestDTO request) {
        try {
            userService.restPassword(request.getUsername(), request.getEmail());
            return ResponseEntity.ok("임시 비밀번호를 이메일로 전송했습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
