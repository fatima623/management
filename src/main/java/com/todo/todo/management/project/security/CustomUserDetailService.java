package com.todo.todo.management.project.security;

import com.todo.todo.management.project.entity.User;
import com.todo.todo.management.project.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor

public class CustomUserDetailService implements UserDetailsService {
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
        User user=userRepo.findByUsernameOrEmail(usernameoremail,usernameoremail).
                orElseThrow(()->new UsernameNotFoundException("User not forund"));
        Set<GrantedAuthority> authorities=user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                usernameoremail,
                user.getPassword(),
        authorities

        );
    }
}
