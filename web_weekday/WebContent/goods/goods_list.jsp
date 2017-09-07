<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="tt" value="이게 태그라이브러리입니다."/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<c:forEach items="${goodsList}" var="gi">
			<tr>
				<td><c:out value="${gi.giNum}" /></td>
				<td><c:out value="${gi.giName}" /></td>
				<td><c:out value="${gi.giDesc}" /></td>
				<td><select>
						<c:forEach items="${vendorList}" var="vi">
							<c:set var="sel" value=""/>
							<c:if test="${vi.viNum eq gi.viNum}">
								<c:set var="sel" value="selected"/>
							</c:if>
							<option value="${vi.viNum}" ${sel}>${vi.viName}</option>
						</c:forEach>
				</select>
				<td><c:out value="${gi.giCredat}" /></td>
				<td><c:out value="${gi.giMofdat}" /></td>
				<td><c:out value="${gi.giCreusr}" /></td>
				<td><c:out value="${gi.name}" /></td>
				<td><c:out value="${gi.giMofusr}" /></td>
				<td><c:out value="${gi.name2}" /></td>
			<tr>
		</c:forEach>
	</table>
	상품리스트 페이지에 오셧슴다.
</body>
</html>