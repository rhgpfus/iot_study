<%@ include file="/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>회원정보수정</title>
</head>
<body>
<script>
$(document).ready(function(){
	var params = {};
	params["user_no"] = "<%=request.getParameter("user_no")%>";
	params = "?command=view&param=" + JSON.stringify(params);
	params = encodeURI(params);
	var au = new AjaxUtil("update.user",params,"post");
	au.changeCallBack(callback);
	au.send();
})
function callback(result){
	var obj = JSON.parse(result);
	$("#name").val(obj.name);
	$("#id").val(obj.id);
	var hobbies = obj.hobby.split(",");
	for(var i=0, max=hobbies.length;i<max;i++){
		$("input[value='" + hobbies[i] + "']").prop("checked", true);
	}
	
	$("#user_no").val(obj.user_no);
}
function check(){
	var pwdObj = $("#pwd").val().trim();
	var nameObj = $("#name").val().trim();
	if(pwdObj==""){
		alert("다 적엉");
		$("#pwd").val("");
		$("#pwd").focuse();
		return false;
	}
	if(nameObj==""){
		alert("다 적엉");
		$("#name").val("");
		$("#name").focuse();
		return false;
	}
	return true;
}
</script>
<form action="/sign.user" method="post" onsubmit="return check()">
	<table border=1>
		<tr>
			<td colspan="2" align="center">회원정보수정</td>
		</tr>
		<tr align="center">
			<td>아이디</td>
			<td><input type="text" name="id" id="id" readonly></td>
		</tr>
		<tr align="center">
			<td>비밀번호</td>
			<td><input type="password" name="pwd" id="pwd"></td>
		</tr>
		<tr align="center">
			<td>이름</td>
			<td><input type="text" name="name" id="name"></td>
		</tr>
		<tr>
			<td align="center">취미</td>
			<td>
				수면<input type="checkbox" name="hobby" value="수면" >
				음악<input type="checkbox" name="hobby" value="음악" >
				영화<input type="checkbox" name="hobby" value="영화">
				요리<input type="checkbox" name="hobby" value="요리" >
				여행<input type="checkbox" name="hobby" value="여행">
				게임<input type="checkbox" name="hobby" value="게임">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="회원정보수정" ></td>
		</tr>
	</table>
	<input type="hidden" name="command" value="update">
	<input type="hidden" name="user_no" value="<%=user.get("user_no")%>">
</form>
</body>
</html>