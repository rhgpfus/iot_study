<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="/js/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	$("input[type='button']").click(function(){
		var value = this.value;
		if(value=="회원탈퇴"){
			$("#command").val("delete");
		}
		this.form.submit();
	})
})
</script>
<body>
<%
Map<String,String> user = null;
if(session.getAttribute("user")!=null){
	user = (Map<String, String>)session.getAttribute("user");
}
if(user==null){
%>
<form action="/login.user" method="post">
아이디 : <input type="text" name="id" id="id"><br>
비밀번호 : <input type="password" name="pwd" id="pwd"><br>
<input type="hidden" name="command" id="command" value="login">
<input type="submit" value="로그인">
</form>
<%
}else{
	String user_no = user.get("user_no");
	String id = user.get("id");
	String name = user.get("name");
	String hobby = user.get("hobby");
	out.println(user.get("id") + "님 어서옵쇼");
	String result = "<table border=1>";
	result += "<tr>";
	result += "<td>유저번호</td>";
	result += "<td>" + user_no + "</td>";
	result += "</tr>";
	result += "<tr>";
	result += "<td>아이디</td>";
	result += "<td>" + id + "</td>";
	result += "</tr>";
	result += "<tr>";
	result += "<td>이름</td>";
	result += "<td>" + name + "</td>";
	result += "</tr>";
	result += "<tr>";
	result += "<td>취미</td>";
	result += "<td>" + hobby + "</td>";
	result += "</tr>";
	result += "</table>";
	out.println(result);
	%>
	<form action="some.user" method="post">
	<input type="button" value="로그아웃">
	<input type="button" value="회원탈퇴">
	<input type="button" value="회원수정">
	<input type="hidden" name="command" id="command" value="logout">
	<input type="hidden" name="user_no" value="<%=user_no%>">
	</form>
	<%
}
	%>

</body>
</html>