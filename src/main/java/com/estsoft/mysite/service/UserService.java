package com.estsoft.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.mysite.domain.User;
import com.estsoft.mysite.repository.UserRepository;
import com.estsoft.mysite.vo.UserVo;

@Service
@Transactional
public class UserService 
{
	@Autowired
	private UserRepository userR;
	
	public void join(User user)
	{
		userR.save(user);
	}
	
	public User login(User user)
	{
		User authUser = userR.getLogin(user);  // 이메일과 ~~가 세팅되어야함
		
		return authUser;
	}
	
	public User getUser(String email)
	{
		User authUser = userR.get(email);
		return authUser;
	}

	public void modify(User authUser)
	{
		userR.Modify(authUser);
	}
	
}
