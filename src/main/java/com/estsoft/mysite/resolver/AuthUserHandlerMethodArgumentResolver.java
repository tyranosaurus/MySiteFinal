package com.estsoft.mysite.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.estsoft.mysite.annotation.AuthUser;
import com.estsoft.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver 
{

	@Override
	public boolean supportsParameter(MethodParameter parameter)   // 괄호안에 든것들을 아규먼트라고 한다. 모든 컨트롤러의 파라미터들(=아규먼트)이 들어와서 검사된다. 
	{
		//@AuthUser 어노테이션 체크  (@AuthUser가 제대로 있는지)
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);  // 
		if (authUser == null)
		{
			return false;
		}
		
		//check parameter type(UserVo)
		if (parameter.getParameterType().equals(UserVo.class) == false) // UserVo의 타입을 클래스 형으로 넘겨줌
		{
			return false;
		}
		
		
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception 
	{
		if (this.supportsParameter(parameter) == false)  // 지 함수 다시 불러서 체크
		{
			return WebArgumentResolver.UNRESOLVED;  // 현재 리졸버가 처리할 파라미터가 아닌 경우 unesolved를 리턴한다.
		}
		
		// Session에서 authUser 가져오기
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if (session == null)
		{
			return WebArgumentResolver.UNRESOLVED;
		}
		
		return session.getAttribute("authUser");
		
	}

}
