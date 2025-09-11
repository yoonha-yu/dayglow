package com.example.dayglow.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailUtil emailUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, EmailUtil emailUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailUtil = emailUtil;
        this.passwordEncoder = passwordEncoder;
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
        return passwordEncoder.matches(password, user.getPassword());
    }

    public String findUsernameByEmail(String email) {
        SiteUser user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("해당 이메일로 가입된 사용자가 없습니다."));
        return user.getUsername();
    }

    public void restPassword(String username, String email) {
        SiteUser user = userRepository.findByUsernameAndEmail(username, email)
                .orElseThrow(()-> new RuntimeException("입력한 정보와 일치하는 사용자가 없습니다."));

        if(!user.getEmail().equals(email)){
            throw new RuntimeException("이메일 불일치");
        }

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);

        user.setPassword(passwordEncoder.encode(tempPassword));
        userRepository.save(user);

        System.out.println("임시 비밀번호: " + tempPassword + ", 이메일: " + email);

        try {
            String subject = "임시 비밀번호 안내";
            String message = "임시 비밀번호: " + tempPassword + "\n로그인 후 비밀번호를 변경해주세요.";
//            emailUtil.sendEmail(email, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("이메일 전송 실패: " + e.getMessage());
        }
    }
}
