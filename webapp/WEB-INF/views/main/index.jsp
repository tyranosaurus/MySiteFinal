<!-- 태그지시자 입력, 이걸해야 JSTL 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- 이걸 선언해줘야 JSTL 중 core를 사용가능 prefix는 name에 해당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 중 fomat 를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  <!-- JSTL 중 function 를 사용가능 -->

<%@ page contentType="text/html;charset=UTF-8" %>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<%-- <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include> 기존 html 방식 --%>
 		<c:import url="/WEB-INF/views/include/header.jsp"/>  <!-- jstl 방식 -->
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="${pageContext.request.contextPath}/assets/images/OhMyGirl.jpg" width="560">
					<h2>Welcome to 최영진's  mysite!!!</h2>
					<p>
						This site is practice site for Web-Programming.
						Menu has site-introduction, guestbook and board.
						Also, I made this wonderful site by using Java, MySQL, JSP&Servlet and JSTL.
						<strong>Finally, I want to thank to my teacher Dae-hyuk Ann.</strong>
						Enjoy my site!!
						<br><br>
						<a href="${pageContext.request.contextPath}/gb?a=list"><strong>Go to Guestbook!!</strong></a><br>
					</p>
				</div>
			</div>
		</div>
			<%-- <jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include> 기존 html 방식 --%>
			<c:import url="/WEB-INF/views/include/navigation.jsp"/> <!-- jstl 방식 -->
			
			<%-- <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include> 기존 html 방식 --%>
			<c:import url="/WEB-INF/views/include/footer.jsp"/> <!-- jstl 방식 -->
	</div>
</body>
</html>