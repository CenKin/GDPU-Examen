<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container main jumbotron">
    <h1>欢迎来到组卷管理系统</h1><br>
    <h4>快速开始 <span class="badge"/>></h4><br>
    <button class="btn btn-primary" onclick="selectQuestion('admin/toSelectQuestion.action')">题目检索</button>
    <button class="btn btn-primary" onclick="selectCourse('admin/toSelectCourse.action')">科目检索</button>
    <button class="btn btn-primary" onclick="jump('admin/toSelectUser.action')">用户检索</button>
    <button class="btn btn-primary" onclick="logout()">登出</button>
</div>
