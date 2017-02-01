package com.estsoft.mysite.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;

@Service
public class BoardService 
{
	@Autowired
	private BoardDao dao;
	
	public List<BoardVo> getList(String kwd)
	{	
		List<BoardVo> list = null;
		
		List<BoardVo> search = dao.search(kwd);
	
		if ( search.size() != 0 )
		{
			list = search;
			return list;
		}
		else 
		{
			list = dao.getList();
			return list;
		}
	}
	
	public List<BoardVo> getSearchPage(int limitFirst, int listNum, String kwd)
	{
		List<BoardVo> list = dao.getSearchPage(limitFirst, listNum, kwd);
		return list;
	}
	
	public List<BoardVo> getPage(int limitFirst, int listNum)
	{
		List<BoardVo> list = dao.getPage(limitFirst, listNum);
		return list;
	}
	
	public void insert(BoardVo vo, Long no)
	{
		dao.add(vo, no);
	}
	
	public BoardVo getBoard(Long no)
	{
		BoardVo board = dao.getBoard(no);
		dao.countIncrease(board);
		return board;
	}
	
	public void delete(Long no)
	{
		dao.delete(no);
	}
	
	public void reply(BoardVo vo, BoardVo vo2, Long no)
	{
		dao.addReply(vo, vo2.getGroupNo(), vo2.getOrderNo(), vo2.getDepth(), no);
		
		dao.updateNo(vo2.getGroupNo(), vo2.getOrderNo());
	}
	
	public void modify(BoardVo boardVo)
	{
		dao.modify(boardVo);
	}
	
	
}
