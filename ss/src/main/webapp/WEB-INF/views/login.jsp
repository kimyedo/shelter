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
<style>
/*로그인 헤더*/
.login-header{
	font-size: 37px; /*글씨크기 */
    font-weight: bold;	/*굵게 */
}
/*아이디 상자 크기*/
.login-input{
width: 60%;/*넓이*/
height: 42px;/*높이*/

}
/* 버튼 위치*/
.button-container {
    position: fixed;
    bottom: 739px;
    right: 677PX;
 	display: flex;
 
}

    
    
/*버튼 너비,높이*/
.button {
	background-color : white;
	color:black;
	width: 232px; /* 회원,비회원 버튼 너비 조정 */
    height: 55px;  /* 회원,비회원 버튼 높이 조정 */
     font-weight: bold;
}
/*버튼*/
.active {
	background-color : #006600;
	color:white;
}
 /*상자 위치 조정*/
  .box{
 	position: fixed;
	top: 25%;
	left: 40%;
	height: auto;
	width: 25%; /* 너비 조정 */
	padding: 20px; /* 상자 내부의 간격을 위한 패딩 추가 */
	background-color: whitesmoke; /* 배경색 설정 */
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	
	
}

 

/*로그인 버튼 위치 */
.login-btn{
   position: fixed; 
    top: 45px;   
    left: 23%;     
    transform: translateX(-50%);
  
	
    
}
/*로그인 버튼 모양*/
 .login-btn{
 	text-align: center;
    background: whitesmoke;
    position: relative;
    border: none;
    display: inline-block;
    padding: 11px 80px;/*버튼 높이,넓이*/
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;/*글자크기*/
    transition: 0.25s;
   	background-color:#006600/*버튼컬러*/
   	
    
}
/*회원가입 일반회원 버튼 모양*/
.membership{
    position: relative;
    border: none;
    display: inline-block;
    padding: 5px 12px;/*버튼 높이,넓이*/
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 400;/*글자 굵기*/
    font-size:13px;/*글자 크기*/   
    transition: 0.25s;
   	background-color:#006600;/*버튼컬러*/
}
/*회원가입 병원회원 버튼 모양*/
.membership2{
    position: relative;
    border: none;
    display: inline-block;
    padding: 5px 12px;/*버튼 높이,넓이*/
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 400;/*글자 굵기*/
    font-size:13px;/*글자 크기*/   
    transition: 0.25s;
   	background-color:#006600;/*버튼컬러*/

}
/*회원가입 병원회원 버튼 위치 */
.membership2{
position:absolute;
top:50%;
right:22.5%;
}
/*회원가입 일반회원 버튼 위치 */
.membership{
position:absolute;
top:230px;
right:115px;
}
</style>

<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>




<section>
		<div>
		<div class="button-container">
	<button id = "button1" class = "button">일반회원</button>
	<button id = "button2" class = "button">병원회원</button>
</div>
		
        <div class="content">
         <div class="box">
            <form id = "1" class="login-form 1" action="/member/login" method="post">
 				<h2 class="login-header">로그인</h2><br><br>
 				<input type="text" class="login-input" name="id" autofocus required placeholder="아이디"><br><br>
 				<input type="password" class="login-input" name="pw" required placeholder="비밀번호"><br><br><br>	
 				<input type="submit" class="login-btn" value="로그인" style="color: white; margin-top : 10px;"><br><br><br><br>
            </form>
            <button onclick="location.href='/member/join'" class="membership2 mem" style="margin-top : 30px; color: white;">회원가입</button>
            <button onclick="location.href='/member/findPw1'" class = "membership2 mem" style = "margin-top : 70px; color : white;">비밀번호 찾기</button>
            
           	<form id = "2" class = "1" action = "/hos/login" method = "get">
           	 	<h2 class="login-header">로그인</h2><br><br>
 				<input type="text" class="login-input" name="code" autofocus required placeholder="코드"><br><br>
 				<input type="submit" class="login-btn" value="로그인" style="color: white; margin-top : 10px;"><br><br><br><br>
 				<button onclick="location.href='/hos/join'"class="membership2 hos"style="color: white;">회원가입</button>
			</form>
        </div>
        </div>
        </div>
    </section>

<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
<script>
$('#button1').addClass('active');
$('#2').hide();
$('#button1').click(()=>{
	$('#1').show();
	$('#2').hide();
	$('.mem').show();
	$('.hos').hide();
	$('#button1').addClass('active');
	$('#button2').removeClass('active');		
})
$('#button2').click(()=>{
	$('#2').show();
	$('#1').hide();
	$('.mem').hide();
	$('.hos').show();
	$('#button1').removeClass('active');
	$('#button2').addClass('active');
})
</script>
<script src="/js/header.js"></script>
</body>
</html>