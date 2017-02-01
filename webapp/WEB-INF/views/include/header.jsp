<!-- 태그지시자 입력, 이걸해야 JSTL 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- 이걸 선언해줘야 JSTL 중 core를 사용가능 prefix는 name에 해당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 중 fomat 를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  <!-- JSTL 중 function 를 사용가능 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div id="header">
			<h1><a href="${pageContext.request.contextPath}/main">MySite</a></h1>
			<ul>
			
			<c:choose>
				<c:when test="${empty authUser }">  <!-- 로그인 이전의 화면 -->
					<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a><li>
					<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a><li>
				</c:when>
				
				<c:otherwise> <!-- 로그인 이후의 화면 -->
					<li><a href="${pageContext.request.contextPath}/user/modifyform">회원정보수정</a><li>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a><li>
					<li>${authUser.name }님 안녕하세요 ^^;</li> <!-- authUser.name 이렇게 써도되지만  페이지 , 리퀘스트,세션, 어플리케이션 순으로  4개 영역 모두 다 찾아서 값을 가지고온다. -->
				</c:otherwise>
			
			</c:choose>
			
			</ul>
		</div>