package com.solt.bean;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RUNTIME)
@Target({ TYPE, FIELD, PARAMETER,METHOD })
@Qualifier
public @interface QuestType {
	enum Type{
		DRAGON, PRINCESS, MATROMONY,YOYO
	};
	Type value(); 
}
