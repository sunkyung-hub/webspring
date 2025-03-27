<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배너 리스트 페이지</title>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0">
<thead>
<tr>
	<th><input type="checkbox"></th>
	<th width="80">번호</th>
	<th width="300">배너명</th>
	<th width="100">이미지</th>
	<th width="150">파일명</th>
	<th width="150">등록일</th>
</tr>
</thead>
<tbody>
<cr:forEach var="bn" items="${all}">

<tr height=50>
	<td><input type="checkbox"></td>
	<td></td>
	<td>${bn.bname}</td>
	<td>
	<cr:if test="${bn.file_url == null}">
	</cr:if>
	<cr:if test="${bn.file_url != null}">
	<img src="..${bn.file_url}" style="width:100px; height:50px">
	</cr:if>
	</td>
	<td align="center"><a href="../upload/${bn.file_new}" target="_blank">${bn.file_ori}</a></td>
	<td align="center">${fn:substring(bn.bdate,0,10)}</td>
</tr>

</cr:forEach>
</tbody>

</table>

</body>
</html>