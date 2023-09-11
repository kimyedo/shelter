<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 상품 작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

<link rel="stylesheet" href="/css/style.css">
<script>
let m='${msg}';
if(m!=''){
	alert(m)
}
</script>
</head>

<body>
<div class="wrap">
    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>
    
  
    
    <h1>쇼핑몰 상품 작성</h1>
    <form action="/shopping/write" method="post" enctype="multipart/form-data">
        <label for="item">상품명:</label>
        <input type="text" id="item" name="item"><br><br>
        
        <label for="price">가격:</label>
        <input type="text" id="price" name="price" maxlength = "20"><br><br>
        
        <label for="ea">수량:</label>
        <input type="text" id="ea" name="ea" maxlength = "6"><br><br>
        
        <label for="sh_content">상품 설명:</label><br>
        <textarea id="sh_content" name="sh_content" rows="4" cols="50"></textarea><br><br>
        
          <label for = "attachments">업로드</label>
		<input type = "file" name="attachments" id="attachments" accept=".jpg, .jpeg, .png, .gif">
        <input type = "text" class="upload-name" value="파일선택" readonly><br>

        <input type="submit" value="작성">
    </form>
    <script>
	$('#attachments').on('change', function() {
		//파일 선택 후 열기 버튼을 누르면 change event 발생
		console.log($('#attachments'));
		let files = $('#attachments')[0].files;
		console.log(files);
		
		let fileName = '';
		if(files.length > 1) {
			fileName = files[0].name + ' 외 ' + (files.length-1) + '개';
		} else if(files.length == 1) {
			fileName = files[0].name;
		} else {
			fileName = "파일 선택";
		}
		$(".upload-name").val(fileName);
	});
	
    // 숫자만 입력 가능하도록 정규식을 적용
    $(document).ready(function () {
        $('#price, #ea').on('input', function () {
            // 입력값에서 숫자 이외의 문자를 모두 제거
            $(this).val($(this).val().replace(/[^0-9]/g, ''));
        });
    });
    </script> 
    <footer>
        <jsp:include page="footer.jsp"></jsp:include>
    </footer>
</div>
</body>
</html>
