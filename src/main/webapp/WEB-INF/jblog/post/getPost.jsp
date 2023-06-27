<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container border">
    <div>
        <h3>#{post.title}</h3>
    </div>
    <br>
    <div>
        <div>
            ${post.content}
        </div>
    </div>
    <br>
    <div>
        포스트 번호: <span id="id">${post.id}</span><br>
        작성자: ${post.user.username}
    </div>

    <hr>
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <a href="/post/updatepost/${post.id}" class="btn btn-warning">수정하기</a>
    <button id="btn-delete" class="btn btn-danger">삭제하기</button>
</div>

<script src="/js/post.js"></script>
<%@ include file="../layout/footer.jsp" %>