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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/extend/layer.ext.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/laypage/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/app.js"></script>

<div class="container main">
    <h1>增加题目</h1>
    <ul id="MyTab" class="nav nav-tabs">
        <li class="active"><a href="#chocie" data-toggle="tab">选择题</a></li>
        <li><a href="#fillin" data-toggle="tab">填空题</a></li>
        <li><a href="#essay" data-toggle="tab">简答题</a></li>
        <li><a href="#discuss" data-toggle="tab">问答题</a></li>
    </ul>

    <div id="myTabContent" class="tab-content">

        <div class="tab-pane fade in active" id="chocie">
            <form class="form-horizontal" method="post" action="">
                <input type="hidden" name="questionType" value="choice">
                <div class="form-group">
                    <label class="col-xs-2 control-label">科目：</label>
                    <div class="col-xs-10">
                        <select class="courseId" name="courseId"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">题目：</label>
                    <div class="col-xs-10">
                        <textarea name="choiceContent" class="form-control" rows="10" placeholder="题目正文"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">章节：</label>
                    <div class="col-xs-10">
                        <input name="choiceSection" type="text" class="form-control" placeholder="输入章节：1、3、5">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">选项A：</label>
                    <div class="col-xs-10">
                        <input name="answer1" type="text" class="form-control" placeholder="选项A，必填">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">选项B：</label>
                    <div class="col-xs-10">
                        <input name="answer2" type="text" class="form-control" placeholder="选项B，必填">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">选项C：</label>
                    <div class="col-xs-10">
                        <input name="answer3" type="text" class="form-control" placeholder="选项C，没有可不填">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">选项D：</label>
                    <div class="col-xs-10">
                        <input name="answer4" type="text" class="form-control" placeholder="选项D，没有可不填">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">正确答案：</label>
                    <div class="col-xs-10">
                        <input name="rightAnswer" type="text" class="form-control" placeholder="A、B、C、D">
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
                    <label class="col-xs-2 control-label">图片：</label>
                    <div class="col-xs-10">
                        <input name="image" type="file" class="form-control" >
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-10">
                        <div class="button">
                            <label>
                                <button type="button" class="btn btn-default" onclick="submitQuestion()">提交</button>
                            </label>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="tab-pane fade" id="fillin">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/addQuestion.action">
                <input type="hidden" name="questionType" value="fillin">
                <div class="form-group">
                    <label class="col-xs-2 control-label">科目</label>
                    <div class="col-xs-10">
                        <select name="courseId" class="courseId"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">题目</label>
                    <div class="col-xs-10">
                        <textarea name="fillinContent" class="form-control" rows="10" placeholder="题目正文"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">章节</label>
                    <div class="col-xs-10">
                        <input name="fillinSection" type="text" class="form-control" placeholder="输入章节：1、3、5">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">答案</label>
                    <div class="col-xs-10">
                        <input name="correctionFill" type="text" class="form-control" placeholder="输入答案">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-10">
                        <div class="button">
                            <label>
                                <input type="submit" class="btn btn-default" value="提交">
                            </label>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="tab-pane fade" id="essay">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/addQuestion.action">
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
                        <textarea name="essayContent" class="form-control" rows="10" placeholder="题目正文"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">章节</label>
                    <div class="col-xs-10">
                        <input name="essaySection" type="text" class="form-control" placeholder="输入章节：1、3、5">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-10">
                        <div class="button">
                            <label>
                                <input type="submit" class="btn btn-default" value="提交">
                            </label>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="tab-pane fade" id="discuss">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/addQuestion.action">
                <input type="hidden" name="questionType" value="discuss">
                <div class="form-group">
                    <label class="col-xs-2 control-label">科目</label>
                    <div class="col-xs-10">
                        <select name="courseId" class="courseId"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">题目</label>
                    <div class="col-xs-10">
                        <textarea name="discussContent" class="form-control" rows="10" placeholder="题目正文"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">章节</label>
                    <div class="col-xs-10">
                        <input name="discussSection" type="text" class="form-control" placeholder="输入章节：1、3、5">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-10">
                        <div class="button">
                            <label>
                                <input type="submit" class="btn btn-default" value="提交">
                            </label>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
