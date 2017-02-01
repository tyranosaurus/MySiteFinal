package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.estsoft.db.DBConnection;
import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.vo.UserVo;

@Repository
public class UserDao 
{
	
	@Autowired
	private DBConnection dbConnection;
	
	@Autowired
	private SqlSession sqlSession;  // dataSource도 같이 여기에 들어있으므로 위의 코드는 지워도 됨.
	
	
	public UserVo get(String email)
	{
		UserVo vo = sqlSession.selectOne("user.selectByEmail", email);
		return vo;
	}
	
	// 보안 = 인증 + 권한
	
	// 인증(Auth)
	public UserVo get(UserVo vo)
	{
		UserVo authVo = sqlSession.selectOne("user.selectAuth", vo);
		return authVo;
		
	}
	
	public UserVo plusGender(Long no)
	{
		
		UserVo authVo = sqlSession.selectOne("user.selectPlusGender", no);
		return authVo;
	}
	
	public Long insert(UserVo vo)
	{
		sqlSession.insert("user.insert", vo);
		return vo.getNo();
	}
	
	public void getModify(UserVo vo)
	{	
		sqlSession.update("user.modify", vo);
	}
}
