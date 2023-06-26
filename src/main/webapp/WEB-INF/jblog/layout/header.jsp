<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>JBlogWeb</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="/webjars/jquery/3.6.4/jquery.min.js"></script>
    <!-- include summernote css/js -->
    <link href="/webjars/summernote/0.8.10/summernote-bs4.css" rel="stylesheet">
    <script src="/webjars/summernote/0.8.10/summernote-bs4.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Main</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mynavbar">
                <c:if test="${sessionScope.principal == null}">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/login">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/insertUser">회원가입</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.principal != null}">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/user/updateUser">회원 상세</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/post/insertPost">포스트 등록</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/logout">로그아웃</a>
                        </li>
                    </ul>
                </c:if>
                <form class="d-flex">
                    <input class="form-control me-2" type="text" placeholder="Search">
                    <button class="btn btn-primary" type="button">Search</button>
                </form>
            </div>
        </div>
    </nav>
