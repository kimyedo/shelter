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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.7.0.min.js"></script>
</head>
<body>
<head>
<link rel="stylesheet" href="/css/style.css">
<script>

	$(()=>{
		//로그인 실패 메세지
		let m='${msg}';
		if(m!=''){
			alert(m)
		}
	})
</script>
</head>
<body>

	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<h1>쇼핑몰</h1>

	<div align="center">
		<button id="write">글쓰기</button>

	</div>

	<!-- 리스트 반복문  -->
	<div class="row">
		<div class="row justify-content-center">
			<c:forEach items="${shList}" var="shopping">
			<c:if test = "${shopping.ea > 0 || mb.type == 'admin'}">
				<div class="col-md-3 mb-4">
					<form action="/shopping/cart?item=${shopping.item}" method="post">
						<div class="card">
							<div class="row no-gutters">
								<div class="col-md-4">
									<c:choose>
										<c:when test="${shopping.sysFileName != null}">
											<a href="/shopping/detail?item=${shopping.item}"><img
												src="${shopping.sysFileName}" class="card-img" alt="Image"></a>
										</c:when>
										<c:otherwise>
											<p class="card-text">이미지가 없습니다!</p>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</form>
					<div class="col-md-6">
						<div class="card-body">
							<h5 class="card-title">상품명 ${shopping.item}</h5>
							<td>가격:<fmt:formatNumber value="${shopping.price}"
									pattern="###,###,###" var="formattedPrice" /></td>
							<td>${formattedPrice}원</td>
							<p class="card-text">재고수량 ${shopping.ea} 개</p>
						</div>

						<form method="post" action="/shopping/addcart">
							<input type="hidden" value="${shopping.item}" name="item">
							<input type="hidden" value="${mb.id}" name="id"> <input
								type="hidden" name="ea" class="ea">
						</form>
						<c:if test = "${mb.type == 'admin'}">
							<form method="get" action="/shopping/update">
								<input type="hidden" value="${shopping.item}" name="item">
								<button class="delete">수정</button>
							</form>
						</c:if>
						
						<form method="post" action="/shopping/delete">
							<input type="hidden" value="${shopping.item}" name="item">
							<button class="delete">삭제</button>
						</form>
						<tr align="center">
							<td colspan="2">
								<form name="form1" method="post" action="/shopping/buy">
									<input type="hidden" name="id" value="${mb.id}"> 
									<input type="hidden" name="item" value="${shoppingDto.item}">
									<input type="hidden" name="price" id="price"> 
									<input type="hidden" name="ea" class="ea"> 
								
								</form>
								<form method="post" action="/shopping/addcart">
									<input type="hidden" value="${shopping.item}" name="item">
									<input type="hidden" value="${mb.id}" name="id"> 
									<select	name="ea" class="ea">
										<c:forEach var="i" begin="1" end="${shopping.ea}" >
											<option value="${i}">${i}</option>
										</c:forEach>
									</select>개 <input type="submit" class="checkcart" value="장바구니에 담기">
								</form>
							</td>
						</tr>
					</div>
				</div>
			</c:if>
			</c:forEach>
		</div>
	</div>

	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

	<script>
	let mbtype = '${mb.type}';
	$("#warn").hide();
	
	$('.checkcart').click(function(event) {   	
    	if (confirm("상품을 장바구니에 담곘습니까?")) {
    		
    	} else {
    		event.preventDefault();
    	}
    });
	
	// 계산 함수 정의
	function calculateTotal(selectElement) {
	    var selectedValue = $(selectElement).val(); // 선택한 값 가져오기
	    var price = ${shoppingDto.price}; // 상품 가격 가져오기
	    var total = selectedValue * price; // 계산
	
	    // 계산된 가격을 hidden 필드와 해당 위치의 모든 input 필드에 설정
	    $(".ea").val(selectedValue);
	    $("#price").val(total);
	}
	
	// 초기에도 한 번 호출하여 기본 값을 설정
	$(".ea").each(function() {
	    calculateTotal(this);
	});
	
	$('.delete').hide();
	if (mbtype === "admin") {
	    $('.delete').show();
	}


    $('#write').hide();
    if (mbtype === "admin") {
        $('#write').show();
    }
    
    $('#write').click(() => {
        location.href = '/shopping/write';
    });
    
    $('.delete').click(function(event) {    	
    	if (confirm("삭제하시겠습니까?")) {
    		
    	} else {
    		event.preventDefault();
    	}
    });
    

	// select 요소가 변경될 때 계산 함수 호출
// select 요소가 변경될 때 이벤트 리스너를 추가

	
</script>


</body>
</html>
