package com.estsoft.mysite.repository;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.estsoft.mysite.domain.Guestbook;

@Repository
public class GuestbookRepository {
	@PersistenceContext()  // 팩토리가 인식할 수 있게 해주는 팩도리. 주입을 해주는 것.
	private EntityManager em;
	
	public void save(Guestbook guestbook){
		guestbook.setRegDate(new Date());
		em.persist(guestbook);
	}
	
	public List<Guestbook> findAll(int page){
		TypedQuery<Guestbook> query = em.createQuery("select gb from Guestbook gb order by gb.regDate desc", Guestbook.class);
		query.setFirstResult( ( page-1) * 5 );
		query.setMaxResults( 5 );
		
		List<Guestbook> list = query.getResultList();
		
		return list;
	}
	
	public Boolean remove(Guestbook guestbook){
		TypedQuery<Guestbook> query = em.createQuery("select gb from Guestbook gb where gb.no = :no and gb.password = :password", Guestbook.class);
		query.setParameter("no", guestbook.getNo());
		query.setParameter("password", guestbook.getPassword());
		
/*		TypedQuery<Guestbook> query = em.createQuery("select gb from Guestbook gb where gb.no = ?1 and gb.password = ?2", Guestbook.class);
		query.setParameter(1, guestbook.getNo());
		query.setParameter(2, guestbook.getPassword());*/ // 위에꺼 방법1, 아래꺼 방법2
		
		List<Guestbook> list = query.getResultList();
		if (list.size() != 1){
			return false;
		}
		
		em.remove(list.get(0));
		
		return true;
	}
	
	public Guestbook checkDelete(Guestbook guestbook1){
		Guestbook guestbook = em.find(Guestbook.class, guestbook1.getNo());
		
		return guestbook;
	}
}
