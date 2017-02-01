<!-- 태그지시자 입력, 이걸해야 JSTL 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- 이걸 선언해줘야 JSTL 중 core를 사용가능 prefix는 name에 해당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 중 fomat 를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  <!-- JSTL 중 function 를 사용가능 -->

<%@page import="com.estsoft.mysite.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite4/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite4/user/modify">
				
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${ authUserVo.name }">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
						
						<c:choose>
							<c:when test="${ authUserVo.gender == 'M' }">
								<fieldset>
									<legend>성별</legend>
									<label>여</label> <input type="radio" name="gender" value="F">
									<label>남</label> <input type="radio" name="gender" value="M" checked="checked">
						</fieldset>
							</c:when>
							
							<c:when test="${ authUserVo.gender == 'F' }">
								<fieldset>
									<legend>성별</legend>
									<label>여</label> <input type="radio" name="gender" value="F" checked="checked">
									<label>남</label> <input type="radio" name="gender" value="M">
						</fieldset>
							</c:when>
							
						</c:choose>
					
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>