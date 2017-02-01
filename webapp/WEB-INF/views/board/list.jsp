<!-- 태그지시자 입력, 이걸해야 JSTL 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- 이걸 선언해줘야 JSTL 중 core를 사용가능 prefix는 name에 해당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- JSTL 중 fomat 를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  <!-- JSTL 중 function 를 사용가능 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board/list" method="post">
					<input type="text" id="kwd" name="kwd" value="${map.kwd}">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

<%-- 				<c:set var="count" value="${fn:length(list) }"/> --%>
				<c:forEach items="${list }" var="boardVo" varStatus="status">
					<tr>
						<td>${map.listSize - (map.pageNum - 1) * map.listNum - status.index }</td>
						
						<c:choose>
							<c:when test="${boardVo.orderNo == '1' }">							
								<td style="text-align:left"><a href="${pageContext.request.contextPath}/board/view/${boardVo.no }">${boardVo.title }</a></td>
							</c:when>
							
							<c:otherwise> 
								<td style="text-align:left; padding-left:${20*boardVo.depth}px"><img src="${pageContext.request.contextPath}/assets/images/reply.png"><a href="${pageContext.request.contextPath}/board/view/${boardVo.no }">${boardVo.title }</a></td>
							</c:otherwise>
						</c:choose>
						
						<td>${boardVo.name }</td>
						<td>${boardVo.count }</td>
						<td>${boardVo.regDate }</td>
						
				<%-- 	<c:out value="${authUser.no}"></c:out>
						<c:out value="${boardVo.userNo}"></c:out>  --%>  <!-- 출력하는 문법 -->
						
						<td>
						<c:if test="${authUser.no == boardVo.userNo }">

							<a href="${pageContext.request.contextPath}/board/delete/${boardVo.no }" class="del">삭제</a>
						
						</c:if>
						</td>
						
					</tr>
				</c:forEach>
					
				</table>
				




				<!-- 페이징 선생님꺼 -->				
				<div class="pager"> 
 					<ul>
 				
 					
 					
 					
 				<c:choose>
 					<c:when test="${map.firstPage > 1 }">
 					    <li><a href="${pageContext.request.contextPath}/board/list?pageGroup=${map.pageGroup - 1 }&firstPage=${(map.pageGroup-1)*3 - 2 }&pageNum=${(map.pageGroup-1)*3 - 2 }&kwd=${map.kwd}">◀</a></li>
 					</c:when>
 				</c:choose>


 						<c:forEach begin="${map.firstPage }" end="${map.lastPage }" var="i" step="1">
 							 <li><a href="${pageContext.request.contextPath}/board/list?kwd=${map.kwd }&pageGroup=${map.pageGroup }&pageNum=${i }&firstPage=${map.firstPage }">${i } </a></li> 
 						</c:forEach>
 						
 						
 				<c:choose>
 					 <c:when test="${map.lastPage < map.totalPage }">
 					    <li><a href="${pageContext.request.contextPath}/board/list?pageGroup=${map.pageGroup + 1 }&firstPage=${map.pageGroup*3 + 1 }&pageNum=${map.pageGroup*3 + 1 }&kwd=${map.kwd}">▶</a></li> 
 					 </c:when>
 				</c:choose>
 					 
 					 
 					 
 					 
 					
 					</ul> 
 				</div> 
 				
 				
 				
 				
 				
				
				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/writeform" id="new-book">글쓰기</a>
					</div>
				</c:if>
						
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>