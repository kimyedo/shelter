 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src ="/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>
    <h1>장바구니</h1>
 <div class="row">
	<div class="row justify-content-center">
	    <c:forEach items="${cartItems}" var="shoppingcart">
	        <div class="col-md-3 mb-4">
	            <div class="card">
	                <div class="row no-gutters">
	                    <div class="col-md-4">
	                        <c:choose>
	                        <c:when test="${shoppingcart.sysFileName != null}">
	                        <form action = "/shopping/detail" method = "post">  
	                                <a href = "/shopping/detail?item=${shoppingcart.item}">
	                                <img src="${shoppingcart.sysFileName}" class="card-img" alt="Image"></a>    
	                        </form>
	                        </c:when> 
	                            <c:otherwise>
	                                <p class="card-text">이미지가 없습니다!</p>
	                            </c:otherwise>
	                        </c:choose>
	                    </div>
	                    </div>
	                </div>
	                    <div class="col-md-6">
	                        <div class="card-body">
	                          <h5 class="card-title">상품명 ${shoppingcart.item}</h5>
						<h6 name="ea" class="ea">
							구매 개수 ${shoppingcart.ea} 개
						</h6>

	                        </div>
	                        <!-- 수량 조정 form -->
							<form method = "get" action = "/shopping/buy">
								<input type="hidden" name="id"  value = "${mb.id}">
								<input type="hidden" name="item"  value = "${shoppingcart.item}">
								<input type="hidden" name="ea"  value = "${shoppingcart.ea}">
								<input type="hidden" name="price"  value = "${shopping.price}">
								<input type="submit" class="itembuy" value="구매하기">
							</form>
	                        
	                        <form method = "post" action = "/shopping/delcart">
	                        	<input type="hidden" name="id" class="update_id" value = "${mb.id}">
	                        	<input type = "hidden" value = "${shoppingcart.item}" name = "item">
	                        <button class="delete">삭제</button>
	                        
	                           
	                    	</form>
	                    </div>
	                </div>
	    </c:forEach>
	</div>
	</div>
<script>

$('.delete').click(function(event) {    	
		if (confirm("삭제하시겠습니까?")) {
			alert("삭제 성공했습니다")
		} else {
			event.preventDefault();
		}
	});
    

</script>
</body>
</html>
