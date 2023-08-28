<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    
     <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<form class = "content" action="findPw1" method="post">
	<div class="textbox">
  		<input id="text" name="id" required type="text" />
  		<label for="text">이름</label>
 	</div>
  
	</div><br><br>
   	<input type="submit" id="check" value="비밀번호찾기">
</form>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>