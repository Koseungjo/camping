package com.example.camping.user.entity;

import com.example.camping.global.entity.BaseTimeEntity;
import com.example.camping.user.dto.UserRegisterRequest;
import com.example.camping.user.enums.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String email;
    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

}
