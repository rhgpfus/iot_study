<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<title>게시판</title>
<script>
function callback(result){
	result = JSON.parse(result);
	var str = "";
	for(var i=0, max=result.length;i<max;i++){
		var b = result[i];
		str +=b.bNum+","+ b.title +"," + b.content + "," + b.regDate + "," + b.writer + "<br>";
	}
	$("#r_div").html(str);
}
$(document).ready(function(){
	var param = "?command=list";
	param = encodeURI(param);
	var au = new AjaxUtil("list.board",param,"post");
	au.changeCallBack(callback);
	au.send();
})
</script>

</head>
<body>
<div id="r_div"></div>
<table border="1">
	<tr align="center">
		<td>게시판번호</td>
		<td>제목</td>
		<td>내용</td>
		<td>생성일자</td>
		<td>글쓴이</td>
	</tr>
	<c:forEach var="bi" items="${boardList}">
		<tr align="center">
			<td><c:out value="${bi.bNum}"/></td>
			<td><c:out value="${bi.title}"/></td>
			<td><c:out value="${bi.content}"/></td>
			<td><c:out value="${bi.regDate}"/></td>
			<td><c:out value="${bi.writer}"/></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
