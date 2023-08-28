<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>

    <script>
		$(() => {
			//로그인 실패 메세지
			let m = '${msg}';
			if(m != '') {
				alert(m)
			}
		})
	</script>

<body>
	<form action="/profile/hos/update" method="post">
		<input type="hidden" name="BeforeCode" value="${hos.code}">
		<input type="text" name="code" id="code" placeholder="코드" value="${hos.code}" required><br>
		<input type="text" name="hostitle" placeholder="병원명" value="${hos.hostitle}" readonly><br>
		연락처:<select id="hosphone">
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
		<input type="text" id="hosphone1" oninput="formatPhoneNumberPart(this); formatPhoneNumber();" oninput="formatPhoneNumber()" maxlength="4" required>
		<input type="text" id="hosphone2" oninput="formatPhoneNumberPart(this); formatPhoneNumber();" oninput="formatPhoneNumber()" maxlength="4" required><br>
	    <input type="hidden" id="hosNumber" name="hosphone">
	    <input type="text" id="hosaddr" name="hosaddr" placeholder="병원주소" value="${hos.hosaddr}" readonly><br>
	    <h3>영업시간 선택</h3>
	    시작시간:<select name="hoshour" id="hoshour">
			<% for (int i = 0; i < 24; i++) { %>
			    <option value="<%= String.format("%02d", i) %>"><%= String.format("%02d", i) %></option>
			<% } %>
	    </select>시
	    <select name="hosmin" id="hosmin">
			<% for (int i = 0; i < 60; i++) { %>
			    <option value="<%= String.format("%02d", i) %>"><%= String.format("%02d", i) %></option>
			<% } %>
	    </select>분
	   	종료시간:<select name="hoshour2" id="hoshour2">
			<% for (int i = 0; i < 24; i++) { %>
			    <option value="<%= String.format("%02d", i) %>"><%= String.format("%02d", i) %></option>
			<% } %>
	    </select>시
	    <select name="hosmin2" id="hosmin2">
			<% for (int i = 0; i < 60; i++) { %>
			    <option value="<%= String.format("%02d", i) %>"><%= String.format("%02d", i) %></option>
			<% } %>
	    </select>분
	    예약단위:<select name="mincheck">
	    	<option value = "15">15</option>
	    	<option value = "30">30</option>
	    	<option value = "45">45</option>
	    	<option value = "60">60</option>	    	
	    </select>분
	    <br>
	    병원종류:<select name="hostype">
	    	<option value = "일반">일반</option>
	    	<option value = "특수">특수</option>
	    </select><br>
	    <label for = "attachments">업로드</label>
		<input type = "file" name="attachments" id="attachments" accept=".jpg, .jpeg, .png, .gif">
        <input type = "text" class="upload-name" value="파일선택" readonly><br>
        <h6 style = "color : red;'">수정할 파일이 없다면, 첨부하지 않으셔도 됩니다.</h6>
	    <input type="submit" id="submit" value="수정">
	</form>
	
	<script>
    	// 전화번호 부분을 합치는 함수
	    function formatPhoneNumber() {
	        const prefix = $('#hosphone').val();
	        const part1 = $('#hosphone1').val();
	        const part2 = $('#hosphone2').val();
	
	        const phoneNumber = prefix + "-" + part1 + "-" + part2;
	        $('#hosNumber').val(phoneNumber);
	    }
    	
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
	
	    // 정규식을 사용하여 입력값을 숫자로 제한하는 함수
	    function formatPhoneNumberPart(input) {
	        input.value = input.value.replace(/\D/g, '');
	    }
	    
	    $('#submit').click(function(event) {
	        // 필요한 입력 요소들
	        const hosphone1 = $('#hosphone1').val();
	        const hosphone2 = $('#hosphone2').val();
	        const hoshour = $('#hoshour').val();
	        const hosmin = $('#hosmin').val();
	        const hoshour2 = $('#hoshour2').val();
	        const hosmin2 = $('#hosmin2').val();

	        // hosphone1과 hosphone2의 길이 검사
	        if (hosphone1.length < 3 || hosphone2.length < 4) {
	            alert('전화번호를 올바르게 입력해주세요.');
	            event.preventDefault(); // 서버로의 전송 중지
	            return;
	        }

	        // 영업시간 검사
	        if (hoshour > hoshour2 || (hoshour === hoshour2 && hosmin >= hosmin2)) {
	            alert('올바른 영업시간을 선택해주세요.');
	            event.preventDefault(); // 서버로의 전송 중지
	            return;
	        }
	    });
    </script>
</body>
</html>