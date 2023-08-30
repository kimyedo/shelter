<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src = "/js/jquery-3.7.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link rel = "stylesheet" href = "/css/style.css">


<link rel="shortcut icon" href="#">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>





	<div class="wrap">
		<section>
			<div class="content">
				<form action="/member/join" method="post" class="login-form"
					name="jFrm">
					
					<h2 class="login-header">회원 가입</h2><br>
					<input name="id" type="text" class="login-input" id="id" 
						title="아이디" autofocus placeholder="아이디"> 
					<span id="checkMsg"> </span>
					<input  type="button" id="checkId" class="idcheck-btn" value="중복확인"><br>
					<input name="pw" type="password" class="login-input" 
						title="비밀번호" placeholder="비밀번호"> <br>
					<input name="name" type="text"
						class="login-input"  title="이름" placeholder="이름"> <br>
					<input name="birth" type="text" class="login-input"  title="생일"
						placeholder="생일"> <br>
					<input name="email" type="text" class="login-input"  title="이메일"
						placeholder="이메일"><br>
					<input name="addr" type="text" class="login-input"
						 title="주소" placeholder="주소"><br>
					<input name="phone" type="text" class="login-input"  title="연락처"
						placeholder="연락처"><br>
					<input name="gender" type="text" class="login-input"  title="성별"
						placeholder="성별"><br>
					<input type="hidden" value="nor" name="type"><br>
					<input type="submit" class="login-btn"	value="가입"><br>
				</form>
			</div>
		</section>
	</div>

<script>

</script>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>