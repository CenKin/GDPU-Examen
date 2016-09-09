<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function(){
        function tagChange(id){
            $.ajax({
                type: 'get',
                url: '${pageContext.request.contextPath}/admin/getQuestionForm.action?type='+id,
                success : function (data) {
                    $("#"+id).html(data);
                    getCourseId();
                }
            });
        }

        tagChange('choice');
        tagChange('fillin');
        tagChange('essay');
        tagChange('discuss');
    });

    function addQuestion(questionForm){
        var $form = $(questionForm);
        $.ajax({
            type: "POST",
            url : "${pageContext.request.contextPath}/admin/addQuestion.action",
            data: $form.serialize(),
            dataType : "json",
            success:function(result){
                var stateCode = result.stateCode;
                var exception = result.exception;
                var msg = result.msg;
                if(exception==null){
                    layer.alert(msg+"("+stateCode+")",{icon:1});
                    jump('/admin/toAddQuestion.action');
                } else {
                    layer.alert(msg+"("+stateCode+")",{icon:2});
                }
            },
            error:function(data){
                layer.alert("未知错误，请过段时间再尝试",{icon:2});
            }
        });
        return false;
    }
</script>

<div class="main">
    <h1>增加题目</h1>
    <ul id="MyTab" class="nav nav-tabs">
        <li class="active"><a href="#choice" data-toggle="tab">选择题</a></li>
        <li><a href="#fillin" data-toggle="tab" >填空题</a></li>
        <li><a href="#essay" data-toggle="tab" >简答题</a></li>
        <li><a href="#discuss" data-toggle="tab">问答题</a></li>
    </ul>

    <div id="myTabContent" class="tab-content">

        <div class="tab-pane fade in active" id="choice">
        </div>

        <div class="tab-pane fade" id="fillin">
        </div>

        <div class="tab-pane fade" id="essay">
        </div>

        <div class="tab-pane fade" id="discuss">
        </div>

    </div>
</div>
</div>