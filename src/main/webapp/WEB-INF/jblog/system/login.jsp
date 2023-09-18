<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
  <form action="/auth/securitylogin" method="post">
    <div class="mb-3 mt-3">
      <spring:message code="user.login.form.username"/>
      <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" value="test">
    </div>
    <div class="mb-3">
      <label for="password"><spring:message code="user.login.form.password">: </label>
      <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" value="test123">
    </div>
    <button id="btn-login" class="btn btn-primary"><spring:message code="user.login.form.login_btn"></button>
  </form>
</div>

<%@ include file="../layout/footer.jsp"%>