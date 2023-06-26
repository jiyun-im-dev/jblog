<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
  <form>
    <div class="mb-3 mt-3">
      <label for="username">Username:</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
    </div>
    <div class="mb-3">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="pswd">
    </div>
  </form>

  <button id="btn-login" class="btn btn-primary">로그인</button>
</div>

<script src="/js/login.js"></script>
<%-- static 폴더가 리소스로 등록되어 있어서 이렇게만 써도 작동하는 듯..?--%>
<%-- "/src/main/resources/static/js/login.js" 이것도 될 듯--%>
<%@ include file="../layout/footer.jsp" %>