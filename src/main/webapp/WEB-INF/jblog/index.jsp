<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ include file="./layout/header.jsp" %>

<div class="container-fluid mt-3">
    <h3>Navbar Forms</h3>
    <p>You can also include forms inside the navigation bar.</p>
    <h1>안녕 ${username}!!!</h1>
</div>

<div class="container mt-3">
    <c:if test="${!empty postList}">
        <div class="card">
            <c:forEach var="post" items="${postList.content}">
                <div class="card-body">
                    <h4 class="card-title">${post.title}</h4>
                    <a href="#" class="btn btn-secondary">상세보기</a>
                </div>
            </c:forEach>
        </div>

        <ul class="pagination justify-content-between">
            <li class="page-item"><a class="page-link" href="?page=${postList.number - 1}">이전 페이지</a></li>
            <li class="page-item"><a class="page-link" href="?page=${postList.number + 1}">다음 페이지</a></li>
        </ul>
    </c:if>
</div>

<%@ include file="./layout/footer.jsp" %>