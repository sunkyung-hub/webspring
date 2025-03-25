<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jstl 엔진 -->
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- jstl 각종함수 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<!-- jstl DB 관련사항 -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
	<!-- jstl 부가옵션 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
	String user = "홍길동"; //jsp 변수
	//jsp session 생성
	HttpSession hs = request.getSession();
	hs.setAttribute("ssdata", "15881004");

	//jsp session 출력
	String se = (String)hs.getAttribute("ssdata");
	out.print(se);
	
	String tels = (String)hs.getAttribute("tel");
	out.print(tels);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법1</title>
</head>
<body>
<!-- jstl 태그 형태 -->
<!-- core 태그 속성 중 set(setAttribute) -->
<cr:set var="a" value="강감찬"/>
<input type="text" name="mname" value="${a}">
<cr:set var="nm" value="<%=user %>"/>
고객명: ${nm}
<cr:out value="값출력 합니다."/> <!-- out.print()와 같음 -->
<br><br>
<p>JSP Session값</p>

<!-- JSTL로 세션을 생성하는 방식 scope: session, request, page -->
<cr:set var="tel" value="0210041004" scope="session"/>
JPS에 session 데이터: ${ssdata}<br>
세션 데이터: ${tel}
<br><br><br>
<%
	String money = "50000";
%>
<cr:set var="kk" scope="request"><%=money %></cr:set>
${kk}

</body>
</html>