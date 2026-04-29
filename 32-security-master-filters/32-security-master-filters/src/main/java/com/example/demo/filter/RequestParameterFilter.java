package com.example.demo.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestParameterFilter extends OncePerRequestFilter{
	@Value("${code.secret}")
	private String code;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String secret = request.getParameter("code");
		SecurityContext context = SecurityContextHolder.getContext();
		if(code.equals(secret)) {
			context.setAuthentication(new AnonymousAuthenticationToken("Yha", "12345", List.of(
					new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"))));
		}
		filterChain.doFilter(request, response);
		
	}

}
