package com.example.dayglow.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SiteUser siteUser) {
        userService.create(siteUser);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody SiteUser siteUser) {
        boolean ok = userService.login(siteUser.getUsername(), siteUser.getPassword());
        if (ok) return "로그인 성공";
        else return "로그인 실패";
    }
}
