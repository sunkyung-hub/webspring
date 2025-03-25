<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	//String data[] = new String[]{"hong", "kim", "park"};
	//request.setAttribute("data",data); //작성하면 ${data}바로 사용가능
	
	ArrayList<String> data = new ArrayList();
	data.add("hong1");
	data.add("kim2");
	data.add("park3");
	request.setAttribute("data",data);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초3(반복문 - foreach)</title>
</head>
<body>

<table border="1">
<tr>
<!-- forEach: JSTL 유일하게 배열 및 반복문으로 사용하는 속성
begin: 시작값 / end: 종료값 -->

<%-- forEach 기본형태 구조
<cr:forEach var="z" begin="1" end="5">
	<td>${z}</td>
</cr:forEach>
--%>

<%-- items: 모든 배열값을 받는 속성 
- ${data}: setAttribute의 값이 있을 경우 $
- <%=data%>: jsp의 변수를 그대로 사용한 것
배열값을 사용하여 출력하는 역할 begin="0"으로 시작해야함 (배열번호 0~)
--%>

<%-- <cr:forEach var="z" items="${data}"> --%>
<cr:forEach var="z" items="<%=data %>" begin="0" end="3"> <%-- 4개의 데이터가 나옴. 계산했던 데이터보다 데이터가 많아도 오류가 나지 않음 --%>
<%-- 반복문 안 조건문 --%>	
	<cr:if test="${z != 'kim2'}"> <%-- 배열에서 kim2를 제외하고 출력 --%>
	<td>${z}</td>
	</cr:if>
</cr:forEach>

</tr>
</table>

</body>
</html>