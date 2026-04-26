package com.arkar.springsec.config;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public @Nullable Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username =authentication.getName();
        String password = (String) authentication.getCredentials();

        if("Arkar".equals(username) && "12345".equals(password)){
            return new UsernamePasswordAuthenticationToken(
                    username,password, Arrays.asList()
            );
        }else{
            throw new AuthenticationCredentialsNotFoundException("Errors");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken
                .class
                .isAssignableFrom(authentication);
    }
}
