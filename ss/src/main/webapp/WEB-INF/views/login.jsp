<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="/css/style.css">
<script>
	$(()=>{
		//로그인 실패 메세지
		let m='${msg}';
		if(m!=''){
			alert(m)
		}
	})
</script>

</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>



	<section>
         <div class="box">
            <form action="/member/login" method="get" id = "1" class="login-form 1">
 				<h2 class="login-header">로그인</h2><br><br>
 				<input type="text" class="login-input" name="id" autofocus required placeholder="아이디"><br><br>
 				<input type="password" class="login-input" name="pw" required placeholder="비밀번호"><br><br><br>	
 				<input type="submit" class="login-btn" value="로그인" style="color: white;" margin-top=:10px;><br><br><br><br>
 				<button onclick="location.href='/member/join'" class="membership"style="color: white;">회원가입</button>
 				     
            </form>
            
           	<form id = "2" class = "1" action = "/hos/login" method = "get">
           	 	<h2 class="login-header">로그인</h2><br><br>
 				<input type="text" class="login-input" name="code" autofocus required placeholder="코드"><br><br>
 				<input type="submit" class="login-btn" value="로그인" style="color: white;" margin-top=:10px;><br><br><br><br>
 				<button onclick="location.href='/hos/join'"class="membership2"style="color: white;">회원가입</button>
			</form>
        </div>
    </section>

<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

</body>
</html>