<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %> 
<!-- db연결하는 jstl 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL기초 - (DB연결)</title>
</head>
<body>

<!-- DB연결 -->
<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" 
url="jdbc:mysql://localhost:3306/mrp" user="project" password="a12345"/>
<%-- DB연결 확인 
${db} --%>

<%-- <sql:query var="ps" sql="select * from event" dataSource="${db}"/> --%>

<!-- DDL2 방식 -->
<%-- 쿼리문은 하나인데, 테이블만 바뀜
<cr:set var="tables" value="event"/>
<sql:query var="ps" dataSource="${db}">
select * from ${tables}
</sql:query>
--%>

<sql:query var="ps" dataSource="${db}">
select * from event
</sql:query>
<!-- rows: DB rows의 전체값을 의미함
	fn: 결과값에 옵션 형태로 함수를 적용하여 결과에 대한 사항을 변화시킴(태그가 없음) -->
<cr:forEach var="row" items="${ps.rows}">
고객명: ${row.ename}, 
이메일: ${row['email']}, 
등록일: ${fn:substring(row['ejoin'], 0, 10)}  <br>
</cr:forEach>
</body>
</html>