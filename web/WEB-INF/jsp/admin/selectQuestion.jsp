<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--检索题目-->
<script type="text/javascript">
    var rows = 7;
    function searchQuestion(curr){
        var questionType = $("#questionType").val();
        var str = "";
        $("#searchResult").html(str);
        $.ajax({
            type: "POST",
            url : "${pageContext.request.contextPath}/admin/selectQuestion.action?offset="+curr+"&rows="+rows,
            data: $("#questionform").serialize(),
            dataType: "json",
            success: function(page){
                var pages = page.pageCount;

                if(pages==0){
                    layer.alert('没有任何记录',{icon:2});
                    return;
                }

                //表头
                str = '<table class="table table-bordered my-table">'
                        +'<tr>'
                            +'<th width="334px">题号</th>'
                            +'<th>题目</th>'
                            +'<th width="120px">科目</th>'
                            +'<th width="90px">章节</th>'
                            +'<th width="60px">难度</th>'
                            +'<th width="100px">操作</th>'
                        +'</tr>'
                    +'</table>'
                    +'<table class="table table-striped table-bordered my-table-hover my-table">'
                    +'<tbody id="questionTable">';

                //表格内容
                $.each(page.pageData, function(i,question){
                    var id = question.id;
                    var content = question.content;
                    var courseName = question.courseName;
                    var section = question.section;
                    var diff = question.diff;

                    str += '<tr>'
                            +'<td width="334px" class="my-table-id">'+id+'</td>'
                            +'<td class="content">'+content+'</td>'
                            +'<td width="120px">'+courseName+'</td>'
                            +'<td width="90px">'+section+'</td>'
                            +'<td width="60px">'+diff+'</td>'
                            +'<td width="100px">'
                            +'<button class="btn btn-primary" data-toggle="modal" data-target="#myModal"'
                            +' onclick="getQuestion(\''+questionType+'\',\''+id+'\')">查看</button></td>'
                });

                str += '</tbody></table>';
                $("#searchResult").html(str);

                //调用分页
                laypage({
                    cont: 'questionButtom',
                    pages: pages,
                    curr: curr || 1,
                    skin: 'molv',
                    jump: function(obj, first){
                        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                            searchQuestion(obj.curr);
                        }
                    }
                })
            },
            error : function(data){
                layer.alert("未知错误，请过段时间再尝试",{icon:2});
            }
        });
    }
</script>
<!--查看题目详情-->
<script>
    function getQuestion(questionType, id){
        var str = "";
        $.ajax({
            type:'GET',
            url :'${pageContext.request.contextPath}/admin/selectOneQuestion.action?questionType='+questionType+'&questionId='+id,
            success: function (page) {
                if(page.pageCount<=0){
                    layer.alert("发生错误", {icon:2});
                }else{
                    var question = page.pageData[0];
                    $("#modal-title").html(question.content);
                    if(questionType=="choice") {
                        str = question.answer1 +
                            '<br/>' + question.answer2 +
                            '<br/>' + question.answer3 +
                            '<br/>' + question.answer4 +
                            '<br/>答案：' + question.rightAnswer + '<br/>';
                    } else if(questionType=="fillin"){
                        str = '答案：' + question.correctionFill + '<br/>';
                    }
                    str = str + '科目：' + question.courseName +
                            '<br/>章节：' + question.section +
                            '<br/>难度：' + question.diff;

                    $("#modal-body").html(str);
                }
            }
        });
    }
</script>

<!--模态框-->
<div class="fade modal" id="myModal" style="top:200px;">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aira-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modal-title"></h4>
            </div>
            <div class="modal-body" id="modal-body"></div>
            <div class="modal-footer" id="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="main">
    <h1>检索题目</h1>
    <form class="form-horizontal" action="#" id="questionform">
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
