<!-- 태그지시자 입력, 이걸해야 JSTL 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- 이걸 선언해줘야 JSTL 중 core를 사용가능 prefix는 name에 해당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 중 fomat 를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  <!-- JSTL 중 function 를 사용가능 -->

<%@page import="com.estsoft.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
	pageContext.setAttribute("newLine", "\r\n");
%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guestbook/insert" method="post">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="message"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
								
				<c:set var="count" value="${fn:length(list) }"/>
				<c:forEach items="${list}" var="vo" varStatus="status">
					<li>
						<table>
							<tr>
								<td>[${count - status.index }]</td>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<td><a href="${pageContext.request.contextPath}/guestbook/deleteform/${vo.no}">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
							<%-- 	<%=vo.getMessage().replace("\r\n", "<br/>") %> --%>
								${fn:replace(vo.message, newLine, "<br/>") }	
								</td>
							</tr>
						</table>
						<br>
					</li>
				</c:forEach>
									
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>