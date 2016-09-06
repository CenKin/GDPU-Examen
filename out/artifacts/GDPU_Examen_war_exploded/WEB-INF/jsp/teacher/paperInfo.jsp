<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设置试卷信息</title>
	<meta content="IE=5.0000" http-equiv="X-UA-Compatible">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/paperDetail.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layer/skin/layer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layer/skin/layer.ext.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/extend/layer.ext.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/examen.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/teacher/paperInfo.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			//加载页面的同时，可以向数据库获取当前试卷的基本可选择的信息
			paperInfo_initPage();
			
			$("#collId").on("change",function(){
				var collId = $("#collId").val();
				paperInfo_collChange(collId);
			});
			$("#majorId").on("change",function(){
				var majorId = $("#majorId").val();
				paperInfo_majorChange(majorId);
			});
		});
	
		function next(){
			$('#paperInfo').submit();
		}
	</script>
</head>
<body>
	<!-- 导航条 -->
	<div class="navbar navbar-fixed-top">
		<div style="margin-top: 12px; margin-left: 30px; float: left;">
			<A href="http://www.gdpu.edu.cn/">
				<img style="height: 50px; " src="${pageContext.request.contextPath}/image/index/logo3.png">
			</A>             
		</div>
	</div>
	
	<div class="container">
		<div id="mainbody">
			<div class="text" style="margin-bottom: 10px;">
				<font size="8">请录入试卷基本信息</font>
			</div>
			<form id="paperInfo" action="${pageContext.request.contextPath}/paper/inputPaperInfo.action" class="form-horizontal" method="post">
				<div class="form-group">
		            <label class="text">学院：&nbsp;
						<select id="collId" name="collId" style="width: 350px;">
							<option value="">-------------------------------</option>
						</select></label>
						
					<br>
						
		            <label class="text">专业：&nbsp;
					<select id="majorId" name="majorId" style="width: 500px;">
						<option value="">-----------------------------------------------</option>
					</select></label>
					
					<br>
					
		            <label class="text">科目：&nbsp;
					<select id="courseId" name="courseId" style="width: 400px;">
						<option value="59E6195BEFDF4586B9D109092CBC94B0">--------------------------------------------</option>
					</select></label>
					
					<br>
					
					<label class="text">卷面类型：&nbsp;
					<select id="paperType" name="paperType">
						<option value="A">A卷</option>
						<option value="B">B卷</option>
					</select></label>
					
					<br>
					
					<label class="text">20&nbsp;
					<select id="fromYear" name="fromYear">
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
					</select></label>
					<label class="text">&nbsp;-&nbsp;20&nbsp;
					<select id="toYear" name="toYear">
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
					</select></label>
					<label class="text">&nbsp;学年&nbsp;</label>
					<label class="text">&nbsp;第&nbsp;
					<select id="useTerm" name="useTerm">
						<option value="一">一</option>
						<option value="二">二</option>
					</select>&nbsp;学期&nbsp;</label>
					
					<br>
					
					<label class="text">使用班级：</label>
					<div>
						<input type="text" id="useClasses" name="useClasses" class="form-control" style="height:80px;font-size:20px;"/>
					</div>
					
					<br>
					
					<div class="btn-group2" style="margin:3% 25%">
						<button class="btn btn-primary" type="button" onclick="toIndex()" style="margin-right:15%;width: 40%;">返回</button>
						<button class="btn btn-primary" type="button" onclick="next()" style="width: 40%;">下一步</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>