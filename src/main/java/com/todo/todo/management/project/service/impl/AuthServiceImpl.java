package com.todo.todo.management.project.service.impl;

import com.todo.todo.management.project.dto.LoginDto;
import com.todo.todo.management.project.dto.RegisterDto;
import com.todo.todo.management.project.entity.Roles;
import com.todo.todo.management.project.entity.User;
import com.todo.todo.management.project.exception.TodoApiException;
import com.todo.todo.management.project.repository.RoleRepo;
import com.todo.todo.management.project.repository.UserRepo;
import com.todo.todo.management.project.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
private PasswordEncoder passwordEncoder;
    @Override
    public String Register(RegisterDto registerDto) {
        //check user name already exixt or not
        if(userRepo.existsByUsername(registerDto.getUsername())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST,"user already exist");
        }
        // check email already exist or not
        if(userRepo.existsByEmail(registerDto.getEmail())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST,"email already exist");
        }
        User user=new User();
        user.setName(registerDto.getUsername());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        Set<Roles>role=new HashSet<>();
       Roles userRole= roleRepo.findByName("ROLE_USER");

        role.add(userRole);
        user.setRoles(role);
        userRepo.save(user);

        return "User Registered Successfully....";
    }

    @Override
    public String Login(LoginDto loginDto) {
        if(userRepo.existsByUsername(loginDto.getUsername())) {

        }
        return "";
    }
}
