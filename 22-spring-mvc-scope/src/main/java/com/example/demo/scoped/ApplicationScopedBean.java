package com.example.demo.scoped;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Component
@ApplicationScope
@Data
public class ApplicationScopedBean {
	private Integer value;
}
