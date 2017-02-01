<!-- 태그지시자 입력, 이걸해야 JSTL 사용 가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이걸 선언해줘야 JSTL 중 core를 사용가능 prefix는 name에 해당 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- JSTL 중 fomat 를 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- JSTL 중 function 를 사용가능 -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(function() {
		$("#join-form?").submit(function() { // #join-form에 해당되는 부분이 submit될때 실행됨. 즉, submit 버튼을 누르면 실행됨.

			//1. 이름 유효성 체크

			if ($("#name").val() == "") {
				alert("이름 써라")
				$("#name").focus(); // 커서 위치
				return false;
			}
			// 2. 이메일 유효성 체크
			if ($("#email").val() == "") {
				alert("이메일 써라")
				$("#email").focus();
				return false;
			}
			if ($("#img-checkemail").is(":visible") == false) {
				alert("이메일 중복체크를 해야한다.")
				return false;
			}

			//3.패스워드 체크
			//4.약관 체크 유무  // jQuery is checked

			return true;
			alert("여기가 보이면 폼을 서밋하세요 즉, return true; 하세요")
		});
		
		$("#email").change(function() { // #email에 넣어진 값들이 변화(change)하면 함수를 실행시켜라
			$("#btn-checkemail").show();
			$("#img-checkemail").hide();
		});

		$("#btn-checkemail").click(
						function() {
							//console.log("버튼 잘 눌러짐");
							var email = $("#email").val();
							if (email == "") {
								return;
							}

							$.ajax({
										url : "${pageContext.request.contextPath}/user/checkemail?email="
												+ email, //  요청 URL
										type : "get", // 통신방식 get/post 둘중 하나
										dataType : "json", //  수신 데이터 타입
										data : "", //post방식인 경우 서버에 전달할 파라미터 데이터 ex) a=checkemail&email=tyranosaurus@nate.com
										//contentType:"application/json"   // 보내는 데이터가 JSON형식인 경우 , 반드시 post방식인 경우로 보내야함 ex)data 부분 :  "{"a":"checkemail", email:afsdf@naver.com"}"
										success : function(response) { // 성공시 callback

											if (response.result != "success") {
												return;
											}

											if (response.data == false) {
												alert("이미 존재하는 이메일 이다. 다른거 써라");
												$("#email").val("").focus();
												return;
											}

											// 사용가능 한 이메일
											$("#btn-checkemail").hide();
											$("#img-checkemail").show();
										},
										error : function(jqXHR, status, error) { // 실패시 callback
											console.error(status + ":" + error);
										}
									});

						});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post"
					action="${pageContext.request.contextPath}/user/join">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="">


					<spring:hasBindErrors name="user">
						<c:if test="${errors.hasFieldErrors('name') }">
							<br />
							<strong style="color: red"> 
								<c:set var="errorName" value="${errors.getFieldError( 'name' ).codes[0] }" /> <!-- 코드 주소값을 받아서 errorName에 저장 -->
								<spring:message code="${errorName }" text="${errors.getFieldError( 'name' ).defaultMessage }" />
							</strong>
						</c:if>
					</spring:hasBindErrors>

					<label class="block-label" for="email">이메일</label> <input
						id="email" name="email" type="text" value=""> <input
						id="btn-checkemail" type="button" value="id 중복체크"> <img
						id="img-checkemail" style="display: none;"
						src="${pageContext.request.contextPath}/assets/images/check.png">

					<spring:hasBindErrors name="user">
						<c:if test="${errors.hasFieldErrors('email') }">
							<br />
							<strong style="color: red"> 
								<c:set var="errorEmail"	value="${errors.getFieldError( 'email' ).codes[0] }" /> <!-- 코드 주소값을 받아서 errorEmail에 저장 -->
								<spring:message code="${errorEmail }" text="${errors.getFieldError( 'email' ).defaultMessage }" />
							</strong>
						</c:if>
					</spring:hasBindErrors>

					<label class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="F"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="M">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>