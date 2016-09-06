<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>我的试卷</title>
	<meta content="IE=5.0000" http-equiv="X-UA-Compatible">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layer/skin/layer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layer/skin/layer.ext.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paperPage.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/extend/layer.ext.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/laypage/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/examen.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/teacher/paperPageForTeacher.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			paperPage(1);
		});
	</script>
</head>
<body>
	<!-- 导航条 -->
	<div class="navbar navbar-fixed-top">
		<div style="margin-top: 12px; margin-left: 30px; float: left;">
			<a href="http://www.gdpu.edu.cn/">
				<img style="height: 50px; " src="${pageContext.request.contextPath}/image/index/logo3.png">
			</a>
		</div>
	</div>

	<!-- 主体 -->  
	<div class="container" style="width:1500px;">
		<div id="mainbody">
			<div class="text" style="margin:20px auto;">
				<h1 size="18px" color="#FFF">我的试卷</h1>
			</div>
			
			<div style="margin-bottom: 20px; height:550px; overflow-y: auto;">
				<table class="table table-bordered my-table">
					<tr>
						<th width="200px">试卷号</th>
						<th>科目</th>
						<th width="120px">编辑时间</th>
						<th width="100px">状态</th>
						<th width="380px">操作</th>
					</tr>
				</table>
				<table class="table table-striped table-bordered my-table-hover my-table">
					<tbody id="paperTable">
					<!-- 动态填充 -->
					</tbody>
				</table>
			</div>
			<br/>
			<div id="paperButtom" class="table-buttom" style="margin-bottom: 0;"></div>
			<br/>
			<div class="btn-group2">
				<button class="btn btn-primary" type="button" onclick="toIndex()">返回首页</button>
			</div>
		</div>
	</div>
</body>
</html>