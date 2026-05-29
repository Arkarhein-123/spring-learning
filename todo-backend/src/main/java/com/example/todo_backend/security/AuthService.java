package com.example.todo_backend.security;

import com.example.todo_backend.dao.RoleDao;
import com.example.todo_backend.dao.UserDao;
import com.example.todo_backend.dto.LoginDto;
import com.example.todo_backend.dto.RegisterDto;
import com.example.todo_backend.dto.ResponseDto;
import com.example.todo_backend.entity.Role;
import com.example.todo_backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final RoleDao roleDao;

    public ResponseDto register(RegisterDto registerDto){
        var user = new User();
        user.setUsername(registerDto.username());
        user.setEmail(registerDto.email());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        roleDao.findByRoleName("ROLE_USER").ifPresentOrElse(
                r->{
                    user.addRole(r);
                },()->{
                    Role role = new Role();
                    role.setRoleName("ROLE_USER");
                    user.addRole(roleDao.save(role));
                }
        );
        var saveUser= userDao.save(user);
        return new ResponseDto(saveUser.getId(),saveUser.getUsername(),saveUser.getEmail());
    }

    public String login(LoginDto loginDto){
        var auth = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        Authentication authentication = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Hello! %s".formatted(authentication.getName());
    }
}
