<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>코드:${hos.code}</div>
	<div>병원명:${hos.hostitle}</div>
	<div>병원번호:${hos.hosphone}</div>
	<div>병원주소:${hos.hosaddr}</div>
	<div>시작시간:<span id="start-time"></span></div>
	<div>종료시간:<span id="end-time"></span></div>
	<div>예약단위:${hos.mincheck}</div>
	<div>병원종류:${hos.hostype}</div>
	<div>병원이미지:<br><img src="${imageUrl}"></div>
	<div><a href="/profile/hos/update">수정</a></div>
	
	<script>
		function formatWithLeadingZero(number) {
			return (number < 10 ? "0" : "") + number;
		}
		
		var hoshour = ${hos.hoshour};
		var hosmin = ${hos.hosmin};
		var hoshour2 = ${hos.hoshour2};
		var hosmin2 = ${hos.hosmin2};
		
		document.getElementById("start-time").textContent = formatWithLeadingZero(hoshour) + ":" + formatWithLeadingZero(hosmin);
		document.getElementById("end-time").textContent = formatWithLeadingZero(hoshour2) + ":" + formatWithLeadingZero(hosmin2);
	</script>
	
	<script src="/js/header.js"></script>
</body>
</html>