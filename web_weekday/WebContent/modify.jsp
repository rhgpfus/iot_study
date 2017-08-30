<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<title>Insert title here</title>
</head>
<%
String user_no = request.getParameter("user_no");
%>
<script>
function callback(result){
	$("#id").val(result.id);
	$("#name").val(result.name);
	$("#user_no").val(result.user_no);
	
	if(result.hobby){
		var hobbys = result.hobby.split(",");
		for(var i=0,max=hobbys.length; i<max; i++){
			$("input[value='" + hobbys[i] + "']").prop("checked",true);
		}
	}
}
$(document).ready(function(){
	var param = "?command=viwe&user_no=<%=user_no%>";
	param = encodeURI(param);
	var au = new AjaxUtil(param);
	au.changeCallBack(callback);
	au.send();
})

</script>

<body>
<form action="sigin.user" method="post">
<table border="1" cellspacing="0" cellpadding="0" width="400" align="center">
<tr>
	<td colspan="2"><p align="center"> = 회원 수정 = </p></td>
</tr>
<tr>
	<td align="center">아이디</td>
	<td><input type="text" name="id" id="id"/></td>
</tr>
<tr>
	<td align="center">비밀번호</td>
	<td><input type="password" name="pwd" id="pwd" maxlength="100"/></td>
</tr>
<tr>
	<td align="center">이름</td>
	<td><input type="text" name="name" id="name" maxlength="100" /></td>
<tr>
<tr>
	<td align="center">취미</td>
	<td>
		잠자기<input type="checkbox" name="hobby" value="잠자기"/>
		게임<input type="checkbox" name="hobby" value="게임"/>
		독서<input type="checkbox" name="hobby" value="독서"/>
		음악<input type="checkbox" name="hobby" value="음악"/>
	</td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="수정하기" /><input type="button" value="뒤로" onclick="backPage()"/></td>
</tr> 
</table>
<input type="hidden" name="command" id="command" value="modify"/><br/>
<input type="hidden" name="user_no" id="user_no"/>
</form>
</body>
<script>
function backPage(){
	location.href = "main.jsp";
}
</script>
</html>