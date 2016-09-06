<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    window.onload = function() {
        var linkList=window.parent.document.getElementsByTagName("link");//获取父窗口link标签对象列表
        var head=document.getElementsByTagName("head").item(0);
        //外联样式
        for(var i=0;i<linkList.length;i++) {
            var l=document.createElement("link");
            l.rel = 'stylesheet'
            l.type = 'text/css';
            l.href=linkList[i].href;
            head.appendChild(l);
        }
    }
</script>
<div class="container main jumbotron">
    <h1>欢迎来到组卷管理系统</h1><br>
    <h4>快速开始 <span class="badge"/>></h4><br>
    <button class="btn btn-primary" onclick="selectQuestion('admin/toSelectQuestion.action')">题目检索</button>
    <button class="btn btn-primary" onclick="selectCourse('admin/toSelectCourse.action')">科目检索</button>
    <button class="btn btn-primary" onclick="jump('admin/toSelectUser.action')">用户检索</button>
    <button class="btn btn-primary" onclick="logout()">登出</button>
</div>
