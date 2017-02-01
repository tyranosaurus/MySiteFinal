package com.estsoft.mysite.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD)  // 어노테이션의 타켓을 지정 FIELD, METHOD, PARAMETER, TYPE 중 하나. 우리는 메소드위에 쓰는 걸로 지정.
@Retention( RetentionPolicy.RUNTIME)  // 어노테이션의 보존기간을 설정, RUNTIME, SOURCE  중 하나. 우리는 런타임 동안으로 지정.
public @interface Auth 
{
/*	String value() default "user";
	boolean reqired() default true;*/
}
