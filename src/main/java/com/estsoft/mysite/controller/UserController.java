package com.estsoft.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.mysite.domain.Gender;
import com.estsoft.mysite.domain.User;
import com.estsoft.mysite.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController 
{	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinform")
	public String joinform()
	{
		return "/user/joinform";
	}
	
	@RequestMapping("/checkemail")
	@ResponseBody
	public Map<String, Object> checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email)
	{      
		//Map<> 대신 Object 그냥 객체 반환이라고 써도됨
		User user = userService.getUser(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", user == null);
		
		return map;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@Valid @ModelAttribute User user, BindingResult result, Model model)  // 모델어트리뷰트를 쓰므로 vo의 변수명과 jsp파일의 name이 일치해야 함.
	{
		if (result.hasErrors())
		{
			model.addAllAttributes(result.getModel());  // map 으로 데이터가 들어간다. 
			return "/user/joinform";
		}
		

		
		userService.join(user);
		return "redirect:/user/joinsuccess";  // 리다이렉팅
	}
	
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess()
	{
		return "/user/joinsuccess";  // 포워딩
	}
	
	@RequestMapping("/loginform")
	public String loginform()
	{
		return "/user/loginform";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session, Model model)
	{
		User authUser = (User) session.getAttribute("authUser");
		
		session.setAttribute("authUserVo", authUser);
		model.addAttribute("authUserVo", authUser);
		
		return "/user/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpSession session, 
						 Model model,
						@RequestParam("name") String name,
						@RequestParam("password") String password,
						@RequestParam("gender") Gender gender)
	{
		User authUser = (User)session.getAttribute("authUserVo");
		
		authUser.setName(name);
		authUser.setPassword(password);
		authUser.setGender(gender);
		
		userService.modify(authUser);
		
		session.setAttribute("authUser", authUser);
		model.addAttribute("authUser", authUser);
		
		return "redirect:/main";
	}
}
