function paperDetail_change(preId){
    $("#course_id").change(function(){
        var newId = $("#course_id").val();
        if(preId!="" && preId!=newId){
            layer.msg('你确定要更换科目吗？更换将清空原有试卷', {
                time: 0,
                btn: ['更换', '取消'],
                yes: function(index){
                    $.ajax({
                        url:localhost+"/teacher/distroyPaper.action?courseId=" + newId,
                        success:function(data){
                            window.location.href=window.location.href;
                        }
                    });
                    layer.close(index);
                },
                cancel: function(index){
                    window.location.href=window.location.href;
                }
            });
        }
    });
}

function paperDetail_selectType(type, score) {
    if(score=="0" || score==""){
        layer.prompt({
            formType: 0,
            title: '请输入每题的分值',
            maxlength: 3,
        }, function(value, index){
            score = value;
            if(Number(score)>0) {
                window.location.href= localhost + "/toGetQuestionPage.action?type="+type+"&"+type+"Score="+score;
            }else{
                layer.alert("输入有误");
            }
        });
    } else window.location.href= localhost + "/toGetQuestionPage.action?type="+type+"&"+type+"Score="+score;
}

function paperDetail_reset(){
    layer.msg('你确定要清空试卷？清空后将返回首页', {
        time:0,
        btn: ['确定', '取消'],
        yes: function(index){
            $.ajax({
                url: localhost + "/teacher/distroyPaper.action",
                success:function(data){
                    layer.alert("清空完成", {icon:1}, function(index){
                        toIndex();
                    });
                }
            });
        },
        cancel: function(data){
            layer.alert("操作失败", {icon:2}, function(index){
                layer.close(index);
            });
        }
    });
}

function paperDetail_resetQuestion(type){
    layer.msg('你确定要清空已选的题目？', {
        time:0,
        btn: ['确定', '取消'],
        yes: function(index){
            $.ajax({
                type:'get',
                url: localhost + "/teacher/resetQuestion.action",
                dataType:'json',
                data:"type=" + type,
                success:function(data){
                    layer.alert("清空完成", {icon:1},function(index){
                        layer.close(index);
                        window.location.href = window.location.href;
                    });
                },
                error:function(data){
                    layer.alert("操作失败", {icon:2}, function(index){
                        layer.close(index);
                    });
                }
            });
        },
        cancel: function(index){
            layer.close(index);
        }
    });
}

function paperDetail_changeQuestionScore(type){
    layer.prompt({
        formType: 0,
        title: '修改分值',
        maxlength: 3,
    }, function(value, index){
        score = value;
        if(Number(score)>0) {
            $.ajax({
                type:'get',
                url: localhost + "/teacher/changeQuestionScore.action",
                dataType:'json',
                data:"type="+type + "&score="+score,
                success:function(data){
                    layer.alert("操作成功", {icon:1},function(index){
                        layer.close(index);
                        window.location.href = window.location.href;
                    });
                },
                error:function(data){
                    layer.alert("操作失败", {icon:2}, function(index){
                        layer.close(index);
                    });
                }
            });
        }else{
            layer.alert("输入有误");
        }
    });
}