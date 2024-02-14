package com.example.camping.user.controller;

import com.example.camping.global.config.JwtTokenProvider;
import com.example.camping.global.exception.DuplicationEmailException;
import com.example.camping.user.dto.UserLoginRequest;
import com.example.camping.user.dto.UserRegisterRequest;
import com.example.camping.user.entity.Users;
import com.example.camping.user.enums.Role;
import com.example.camping.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public Long register(@RequestBody UserRegisterRequest request) {
        Optional<Users> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            throw new DuplicationEmailException("이미 존재하는 이메일입니다.");
        }

        return userRepository.save(Users.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .phone(request.getPhone())
                .role(Role.ROLE_MEMBER)
                .build()
        ).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        Users users = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("가입 되지 않은 이메일입니다.")
        );

        if (!passwordEncoder.matches(request.getPassword(), users.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
        }

        return jwtTokenProvider.createToken(users.getEmail(), users.getRole());
    }
}
