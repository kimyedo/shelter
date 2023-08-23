<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<!-- 카드형태 -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<style>
body{
min-width:1920px;
}

.card-text{
	font-size:13.5px;
}

.card{
	potion:center;
	width:600px;
	height:270px;
	margin-top: 50px;
	max-width: 500px;
}


.putimg {
	width: 200px;
	height: 210px;
	float: left;
	margin:15px;
	max-width: 500px;
	
}


table {
	width: 100%;
	height: 200px;
}

#listcontainer {
	width: 25%;
	height: 260px;
}
.search-box{
    position: fixed;
            top: 16%;
            left: 50%;
            transform: translate(-50%, -50%);
            height: 60px;
            width: 100%;
            max-width: 1000px;
            z-index: 999;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 30px;
            border: 1px solid #999999;
}
.card-container {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 100%;
            max-width: 1200px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            z-index: 1;
        }
    

.search-txt{
 margin-top: 20px;
 margin-right:250px;
  padding:0;
  width: 700px;/*검색창 넒이*/
  border:none;
  outline:none; 
  font-size:18px;/*검색창 안글씨*/
  line-height:20px;
   box-sizing: border-box;
   }
   
#search-btn {
  position: absolute;
  top: 50%; /* Adjusted top value to vertically center the button */
  right: 19px;
  padding: 12px;
  background-color: #fff;
  color: black;
  border: none;
  outline: none;
  cursor: pointer;
  transform: translateY(-50%); /* Vertically center the button using translation */
}


 
</style>
<head>
<link rel="stylesheet" href="/css/style.css">

</head>
<body>

	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
		<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>
	
	  <div class="content">
 <div class="search-box">

      <input type="text" class="search-txt" name=""placeholder="동물 이름을 입력하세요"> <button id="search-btn">검색</button>
</div>
 <div class="card-container">
<!-- 리스트 반복문  -->
	 <div class="container mt-5">
    <c:forEach items="${AcList}" var="auction" varStatus="status">
      <c:if test="${status.index % 2 == 0}">
			<!--   -->
        <div class="row">
      </c:if>        
      <div class="col-md-6 mb-4">
        <div class="card">
          <div class="row no-gutters">
            <div class="col-md-4">
              <img class="putimg" src="황민현.jpg" alt="" class="card-img" />            
            </div>
            <div class="col-md-8">
              <div class="card-body">       
                <p class="card-text">
               <c:if test="${empty AcList}">
											<tr>
												<td colspan="8">데이터가 없습니다</td>
											</tr>
										</c:if>
										<c:if test="${not empty AcList}">
											<%--                     <c:forEach items="${AcList}" var="auction"> --%>
											<tr>
												<td>${auction.acnum}</td><br>
												<td>${auction.id}</td><br>
												<td>
													<%--                           <a href="/detail/${auction.acnum}">${auction.image}</a> --%>
												</td>
												<td>${auction.animal}</td><br>
												<td>${auction.minprice}</td><br>
												<td>${auction.toprice}</td><br>
												<td>${auction.starttime}</td><br>
												<td>${auction.endtime}</td>
											</tr>

											<%-- </c:forEach> --%>
										</c:if>
                </p>
                <button type="button" class="btn btn-outline-success">입찰</button>
              </div>
            </div>
          </div>  
        </div>
        
      </div><c:if test="${status.index % 2 == 1 or status.last}">
        </div>       
      </c:if>   
	</c:forEach>
	

	

	<script>
	document.querySelector('#writeBtn').addEventListener('click', ()=> {
		location.href = '/write'
	})
	</script>
</body>
</html>