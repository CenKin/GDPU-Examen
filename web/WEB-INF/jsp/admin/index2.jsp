<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>组卷管理平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layer/skin/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layer/skin/layer.ext.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layer/laypage/skin/laypage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webuploader/webuploader.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/extend/layer.ext.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/laypage/laypage.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/app.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webuploader/webuploader.js"></script>
    <script type="text/javascript">
        $(function(){
            init();
        });
    </script>
    <script type="text/javascript">
        function addQuestion(url){
            jump(url);
            getCourseId();
        }
        function selectQuestion(url){
            jump(url);
            getCourseId();
        }
        function addCourse(url){
            jump(url);
        }
        function selectCourse(url){
            jump(url);
        }
    </script>
</head>
<body>
    <div class="sidebar-menu">
        <div class="sidebar-menu-logo" style="padding-left: 0;">
            <img src="image/admin/min_logo.png" style="width: 150px;">
            <div class="text">组卷管理平台</div>
        </div>
        <!--选项卡部分-->
        <div class="panel-group" id="sidebar">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <a data-toggle="collapse" data-parent="#sidebar" href="#questionMeun">
                        <h4 class="panel-title">
                            <div class="text">
                                <span class="glyphicon glyphicon-folder-open"></span> 题库管理
                            </div>
                        </h4>
                    </a>
                </div>
                <div id="questionMeun" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <ul>
                            <li><a href="javascript:void(0)" onclick="addQuestion('admin/toAddQuestion.action')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-plus-sign"></i> 增加题目</a></li>
                            <li><a href="javascript:void(0)" onclick="selectQuestion('admin/toSelectQuestion.action')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-search"></i> 高级检索</a></li>
                            <li><a href="javascript:void(0)" onclick="jump('admin/toImportQuestion.action')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon glyphicon-import"></i> 题库导入</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <a data-toggle="collapse" data-parent="#sidebar" href="#courseMenu">
                        <h4 class="panel-title">
                            <div class="text">
                                <span class="glyphicon glyphicon-th-list"></span> 科目管理
                            </div>
                        </h4>
                    </a>
                </div>
                <div id="courseMenu" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a href="javascript:void(0)" onclick="addCourse('admin/toAddCourse.action')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-plus-sign"></i> 增加科目</a></li>
                            <li><a href="javascript:void(0)" onclick="selectCourse('admin/toSelectCourse.action')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-search"></i> 检索科目</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <a data-toggle="collapse" data-parent="#sidebar" href="javascript:void(0)" onclick="jump('admin/toSelectPaper.action')">
                        <h4 class="panel-title">
                            <div class="text">
                                <span class="glyphicon glyphicon-tasks"></span> 试卷管理
                            </div>
                        </h4>
                    </a>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <a data-toggle="collapse" data-parent="#sidebar" href="#userMenu">
                        <h4 class="panel-title">
                            <div class="text">
                                <span class="glyphicon glyphicon-user"></span> 用户管理
                            </div>
                        </h4>
                    </a>
                </div>
                <div id="userMenu" class="panel-collapse collapse">
                    <div class="panel-body">
                        <ul>
                            <li><a href="javascript:void(0)" onclick="jump('admin/toAddUser.action')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-plus-sign"></i> 增加用户</a></li>
                            <li><a href="javascript:void(0)" onclick="jump('admin/toSelectUser.action')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-search"></i> 检索用户</a></li>
                            <li><a href="javascript:void(0)" onclick="jump('admin/listUser.action?identity=2')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-leaf"></i> 教师管理</a></li>
                            <li><a href="javascript:void(0)" onclick="jump('admin/listUser.action?identity=3')" class="btn btn-default sidebar-child"><i class="glyphicon glyphicon-eye-open"></i> 主任管理</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a data-toggle="collapse" data-parent="#sidebar" href="javascript:void(0)" onclick="logout()">
                        <h4 class="panel-title">
                            <div class="text">
                                <span class="glyphicon glyphicon-log-out"></span> 登出
                            </div>
                        </h4>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!--主体-->
   <div class="mainbody">
         <div class="container" id="main"></div>
   </div>
</body>
</html>