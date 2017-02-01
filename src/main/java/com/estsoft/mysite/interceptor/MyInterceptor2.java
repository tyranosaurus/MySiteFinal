package com.estsoft.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.mysite.service.UserService;

public class MyInterceptor2 extends HandlerInterceptorAdapter 
{
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyIntercepter2.preHandle called");
		System.out.println("123" + userService);
		
		return true;  // 리턴 펄스면 뒤에 핸들러 까지 전달이 안된다
	}
	


}
