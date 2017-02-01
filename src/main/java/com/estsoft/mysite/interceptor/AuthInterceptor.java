package com.estsoft.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.mysite.annotation.Auth;
import com.estsoft.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter 
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (handler instanceof HandlerMethod == false)  // 안전장치.
		{
			return true;
		}

		Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);  //getMethodAnnotation메소드가  메소드에 @Auth가 있는지 없는지 판별한다.
		//@Auth 가 없는 컨트롤러 핸들러
		// 접근제어가 필요 없음
		if (auth == null)
		{
			return true;
		}
		
		//접근제어 (인증이 필요함)
		HttpSession session = request.getSession();
		if (session == null)
		{
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null)
		{
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
		
		//인증된 사용자
		return true;
	}

}
