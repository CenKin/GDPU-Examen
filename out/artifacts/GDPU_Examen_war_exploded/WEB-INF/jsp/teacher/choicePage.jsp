<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>选择题列表</title>
	<meta content="IE=5.0000" http-equiv="X-UA-Compatible">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layer/skin/layer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layer/skin/layer.ext.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionPage.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/extend/layer.ext.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/laypage/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/examen.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/teacher/question.js"></script>
	<script type="text/javascript">
        $(function() {
            var rows = 7;
            function choicePage(curr) {
                var str = "";
                $.ajax({
                    type: 'get',
                    url: localhost + "/teacher/getQuestionPage.action?type=choice&offset=" + curr + "&rows=" + rows,
                    dataType: "json",
                    success: function (page) {
                        var pages = page.pageCount;

                        if (pages == 0) {
                            layer.alert('没有任何记录', {icon: 2});
                            return;
                        }

                        //用于图片检验的正则表达式
                        var re = /^\/image\/choice\//;

                        //表格内容
                        $.each(page.pageData, function (i, question) {
                            str += '<tr>'
                                    + '<td width="70px">' + (i + 1) + '</td>'
                                    + '<td class="content">'
                                    + question.content + '<br>';

                            //检查有没有图片
                            if (question.imagepath != null && question.imagepath != "") {
                                str += '<image src="' + localhost + question.imagepath + '" style="width:128px;height:72;"/><br>';
                            }

                            str += '&nbsp;&nbsp;';
                            if (re.test(question.answer1)) {
                                str += '<image src="' + localhost + question.answer1 + '" style="width:128px;height:72;"/><br>';
                            } else str += question.answer1 + '<br>';

                            str += '&nbsp;&nbsp;';
                            if (re.test(question.answer2)) {
                                str += '<image src="' + localhost + question.answer2 + '" style="width:128px;height:72;"/><br>';
                            } else str += question.answer2 + '<br>';

                            str += '&nbsp;&nbsp;';
                            if (re.test(question.answer3)) {
                                str += '<image src="' + localhost + question.answer3 + '" style="width:128px;height:72;"/><br>';
                            } else str += question.answer3 + '<br>';

                            str += '&nbsp;&nbsp;';
                            if (re.test(question.answer4)) {
                                str += '<image src="' + localhost + question.answer4 + '" style="width:128px;height:72;"/><br>';
                            } else str += question.answer4 + '<br>';

                            str += '&nbsp;答案：' + question.rightAnswer
                                    + '</td>'
                                    + '<td width="120px">' + question.section + '</td>'
                                    + '<td width="70px">' + question.diff + '</td>'
                                    + '<td width="130px">'
                                    + '<div class="btn btn-primary btn-lg" onclick="addId(\''+question.id+'\',\'choice\')" qId="'+question.id+'">加入</div>'
                                    + '</td>'
                                    + '</tr>';
                        });

                        $("#questionTable").html(str);
                        getSelectIds('choice');

                        //调用分页
                        laypage({
                            cont: 'questionButtom',
                            pages: pages,
                            curr: curr || 1,
                            skin: 'molv',
                            jump: function (obj, first) {
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    choicePage(obj.curr);
                                }
                            }
                        })
                    },
                    error: function (data) {
                        layer.alert("未知错误，请过段时间再尝试", {icon: 2});
                    }
                });
            }
            choicePage(1);
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
	<div class="container">
		<div id="mainbody" style="width: 1200px;margin: 0;">
			<div class="text" style="margin:20px auto;">
				<h1 size="20px" color="#FFF">选择题</h1>
			</div>
		
			<div style="margin-bottom: 20px; height:550px; overflow-y: auto;">
				<table class="table table-bordered my-table">
					<tr>
					  <th width="70px">题号</th>
					  <th>题目</th>
					  <th width="120px">章节</th>
					  <th width="70px">难度</th>
					  <th width="130px">操作</th>
					</tr> 
				</table>
				<table class="table table-striped table-bordered my-table-hover my-table">
					<tbody id="questionTable">
					<!-- 动态填充 -->
					</tbody>
				</table>
			</div>
			<div id="questionButtom" class="table-buttom" style="margin-bottom: 0"></div>
			<br/>
			<div class="btn-group2">
				<button class="btn btn-primary" type="button" onclick="toPaperDetail()">返回题型选择</button>
			</div>
		</div>
	</div>
</body>
</html>