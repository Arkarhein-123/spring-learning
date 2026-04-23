package com.example.demo.service;


import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RoleDao roleDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    public void register(User user){
        roleDao.findByRoleName("ROLE_USER")
                .ifPresentOrElse( r -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.addRole(r);
                    userDao.save(user);
                },() ->{
                    Role userRole = new Role();
                    userRole.setRoleName("ROLE_USER");
                });
    }
}


