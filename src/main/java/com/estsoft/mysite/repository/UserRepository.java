package com.estsoft.mysite.repository;

import static com.estsoft.mysite.domain.QUser.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.estsoft.mysite.domain.User;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;

@Repository
public class UserRepository {
	
	@PersistenceContext()  // 팩토리가 인식할 수 있게 해주는 팩도리. 주입을 해주는 것.
	private EntityManager em;
	
	public void save(User user){
		
		em.persist(user);
	}
	
	public User getLogin(User user1){
		JPAQuery query = new JPAQuery(em);
		List<User> list = 
							query.
							from(user).
							where(user.email.eq(user1.getEmail()), user.password.eq(user1.getPassword())).
							list(user);
		
		if (list.size() != 0){
			User user = list.get(0);
			
			User authUser = em.find(User.class, user.getNo());
			
			return authUser;
		}

		return null;
	}
	
	public User get(String email){
		
		JPAQuery query = new JPAQuery(em);
		List<User> list = 
							query.
							from(user).
							where(user.email.eq(email)). // where 절의 and는 쉼표 -> , 로 대체할 수 있다.
							list(user);
		
		if (list.size() != 0){
			User user = list.get(0);
			
			User authUser = em.find(User.class, user.getNo());
			
			return authUser;
		}

		return null;
	}
	
	public void Modify(User authUser){
		JPAUpdateClause clause = new JPAUpdateClause(em, user); // (em, 수정할 형식)
		//몇개 수정됬는지 알려줌.
		long count = clause.
				     where(user.no.eq(authUser.getNo())).
				     set(user.gender, authUser.getGender()).
				     set(user.name, authUser.getName()).
				     set(user.password, authUser.getPassword()).
				     execute();
		System.out.println(count);
	}

}
