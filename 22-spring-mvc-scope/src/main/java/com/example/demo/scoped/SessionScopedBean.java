package com.example.demo.scoped;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class SessionScopedBean {
	private Integer value;
}
