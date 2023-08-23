<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<head>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <header>
        <jsp:include page="header.jsp" />
    </header>


    <h1>Shelter 글쓰기</h1>
    <!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
    <form method="post" action="/shelter/writepro" enctype="multipart/form-data">
<!--         <label for="id">ID</label> -->
<!--         <input type="text" id="id" name="id" required><br> -->
        
        <label for="name">Name</label>
        <input type="text" id="name" name="name" required><br>
        
        <label for="title">Title</label>
        <input type="text" id="s_title" name="s_title" required><br>
        
        <label for="content">Content</label>
        <textarea id="content" name="s_content" required></textarea><br>
        
        <label for="imageFile">Image</label>
		<input type="file" id="imageFile" name="file" accept="image/*" ><br>
        
        <button type="submit">글쓰기</button>
    </form>

    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</body>
</html>
