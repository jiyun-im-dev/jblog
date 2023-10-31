<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
  <form>
    <input type="hidden" id="id" value="${principal.user.id}">
    <div class="mb-3 mt-3">
      <label for="username">Username:</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" value="${principal.user.username}">
    </div>
    <c:if test="${principal.user.oauth == 'JBLOG'}">
    <div class="mb-3">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="pswd">
    </div>
    </c:if>
    <div class="mb-3 mt-3">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${principal.user.email}">
    </div>
  </form>

  <button id="btn-update" class="btn btn-primary">회원 정보 수정</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>