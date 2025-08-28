package com.example.dayglow.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String create(SiteUser user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "이미 존재하는 이름입니다.";
        }
        userRepository.save(user);
        return "회원가입 성공";
    }

    public boolean login(String username, String password) {
        SiteUser user = userRepository.findByUsername(username);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }
}
