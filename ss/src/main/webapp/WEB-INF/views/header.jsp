<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <div class="top-bar">
    <div class="content">
        <a href = "/"><img class="logo" src="/images/logo.png"></a>
        <ul>
            <button id = "com">커뮤니티</button>
        
            <ul class = "toggle-ul" id = "com-toggle">
                <li class = "toggle-li">#전체 게시판</li>
                <li class = "toggle-li">#일상 게시판</li>
                <li class = "toggle-li">#질문 게시판</li>
            </ul>
            
 			<c:choose>
                <c:when test="${hos.code != null}">
                	<li><a href="/hospital/myhos" class = "li">내 병원</a></li>
                </c:when>
	            <c:otherwise>   
	            	<li><a href="/hospital" class = "li">동물병원</a></li>
	            </c:otherwise>
            </c:choose>
        
            <button id = "shop">쇼핑</button>
        
            <ul class = "toggle-ul" id = "shop-toggle">
                <li class = "toggle-li">#강아지 용품</li>
                <li class = "toggle-li">#장바구니</li>
            </ul>
        
            <li><a href="/auction/list" class = "li">경매</a></li>
        
            <li><a href="/shelter/list" class = "li">유기견보호소</a></li>
            
            <c:choose>
                <c:when test="${hos.code != null}">
                    <li><a href="/profile/hos" class="li">${hos.hostitle}</a></li>
                    <li><a href="/logout" class="li">로그아웃</a></li>
                </c:when>
                <c:when test="${mb.id == null}">
                    <li><a href="/login" class="li">로그인</a></li>
                </c:when>
                <c:when test="${mb.type eq 'admin'}">
                    <li><a href="/admin/list" class="li">관리자</a></li>
                    <li><a href="/logout" class="li">로그아웃</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/member/detail" class="li">${mb.name}</a></li>
                    <li><a href="/logout" class="li">로그아웃</a></li>
                </c:otherwise>
            </c:choose>  
        </ul>
        <div class="links">
    </div>
  </div>
 </div>