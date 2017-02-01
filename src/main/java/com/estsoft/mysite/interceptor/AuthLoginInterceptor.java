package com.estsoft.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.mysite.domain.User;
import com.estsoft.mysite.service.UserService;
import com.estsoft.mysite.vo.UserVo;


public class AuthLoginInterceptor extends HandlerInterceptorAdapter 
{
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
			
		//UserService 가져오는 방법 1
		/*// WEB Application Context 루트 컨터이너 가져오기 - IoC 컨테이너 가져오기
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());  // WEB 컨테이너 가져오는 녀석.
		
		UserService userService = applicationContext.getBean(UserService.class);  // WEB 컨테이너에있는 유저서비스의 빈을 가져오는 놈.
*/
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		//login 서비스 호출 = 로그인 작업
		User authUser = userService.login(user);
		if (authUser == null)
		{
			response.sendRedirect(request.getContextPath() + "/user/loginform");  //request.getContextPath() = /mysite3  이다.
			return false;
		}
		//로그인 처리
		HttpSession session = request.getSession(true);  // getSession에 true가 들어가면 세션이 없는경우 세션을 만들고, 세션이 있으면 있는 세션을 이용하라는 뜻.
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath() + "/main");
		return false;  // 리턴 펄스면 뒤에 핸들러 까지 전달이 안된다
	}
}
