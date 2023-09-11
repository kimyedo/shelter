<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items = "${shbDto}" var = "shbDto">
		<table border = "1" style = "border-collapse: collapse;">
			<tr>
				<td>구매번호 : ${shbDto.buynum}</td>
			</tr>
			<tr>
				<td>구매자 : ${shbDto.id}</td>
			</tr>
			<tr>
				<td>구매품목 : ${shbDto.item}</td>
			</tr>
			<tr>
				<td>총금액 : ${shbDto.price}</td>
			</tr>
			<tr>
				<td>구매수량 : ${shbDto.ea}</td>
			</tr>
			<tr>
				<td>구매일자 : ${shbDto.buydate}</td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>