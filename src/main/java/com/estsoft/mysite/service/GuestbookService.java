package com.estsoft.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.mysite.domain.Guestbook;
import com.estsoft.mysite.repository.GuestbookRepository;


@Service
@Transactional  // 모든 메소드가 aop 작동
public class GuestbookService 
{
	@Autowired
	private GuestbookRepository dao;
	
	//list
	public List<Guestbook> getList(int page)
	{	
		return dao.findAll(page);
	}

	public void add(Guestbook guestbook)
	{
		dao.save(guestbook);
		//guestbook은 영속 객체이다.
	}
	
	public Boolean delete(Guestbook guestbook)
	{		
		return dao.remove(guestbook);
	}
	
	public Guestbook checkDelete(Guestbook guestbook){
		return dao.checkDelete(guestbook);
	}
}
