<%@page import="org.springframework.web.servlet.ModelAndView" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
request로 setAttribute를 만든것이 아니기 때문에 값을 받아올 수 없음
<%
	ModelAndView mv = (ModelAndView)request.getAttribute("mv");
%>
--%> 
<!-- 

 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- Controller에서 ModelAndView를 사용했을 때, jstl로 출력해야함 ${} --%>
상품명: ${pdnm}<br>
상품코드: ${pcode}<br>
상품가격: ${pmoney}<br>
</body>
</html>