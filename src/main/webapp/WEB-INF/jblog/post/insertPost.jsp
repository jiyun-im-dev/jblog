<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
  <form>
    <div class="mb-3 mt-3">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
    </div>
    <div class="mb-3">
      <label for="content">Content:</label>
      <textarea class="form-control" rows="5" id="content"></textarea>
    </div>
  </form>

  <button id="btn-insert" class="btn btn-primary">포스트 등록</button>
</div>

<script src="/src/main/resources/static/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>