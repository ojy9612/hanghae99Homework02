package com.homework.hanghae99homework02.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDto {
    private String email;
    private String name;
    private String password;
    private String nickname;
}