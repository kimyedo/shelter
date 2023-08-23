<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>s
<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>


<!-- 카드형태 -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<head>
<link rel="stylesheet" href="/css/style.css">

</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	
<c:forEach items="${sList}" var="auction">
<div class="container">
      <div class="row">
        <div class="container"></div>
          
       
         
          <div class="card">
            <div class="row no-gutters">
              <div class="col-4">
                <img src="황민현.jpg" alt="" class="card-img" />
              </div>
              <div class="col-8">
                <div class="card-body">
	     <span style=" float: right; width: 33%;">
                  <p class="card-text">

 									<c:if test="${empty sList}">
                    <tr>
                      <td colspan="7">데이터가 없습니다</td>
                    </tr>
                  </c:if>
                  <c:if test="${not empty AcList}">
<%--                     <c:forEach items="${AcList}" var="auction"> --%>
                      <tr>
                      <td>${shelter.s_title}</td>
                        <td>${shelter.snum}</td>
                        <td>${shelter.id}</td>
                        <td>
<%--                           <a href="/detail/${auction.acnum}">${auction.image}</a> --%>
                        </td>
                        <td>${shelter.name}</td>
                        <td>${shelter.s_date}</td>
                        <td>${shelter.s_views}</td>
                        <td>${shelter.s_content}</td>
                      </tr>

<%-- </c:forEach> --%>
</c:if>

                  </p>
                    <button type="button" class="btn btn-outline-success">입찰</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
   </c:forEach>

<a href="/shelter/write">글쓰기</a>

	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>
	 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>