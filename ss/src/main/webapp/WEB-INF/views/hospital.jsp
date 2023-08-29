<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동물병원 리스트</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script src="/js/jquery-3.7.0.min.js"></script>
     <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
    <c:if test="${showAlert}">
        <script>
            alert("로그인이 필요합니다.");
        </script>
    </c:if>

<div class="scrollable-container">
    <table class="table">
        <thead>
            <tr>
                <th>병원 이름</th>
                <th>전화번호</th>
                <th>주소</th>
                <th>운영시간</th>
                <th>병원 유형</th>
                <th>예약</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${hos}" var="hospital">
                <tr>
                    <td>${hospital.hostitle}</td>
                    <td>${hospital.hosphone}</td>
                    <td>${hospital.hosaddr}</td>
                    <td>${hospital.hoshour}:${hospital.hosmin} ~ ${hospital.hoshour2}:${hospital.hosmin2}</td>
                    <td>${hospital.hostype}</td>
                    <td><a href="/reserves_hospital?hostitle=${hospital.hostitle}">예약</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<button id="hosReservesList">예약 목록</button>

<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>

<script>
	$("#hosReservesList").click(function() {
		location.href = "/reserves_List";
	})
</script>
</html>