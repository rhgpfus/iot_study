<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<title>게시판</title>
<script>
function callback(result){
	
	$("#table").bootstrapTable({data : result});
}

function getBoardList(content){
	var param = {};
	param["command"] = "list";
	if(content){
		param["content"] = content;
	}
	param = JSON.stringify(param);
	var ja = new JqAjax("list.board", param);
	ja.changeFunc(callback);
	ja.send();
}
$(document).ready(function(){
	getBoardList();
	$("#btnSearch").click(function(){
		var searchStr = $("#searchStr").val().trim();
		if(!searchStr){
			alert("검색할 내용을 적엉");
			$("#searchStr").val("");
			$("#searchStr").focus();
			return;
		}
		getBoardList(searchStr);
	});
})
</script>
</head>
<body>
내용 : <input type="text" name="searchStr" id="searchStr"/>
<input type="button" value="검색" id="btnSearch"/>
<table border="1" id="table" data-height="460" class="table table-boardered table-hover">
	<thead>
		<tr>
			<th data-field="bNum" class="text-center">게시판번호</th>
			<th data-field="title" class="text-center">제목</th>
			<th data-field="content" class="text-center">내용</th>
			<th data-field="writer" class="text-center">게시자</th>
			<th data-field="regDate" class="text-center">게시일자</th>
		</tr>
	</thead>
	<tbody id="r_tbody">
	</tbody>
</table>
</body>
</html>
