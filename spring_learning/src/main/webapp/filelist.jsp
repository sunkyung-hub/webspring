<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<String> filenm = (ArrayList)request.getAttribute("filenm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첨부파일 리스트</title>
</head>
<body>
<table border="1">
<tr>
	<th>파일명</th>
	<th>삭제</th>
</tr>
<%
int w = 0;
while(w < filenm.size()){
	
%>
<tr>
	<td><%=filenm.get(w) %></td>
	<td><input type="button" value="삭제" onclick="file_del('<%=filenm.get(w) %>')" > </td>
</tr>
<%
w++; 
}
%>
</table>
<form id ="fm" method="post" action="./filedel.do">
<input type="hidden" name="fnm" value="">
</form>
</body>
<script>
function file_del(fnm){
	fm.fmnm.value = fnm;
	fm.submit();
}
</script>
</html>