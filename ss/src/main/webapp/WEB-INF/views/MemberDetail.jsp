<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="/css/style.css">
<title>Insert title here</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>






   	<form action = "/member/update" method = "get">
  		<table>
  			<tr>
  				<td><hr>아이디 : ${mb.id}<hr></td>
  			</tr>
  			<tr>
  				<td><hr>이름 : ${mb.name}<hr></td>
  			</tr>
  			<tr>
  				<td><hr>생년월일 : ${mb.birth}<hr></td>
  			</tr>
  			<tr>
  				<td><hr>주소 : ${mb.addr}<hr></td>
  			</tr>
  			<tr>
  				<td><hr>이메일 : ${mb.email}<hr></td>
  			</tr>
  			<tr>
  				<td><hr>전화번호 : ${mb.phone}<hr></td>
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