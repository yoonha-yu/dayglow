package com.example.dayglow;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController ("mainController")
public class MainController {
    @GetMapping("/")
    public String home() {
        return "요기는 메인 페이지 입니당^^!";
    }
}
