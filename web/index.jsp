<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>试卷组成系统</title>
	<meta content="IE=5.0000" http-equiv="X-UA-Compatible">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>     

<body >
	<!-- 导航条 -->
	<div class="navbar navbar-fixed-top">
		<div style="margin-top: 12px; margin-left: 30px; float: left;">
			<a href="http://www.gdpu.edu.cn/">
				<img style="height: 50px;" src="${pageContext.request.contextPath}/image/index/logo3.png">
			</a>
		</div>
	</div>

	<!-- 主体 -->
	<div class="container">
		<div id="mainbody">
			<div class="text">广东药科大学</div>
			<img src="${pageContext.request.contextPath}/image/index/logo.png" style="width: 120px; margin-bottom: 5px;" >
			<div class="text">试题选择与审核系统</div>	
			<c:choose>
			<c:when test="${sessionScope.user.userType==3}">
			<div class="text" style="color: #00ffff">
				欢迎，系主任：${sessionScope.user.realname}&nbsp;&nbsp;
			</div>
			<button class="btn btn-primary" type="button" onclick="shenhe()" id="shenhe">试卷审核</button>
			<button class="btn btn-primary" type="button" onclick="logout()" id="logout">登出</button>
			</c:when>
			<c:when test="${sessionScope.user.userType==2}">
			<div class="text" style="color: #00ffff">
				欢迎，教师：${sessionScope.user.realname}&nbsp;&nbsp;
			</div>
			
			<button class="btn btn-primary" type="button" onclick="chuti()" id="chuti">我要出题</button>
			<button class="btn btn-primary" type="button" onclick="chakan()" id="chakan">我的试卷</button>
			<button class="btn btn-primary" type="button" onclick="logout()" id="logout">登出</button>
			
			</c:when>
			<c:otherwise>
			<form method="post" action="${pageContext.request.contextPath}/login.action" class="form-horizontal">
				<input name="username" type="text" placeholder="账号" class="form-control input-username" autocomplete="off">
				<input name="password" type="password" placeholder="密码" class="form-control input-password" autocomplete="off">
				<div class="tip">
					<c:if test="${requestScope.message!=null}">${requestScope.message}</c:if>
				</div>
				<input type="submit" class="btn btn-primary" value="登录">
			</form>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</body></html>

