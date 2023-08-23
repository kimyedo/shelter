<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>


<!-- 입찰가입력란 -->
<style>
 input::-webkit-outer-spin-button,
 input::-webkit-inner-spin-button{
 -webkit-appearance:none;
 margin: 0;
 }
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
 <h1>auctionBid.jsp</h1>
 <!-- 메뉴 -->

 <div class="container" id="listcontainer"> <!--   -->
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

 									<c:if test="${empty AcList}">
                    <tr>
                      <td colspan="8">데이터가 없습니다</td>
                    </tr>
                  </c:if>
                  <c:if test="${not empty AcList}">
<%--                     <c:forEach items="${AcList}" var="auction"> --%>
                      <tr>
                        <td>${auction.acnum}</td>
                        <td>${auction.id}</td>
                        <td>
<%--                           <a href="/detail/${auction.acnum}">${auction.image}</a> --%>
                        </td>
                        <td>${auction.animal}</td>
                        <td>${auction.minprice}</td>
                        <td>${auction.toprice}</td>
                        <td>${auction.starttime}</td>
                        <td>${auction.endtime}</td>
                      </tr>

<%-- </c:forEach> --%>
</c:if>




                  </p>
                  <form>
                  <input type="number" id="bidNum" placeholder="입찰가 입력">
					 <button id="bidBtn" class="btn btn-outline-success"
					onclick= >입찰</button>
					 </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
 
 
 <footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>