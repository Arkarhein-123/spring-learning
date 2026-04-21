package com.example.demo.scoped;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;


@RequestScope
@Component
@Data
public class RequestScopedBean {
	private Integer value;
}
