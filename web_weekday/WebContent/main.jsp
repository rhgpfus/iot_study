<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<%
		if(session.getAttribute("user") != null) {
			Map<String, String> user = (Map) session.getAttribute("user");
			String id = user.get("id");
			String name = user.get("name");
			String user_no = user.get("user_no");
			String hobby = user.get("hobby");
			String admin = user.get("admin");
	%>

	<%=id%>님 반갑슴다
	<br> 유저번호 :
	<%=user_no%><br> 이름:
	<%=name%><br> 취미 :
	<%=hobby%><br>
	<form action="logout.user" method="post" id="btnForm">
		<input type="button" value="로그아웃" onclick="move(this)"> <input
			type="button" value="회원수정" onclick="move(this)"> <input
			type="button" value="회원탈퇴" onclick="move(this)">
		<%
			if (admin != null && admin.equals("1")) {
		%>
		<input type="button" value="회원리스트" onclick="move(this)">
		<%
			}
		%>
		<input type="hidden" value="logout" name="command" id="command">
	</form>
	<script>
	$("input").click(function(obj){
		alert(this.getAttribute("value"));
	})
	/*
		function move(obj) {
			if (obj.value == "회원수정") {
				location.href = "/modify.jsp";
				return;
			} else if (obj.value == "회원탈퇴") {
				document.getElementById("command").value = "delete";
			} else if (obj.value == "회원리스트") {
				document.getElementById("command").value = "list";
			}
			obj.form.submit();
		}
	*/
	</script>
	<%
		} else {
			response.sendRedirect("/login.jsp");
		}
	%>
</body>
<script>
	function movePage() {
		location.href = "modify.jsp";
	}
</script>
</html>