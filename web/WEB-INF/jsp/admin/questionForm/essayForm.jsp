<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="form-horizontal" onsubmit="return addQuestion(this);">
    <input type="hidden" name="questionType" value="essay">
    <div class="form-group">
        <label class="col-xs-2 control-label">科目</label>
        <div class="col-xs-10">
            <select name="courseId" class="courseId"></select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-2 control-label">题目</label>
        <div class="col-xs-10">
            <textarea name="content" class="form-control" rows="10" placeholder="题目正文"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-2 control-label">章节</label>
        <div class="col-xs-10">
            <input name="section" type="text" class="form-control" placeholder="输入章节：1、3、5">
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-2 control-label">难度：</label>
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
                    <input type="submit" class="btn btn-default" value="提交"/>
                </label>
            </div>
        </div>
    </div>
</form>