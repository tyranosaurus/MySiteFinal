package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.UserVo;

@Repository
public class BoardDao 
{	
/*	@Autowired
	private DBConnection dbConnection;*/
	
	@Autowired
	private SqlSession sqlSession;
	
/*	public BoardDao(DBConnection dbConnection)
	{
		this.dbConnection = dbConnection;
	}*/
	
	public BoardVo getBoard(Long no)
	{
		BoardVo boardVo = sqlSession.selectOne("board.selectByNo", no);
		return boardVo;
	}
	
	public List<BoardVo> getList()
	{
		List<BoardVo> list = sqlSession.selectList("board.selectList");
		return list;

	}
	
	public List<BoardVo> getPage(int limitFirst, int listNum)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("limitFirst", limitFirst);
		map.put("listNum", listNum); 
		
		List<BoardVo> list = sqlSession.selectList("board.selectList2", map);
		
		return list;
	}
	

	public List<BoardVo> getSearchPage(int limitFirst, int listNum, String kwd)
	{		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("limitFirst", limitFirst);
		map.put("listNum", listNum);
		map.put("kwd", kwd);
		
		List<BoardVo> list = sqlSession.selectList("board.selectList3", map);
		
		return list;
		
	}
	
	public void add(BoardVo vo, Long no)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", vo.getTitle());
		map.put("content", vo.getContent());
		map.put("no", no);
		
		sqlSession.insert("board.insert", map);
	}
		
	public void delete(Long no)
	{
		sqlSession.delete("board.delete", no);
	}
	
	public void countIncrease(BoardVo board)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", board.getCount()+1);
		map.put("no", board.getNo());
		
		sqlSession.update("board.countupdate", map);
	}
	
	public void modify(BoardVo boardVo)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", boardVo.getTitle());
		map.put("content", boardVo.getContent());
		map.put("no", boardVo.getNo());
		
		sqlSession.update("board.modifyupdate", map);
	}
	
	public List<BoardVo> search(String kwd)
	{	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwd", kwd);
		
		List<BoardVo> list = sqlSession.selectList("board.selectList4", map);
		
		return list;
	}
	
	public void addReply(BoardVo vo, Long groupNo, Long orderNo, Long depth, Long userNo)
	{
//		System.out.println("댓글테스트 성공!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", vo.getTitle());
		map.put("content", vo.getContent());
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo+1);
		map.put("depth", depth+1);
		map.put("userNo", userNo);
		
		sqlSession.insert("board.insertreply", map);
	}
	
	public void updateNo(Long groupNo, Long orderNo)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupNo", groupNo);  
		map.put("orderNo", orderNo+1);
		
		sqlSession.update("board.noupdate", map);
	}
	
}
