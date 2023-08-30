<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
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
					<input type="hidden" name = "addr" id = "addr">
				    <select id ="addrs">
				    	<option value="서울">서울</option>
				    	<option value="부산">부산</option>
				    	<option value="인천">인천</option>
				    	<option value="대구">대구</option>
				    	<option value="대전">대전</option>
				    	<option value="광주">광주</option>
				    	<option value="울산">울산</option>
				    	<option value="세종">세종</option>
				    	<option value="경기">경기</option>
				    	<option value="충청">충청</option>
				    	<option value="전라">전라</option>
				    	<option value="경상">경상</option>
				    	<option value="강원">강원</option>
				    	<option value="제주">제주</option>
				    </select>
				    <input type="text" id="addr2" placeholder="상세주소" required><br>
					연락처:<select id="phone">
						<option value="010">010</option>
					    <option value="02">02</option>
					    <option value="031">031</option>
					    <option value="032">032</option>
					    <option value="042">042</option>
					    <option value="043">043</option>
					    <option value="044">044</option>
					    <option value="051">051</option>
					    <option value="052">052</option>
					    <option value="053">053</option>
					    <option value="054">054</option>
					    <option value="055">055</option>
					    <option value="061">061</option>
					    <option value="062">062</option>
					    <option value="063">063</option>
					    <option value="064">064</option>
					</select>
					<input type="text" id="phone1" oninput="formatPhoneNumberPart(this); formatPhoneNumber();" oninput="formatPhoneNumber()" maxlength="4" required>
					<input type="text" id="phone2" oninput="formatPhoneNumberPart(this); formatPhoneNumber();" oninput="formatPhoneNumber()" maxlength="4" required><br>
				    <input type="hidden" id="Number" name="phone">
					<select name = "gender">
						<option value = "남자">남자</option>
						<option value = "여자">여자</option>
						<option value = "비공">비공</option>
					</select>
					<input type="hidden" value="nor" name="type"><br>
					<input type="submit" class="login-btn"	value="가입" disabled><br>
				</form>
			</div>
		</section>
	</div>

<script>
	$('#checkId').click(() => {
	    let id = $('#id').val();
	    
	    $.ajax({
	        method: "POST",
	        url: "/idcheck",
	        data: { id: id },
	    }).done(function(res) {
	        if (res === 0) {
	            alert("사용가능한 아이디입니다.");
	            $('.login-btn').prop('disabled', false);
	            $('#checkId').prop('disabled', true);
	            $('#id').prop('readonly', true);
	        } else {
	            alert("중복된 아이디입니다.");
	        }
	    }).fail(function(err) {
	        alert("서버 작업 중 오류가 발생하였습니다.");
	    });
	});
	
	    	// 전화번호 부분을 합치는 함수
	    function formatPhoneNumber() {
	        const prefix = $('#phone').val();
	        const part1 = $('#phone1').val();
	        const part2 = $('#phone2').val();
	
	        const phoneNumber = prefix + "-" + part1 + "-" + part2;
	        $('#hosNumber').val(phoneNumber);
	    }
	
	    // 정규식을 사용하여 입력값을 숫자로 제한하는 함수
	    function formatPhoneNumberPart(input) {
	        input.value = input.value.replace(/\D/g, '');
	    }
	    
	    $('#submit').click(function(event) {
	        // 필요한 입력 요소들
	        const phone1 = $('#phone1').val();
	        const phone2 = $('#phone2').val();
	        const addrs = $('#addrs').val();
	        const addr2 = $('#addr2').val();
	        $('#addr').val(addrs + " " + addr2);
	        // phone1과 phone2의 길이 검사
	        if (phone1.length < 3 || phone2.length < 4) {
	            alert('전화번호를 올바르게 입력해주세요.');
	            event.preventDefault(); // 서버로의 전송 중지
	            return;
	        }
	    });
</script>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>