<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "/js/jquery-3.7.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/css/style.css">
<title>Insert title here</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
 	<form action = "/member/update" method = "post">
 		<table>
 			<tr>
 				<td><hr>아이디 : <input type = "text" name = "id" value="${mb.id}" readonly><hr></td>
 			</tr>
 			<tr>
 				<td><hr>비밀번호 : <input type = "text" name = "pw" value="" required><hr></td>
 			</tr>
 			<tr>
 				<td><hr>이름 : <input type = "text" name = "name" value="${mb.name}"><hr></td>
 			</tr>
 			<tr>
 				<td><hr>생년월일 : <input type = "text" name = "birth" value="${mb.birth}"><hr></td>
 			</tr>
 			<tr>
 				<td><hr>주소 : <input type = "text" name = "addr" value="${mb.addr}"><hr></td>
 			</tr>
 			<tr>
 				<td><hr>이메일 : <input type = "text" name = "email" value="${mb.email}"><hr></td>
 			</tr>
 			<tr>
 				<td><hr>전화번호 : <input type = "text" name = "phone" value="${mb.phone}"><hr></td>
 			</tr>
 		</table>
 	
 		<div class="btn-area">
             <input type="submit" class="btn-write" value="U">
             <input type="button" class="btn-write"
                    value="B" onclick="backbtn()">
        </div>
 	</form>
 	
 	<script>
		function backbtn() {
			history.back();
		}
	</script>
	<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>