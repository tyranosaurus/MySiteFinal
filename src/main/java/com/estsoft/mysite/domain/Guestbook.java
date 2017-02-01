package com.estsoft.mysite.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "guestbook")
public class Guestbook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동증가
	private Long no;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "message", nullable = false, columnDefinition = "text") // columnDefinition : 직접 타입을 주고 싶을때 사용
	private String message;
	
	@Column(name = "passwd", nullable = false, length = 32) //db를 만들고 나서도 칼럼명을 이렇게 쓰면 칼럼명을 바꿀 수 있다. length -> varchar() 이 괄호안의 숫자 결정
	private String password;
	
	@Column(name = "reg_date", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date regDate;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Guestbook [no=" + no + ", name=" + name + ", message=" + message + ", password=" + password
				+ ", regDate=" + regDate + "]";
	}

	
}
