<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
.menu{
width: 500px;
height: 30px;
display: flex;
flex-direction: row;
justify-content: center;
align-content: center;
}

.menu > span{
display: block;
border: 1px solid black;
width: 100px;
height: inherit;
text-aligh: center;
line-hegiht: 30px;
}
</style>
<title>상단 페이지</title>
<!-- div, span사이에 form 사용하면 css 깨짐 -->
아이디: ${mid}
고객명: ${param.user}
<div class="menu">
<!-- <span>수강신청</span>
<span>학습지원센터</span>
<span>나의 강의실${param.z}</span>-->
</div>
