<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/teacher/paperDetail.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			//更改科目时，需要检查是否已经出过试卷了
			var preId = "${sessionScope.paper.courseId}";
			paperDetail_change(preId);
		});
		
		function selectType(type){
			var score = "0";
			//跳转之前先检查是否录入了分值,或者分值是否为0
			switch(type){
				case "choice" : score = "${sessionScope.paper.choiceScore}";break;
				case "essay" :  score = "${sessionScope.paper.essayScore}";break;
				case "fillin" : score = "${sessionScope.paper.fillinScore}";break;
				case "discuss" :score = "${sessionScope.paper.discussScore}";break;
			}
			paperDetail_selectType(type,score);
		}
		
		function baocun(){
			var cs = "${fn:length(sessionScope.choiceIds)}";
			var fs = "${fn:length(sessionScope.fillinIds)}";
			var es = "${fn:length(sessionScope.essayIds)}";
			var ds = "${fn:length(sessionScope.discussIds)}";
		
			if(ds=="0" && cs=="0" && es=="0" && fs=="0"){
				layer.alert('未选择任何题目', {icon: 2}, function(index){
					layer.close(index);
				});
			} else{
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/teacher/submitPaper.action",
					dataType:"json",
					success:function(data){
						layer.alert('提交成功',{icon:1},function(index){
							window.location.href = "./index.jsp";
						});
					},
					error:function(data){
						layer.alert('提交失败',{icon:2},function(index){
							window.location.href = window.location.href;
						});
					}
				});
			}
		}
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
	
	<div class="container">
		<div id="mainbody" style="padding:2% 8%;">
			<div class="text" style="margin-bottom: 10px">
				<font size="8">请选择试卷题型及细节</font>
			</div>
			
			<div class="text" >
				试卷总分：${fn:length(sessionScope.choiceIds) * sessionScope.paper.choiceScore + 
		  		fn:length(sessionScope.fillinIds) * sessionScope.paper.fillinScore + 
		  		fn:length(sessionScope.essayIds) * sessionScope.paper.essayScore + 
		  		fn:length(sessionScope.discussIds) * sessionScope.paper.discussScore} 分
			</div>
			
			<div class="div-btn" style="float:left;">
				<div class="top btn" onclick="selectType('choice')">选择题<br>
					<font size="5">
						已选择${fn:length(sessionScope.choiceIds)}题，
					  	每题${sessionScope.paper.choiceScore==null ? 0 : sessionScope.paper.choiceScore}分，
						共${fn:length(sessionScope.choiceIds) * sessionScope.paper.choiceScore}分
					</font>
				</div>
				<div class="left btn" onclick="paperDetail_resetQuestion('choice')">清空题目</div>
				<div class="right btn" onclick="paperDetail_changeQuestionScore('choice')">修改分值</div>
			</div>
			
			<div class="div-btn" style="float:right;">
				<div class="top btn" onclick="selectType('fillin')">填空题<br>
				<font size="5">
					已选择${fn:length(sessionScope.fillinIds)}题，
					每题${sessionScope.paper.fillinScore==null ? 0 : sessionScope.paper.fillinScore}分，
					共${fn:length(sessionScope.fillinIds) * sessionScope.paper.fillinScore}分
				</font>
				</div>
				<div class="left btn" onclick="paperDetail_resetQuestion('fillin')">清空题目</div>
				<div class="right btn" onclick="paperDetail_changeQuestionScore('fillin')">修改分值</div>
			</div>
			
			<div class="div-btn" style="float:left;">
				<div class="top btn" onclick="selectType('essay')">简答题<br>
				<font size="5">
					已选择${fn:length(sessionScope.essayIds)}题，
					每题${sessionScope.paper.essayScore==null ? 0 : sessionScope.paper.essayScore}分，
					共${fn:length(sessionScope.essayIds) * sessionScope.paper.essayScore}分
				</font>
				</div>
				<div class="left btn" onclick="paperDetail_resetQuestion('essay')">清空题目</div>
				<div class="right btn" onclick="paperDetail_changeQuestionScore('essay')">修改分值</div>
			</div>
			
			<div class="div-btn" style="float:right;">
				<div class="top btn" onclick="selectType('discuss')">问答题<br>
				<font size="5">
					已选择${fn:length(sessionScope.discussIds)}题，
					每题${sessionScope.paper.discussScore==null ? 0 : sessionScope.paper.discussScore}分，
					共${fn:length(sessionScope.discussIds) * sessionScope.paper.discussScore}分
				</font>
				</div>
				<div class="left btn" onclick="paperDetail_resetQuestion('discuss')">清空题目</div>
				<div class="right btn" onclick="paperDetail_changeQuestionScore('discuss')">修改分值</div>
			</div>
			
			<div class="clear"></div>
			
			<div class="btn-group2">
				<div class="btn btn-primary" id="reset" onclick="paperDetail_reset()">清空试卷</div>
				<div class="btn btn-primary" id="baocun" onclick="baocun()" style="margin:0% 11%;">保存试卷</div>
				<div class="btn btn-primary" id="back" onclick="toPaperInfo()">上一步</div>
			</div>
		</div>
	</div>
</body></html>
