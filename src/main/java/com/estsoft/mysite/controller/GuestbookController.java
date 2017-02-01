package com.estsoft.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.mysite.domain.Guestbook;
import com.estsoft.mysite.service.GuestbookService;
import com.estsoft.mysite.vo.GuestBookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController 
{
	@Autowired
	private GuestbookService gbService;
	
	@RequestMapping("/list")
	public String list()
	{	
		return "/guestbook/ajax-main";
	}
	
	@RequestMapping("/list2")
	@ResponseBody
	public Map<String, Object> list2(@RequestParam(value="p", required=true, defaultValue="1") int page, Model model)
	{	
		List<Guestbook> list = gbService.getList(page);
		model.addAttribute("list", list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);

		return map;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(@ModelAttribute Guestbook gb)
	{	
		gbService.add(gb);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", gb);
		
		return map;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@ModelAttribute Guestbook gb)
	{
		gbService.delete(gb);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", gb.getNo());
		
		return map;
	}
}
