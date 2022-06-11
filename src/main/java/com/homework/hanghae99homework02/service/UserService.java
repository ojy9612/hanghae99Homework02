package com.homework.hanghae99homework02.service;

import com.homework.hanghae99homework02.dto.RegisterDto;
import com.homework.hanghae99homework02.dto.UserDto;
import com.homework.hanghae99homework02.jwt.JwtTokenProvider;
import com.homework.hanghae99homework02.model.User;
import com.homework.hanghae99homework02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void registerUser(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        System.out.println("registerDto1 = " + registerDto.getUsername());
        userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .email(registerDto.getEmail())
                .nickname(registerDto.getNickname())
                .build());
    }

    public String loginUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("ID를 찾을 수 없습니다."));
        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.pass() = " + user.getPassword());
        if(!passwordEncoder.matches(userDto.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }
}
