package com.estsoft.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.mysite.annotation.Auth;
import com.estsoft.mysite.annotation.AuthUser;
import com.estsoft.mysite.service.BoardService;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController 
{	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="firstPage", required=true, defaultValue="1") int firstPage2,
					   @RequestParam(value="pageGroup", required=true, defaultValue="1") int pageGroup2,
					   @RequestParam(value="pageNum", required=true, defaultValue="0") int pageNum2,
					   @RequestParam(value="kwd", required=true, defaultValue="" ) String kwd,
					   HttpSession session,
					   Model model)
	{	
		final int maxPageNum = 3; 
		final int listNum = 5;  // 페이지당 게시물 수
		
		int pageNum = 1;
		int limitFirst = 0;
		int totalPage = 0;
		int listSize = 0;
		
		int firstPage = 1;
		int lastPage = 0;
		int pageGroup = 1;
		
		session.removeAttribute("board");
		
		List<BoardVo> list = boardService.getList(kwd);
		
		listSize = list.size();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		totalPage = (int) Math.ceil(list.size()/(double)listNum);
		
		//인자로 받아온는 firstPage, pageGroup 이 두값이 모두 null이 아닐때 이들을 인자로 받아 firstpage와 pageGroup를 최신화 한다. 
		if ( firstPage != 0 && pageGroup != 0)
		{
			firstPage = firstPage2;	
			pageGroup = pageGroup2;
		}
		
		// 페이징 처리시 아래 보이는 페이지 설정
		if ( (totalPage - firstPage) > 2 )
		{
			lastPage = firstPage +2;
		}
		else if ( (totalPage - firstPage) <= 2 )
		{
			lastPage = totalPage;
		}
		
		//게시물 전체나 검색시 현재 pageNum를 받아서 해당하는 게시글 목록을 출력하는 것.
		if (kwd.equals("") == false && pageNum2 == 0)
		{
			//System.out.println("1");
//			System.out.println("pageNum 없을때 : 키워드를 넣고 검색했을때 처음 보이는 초기 화면");
			list = boardService.getSearchPage(limitFirst, listNum, kwd);
		}
		else if (kwd.equals("") == false && pageNum2 != 0)
		{
			//System.out.println("2");
//			System.out.println("pageNum 있을때 : 검색 후 페이지 숫자를 누르면 나오는 게시글");
			pageNum = pageNum2;
			limitFirst = ( pageNum - 1 ) * listNum;
			
			list = boardService.getSearchPage(limitFirst, listNum, kwd);
		}
		else if (pageNum2 == 0)
		{
			//System.out.println("3");
//			System.out.println("pageNum 없을때 : 게시판 버튼이나 검색 버튼 누르고 나서 보이는 초기화면");
			list = boardService.getPage(limitFirst, listNum);
		}
		else if (pageNum2 > totalPage)
		{
			//System.out.println("4");
//			System.out.println("예외처리 : 페이지 수를 전체 페이지 수 보다 많이 입력했을 때");
			list = boardService.getPage(limitFirst, listNum);
		}
		else
		{
			//System.out.println("5");
//			System.out.println("pageNum 있을때 : 페이지 숫자를 누르면 나오는 게시글");
			pageNum = pageNum2;
			limitFirst = ( pageNum - 1 ) * listNum;
			
			list = boardService.getPage(limitFirst, listNum);
		}
		
		
		// 검색결과 일치하는 게시물이 없을때, 페이지가 1로 나오도록 설정.
		if (kwd != null && list.size() == 0)
		{
			firstPage = 1;
			lastPage = 1;
			totalPage = 1;
		}
		
		
		//map 에 넣는다 
		map.put("maxPageNum", maxPageNum);
		map.put("listNum", listNum);
		
		map.put("totalPage", totalPage);
		map.put("listSize", listSize);
		
		map.put("pageNum", pageNum);
		
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("pageGroup", pageGroup);
		
		map.put("kwd", kwd);

		// 모텔 애드 어트리뷰트		
		model.addAttribute("list", list);
		model.addAttribute("map",map);
		return "/board/list";
	}
	
	//@Auth("Admin") 이렇게하면 관리자만 접속 할 수 있게 설정할 수 있음
	@Auth
	@RequestMapping("/writeform")
	public String writeform(HttpSession session)
	{
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if( authUser == null)
		{
			return "redirect:/user/loginform";
		}
		
		return "/board/write";
	}
	
	@Auth
	@RequestMapping("/write")
	//public String write(@ModelAttribute BoardVo vo, HttpSession session, @RequestParam("no") Long no2)
	public String write(@AuthUser UserVo authUser, @ModelAttribute BoardVo vo, @RequestParam("no") Long no2)
	{	
		//UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if( authUser == null)
		{
			return "redirect:/user/loginform";
		}
		
		Long no = authUser.getNo();
		if (no2 == null)
		{
			boardService.insert(vo, no);
		}
		else
		{
			//BoardVo vo2 = (BoardVo)session.getAttribute("board");
			boardService.reply(vo, vo, no);
		}

		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, HttpSession session, Model model)
	{	
		BoardVo board = boardService.getBoard(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardNo", board.getNo());
		map.put("boardTitle", board.getTitle());
		map.put("boardContent", board.getContent());
		
		model.addAttribute("map", map);
		
		session.setAttribute("board", board);
		
		return "/board/view";  // view.jsp로 이동
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no)
	{	
		boardService.delete(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform()
	{	
		return "/board/modify"; 
	}
	
	@RequestMapping("/modify")
	public String modify(@RequestParam("title") String title, @RequestParam("content") String content, HttpSession session)
	{	
		BoardVo boardVo = (BoardVo)session.getAttribute("board");
		boardVo.setTitle(title);
		boardVo.setContent(content);
		
		boardService.modify(boardVo);
		return "redirect:/board/list";
	}
}
