package com.example.camping.user.service;

import com.example.camping.user.UserInfoAccount;
import com.example.camping.user.entity.Users;
import com.example.camping.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " 사용자 없음")
        );

        return new UserInfoAccount(users);
    }
}
