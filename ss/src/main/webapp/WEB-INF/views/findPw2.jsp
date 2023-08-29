<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
     <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<form action = "/member/findPw2" method = "post">
<input type = "hidden" value = "${id}" name = "id">
<hr>새 비밀번호 : <input type = "password" name = "pw" id="pw" required><hr>
<hr>비밀번호 확인 : <input type = "password" id="pw2" required><hr> <input type = "submit" id="pwChk" value="확인"> 
<button id="submit" disabled>비밀번호 변경</button>
</form>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
<script>
$('#pwChk').click(function(event){
	if($('#pw').val() === $('#pw2').val()){
		$('#submit').prop('disabled',false);
		$('#pwChk').prop('disabled',true);
		$('#pw').prop('readonly',true);
		$('#pw2').prop('readonly',true);
		event.preventDefault();
		return;
	} else {
		event.preventDefault();
		return;
	}
})


</script>
</body>

</html>