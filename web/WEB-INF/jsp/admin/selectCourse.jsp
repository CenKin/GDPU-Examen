<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--初始化页面-->
<script>
    $(function(){
        getCollId();
        getMajorId();
    });
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/selectCourse.js"></script>

<!--模态框-->
<div class="fade modal" id="myModal" style="top:200px;">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content">
            <form id="newCourseForm" onsubmit="return updateCourse()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aira-hidden="true">&times;</span>
                    </button>
                    <h2 class="modal-title">科目</h2>
                    <h4 class="modal-title" id="modal-title"></h4>
                </div>
                <div class="modal-body" id="modal-body"></div>
                <div class="modal-footer" id="modal-footer">
                    <input type="submit" class="btn btn-default" value="修改提交"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="main">
    <h1>检索科目</h1>
    <form class="form-horizontal" id="courseForm" onsubmit="return submitForm(1);">
        <div class="form-group">
            <label class="col-xs-2 control-label">学院</label>
            <div class="col-xs-10">
                <select id="collId" name="collId">
                    <option value="">--------------------</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">专业</label>
            <div class="col-xs-10">
                <select id="majorId" name="majorId">
                    <option value="">------------------</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">科目名</label>
            <div class="col-xs-10">
                <input name="courseName" type="text" class="form-control" placeholder="输入科目名关键字">
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <div class="button">
                    <label>
                        <input type="submit" class="btn btn-default" value="搜索">
                    </label>
                </div>
            </div>
        </div>
    </form>
</div>

<div id="searchResult"></div>
<div id="courseButtom" class="table-buttom"></div>