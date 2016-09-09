<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--检索题目-->
<script type="text/javascript">
    $(function(){
        getCourseId();
    });
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/selectQuestion.js"></script>

<!--模态框-->
<div class="fade modal" id="myModal" style="top:200px;">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content">
            <form id="newQuestionForm" onsubmit="return updateQuestion()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aira-hidden="true">&times;</span>
                    </button>
                    <h2 class="modal-title">题目正文</h2>
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
    <h1>检索题目</h1>
    <form class="form-horizontal" id="questionform">
        <div class="form-group">
            <label class="col-xs-2 control-label">题号</label>
            <div class="col-xs-10">
                <input name="questionId" type="text" class="form-control" placeholder="题号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">题型</label>
            <div class="col-xs-10">
                <select name="questionType" id="questionType">
                    <option value="choice">选择题</option>
                    <option value="fillin">填空题</option>
                    <option value="essay">简答题</option>
                    <option value="discuss">问答题</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">科目</label>
            <div class="col-xs-10">
                <select name="courseId" class="courseId">
                    <option value="">--------------</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">题目</label>
            <div class="col-xs-10">
                <input name="questionContent" type="text" class="form-control" placeholder="输入题目关键字">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">章节</label>
            <div class="col-xs-10">
                <input name="questionSection" type="text" class="form-control" placeholder="输入章节：1、3、5">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">难度</label>
            <div class="col-xs-10">
                <select name="diff">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <div class="button">
                    <label>
                        <input type="button" class="btn btn-default" onclick="searchQuestion(1)" value="搜索">
                    </label>
                </div>
            </div>
        </div>
    </form>
</div>

<div id="searchResult"></div>
<div id="questionButtom" class="table-buttom"></div>
