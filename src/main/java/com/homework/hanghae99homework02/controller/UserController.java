package com.homework.hanghae99homework02.controller;


import com.homework.hanghae99homework02.dto.RegisterDto;
import com.homework.hanghae99homework02.dto.UserDto;
import com.homework.hanghae99homework02.jwt.JwtTokenProvider;
import com.homework.hanghae99homework02.model.User;
import com.homework.hanghae99homework02.security.UserDetailsImpl;
import com.homework.hanghae99homework02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/api/login")
    public String loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto);
    }

    @PostMapping("/api/register")
    public void registerUser(@RequestBody RegisterDto registerDto){
        userService.registerUser(registerDto);
    }

    @GetMapping("/api/user")//?layout=
    public int setLayout(@RequestParam int layout, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.setLayout(layout,userDetails);
    }

}
