<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ include file="./layout/header.jsp" %>

  <div class="container-fluid mt-3">
    <h3>Navbar Forms</h3>
    <p>You can also include forms inside the navigation bar.</p>
    <h1>안녕 ${username}!!!</h1>
  </div>

  <div class="container mt-3">
    <h2>Card titles, text, and links</h2>
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">포스트 제목</h4>
        <p class="card-text">포스트 내용</p>
        <a href="#" class="btn btn-secondary">상세보기</a>
      </div>
    </div>
  </div>

<%@ include file="./layout/footer.jsp" %>