package com.example.dayglow.User;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequestDTO {
    private String username;
    private String email;
}
