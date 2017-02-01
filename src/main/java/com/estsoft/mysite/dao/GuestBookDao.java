package com.estsoft.mysite.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.estsoft.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao 
{	

	
/*	@Autowired // bean설정에 구현한 객체가 여기로 주입된다.
	private DataSource dataSource;*/
	
	@Autowired
	private SqlSession sqlSession;  // dataSource도 같이 여기에 들어있으므로 위의 코드는 지워도 됨.
	
	public GuestBookVo get(Long no)
	{	
		GuestBookVo vo = sqlSession.selectOne("guestbook.selectByNo",no);
		return vo;
	}
	
	public Long add(GuestBookVo vo)
	{
		//int count = 
		sqlSession.insert("guestbook.insert", vo);  // sqlSession.insert("guestbook.insert", vo)는 개수를 반환한다.
		//System.out.println(count + ":" + vo.getNo());
		
		return vo.getNo();
	}
	
	public int delete(Long no, String password)
	{
		//int countDeleted = sqlSession.delete("guestbook.delete", vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);  // 왼쪽인자는 #{password}임
		
		int countDeleted = sqlSession.delete("guestbook.delete2", map);
		return countDeleted;
		
	}
	
	public List<GuestBookVo> getList()
	{

		//sqlSession.selectList("",~~~); 
		List<GuestBookVo> list = sqlSession.selectList("guestbook.selectList"); // 리턴타입이 제네릭임. 괄호안에 쿼리의 아이디를 적어준다. namespace도 같이 붙여준다. guestbook. 요녀석.
				
		return list;
	}
	
	public List<GuestBookVo> getList(int page)  // 페이지넘버를 받아 데이터를 가져오는 오버로딩
	{

		int p = (page-1)*5;
		List<GuestBookVo> list = sqlSession.selectList("guestbook.selectList2", p);
		return list;
	}
}
