var curWwwPath = window.document.location.href;
var pathName =  window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var localhostPath = curWwwPath.substring(0,pos);
var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var localhost = localhostPath + projectName;

function searchQuestion(curr){
    var questionType = $("#questionType").val();
    var rows = 7;
    var str = "";
    $("#searchResult").html(str);
    $.ajax({
        type: "POST",
        url : localhost + "/admin/selectQuestion.action?offset="+curr+"&rows="+rows,
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
                    +'<button class="btn btn-primary" onclick="deleteQusertion(\''+questionType+'\',\''+id+'\')">删除</button>'
                    +'<button class="btn btn-primary" data-toggle="modal" data-target="#myModal"'
                    +' onclick="getQuestionInfo(\''+questionType+'\',\''+id+'\')">查看</button>'
                    +'</td>'
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

function getQuestionInfo(questionType, id){
    var str = '';
    $.ajax({
        type: 'GET',
        url : localhost + '/admin/selectOneQuestion.action?questionType='+questionType+'&questionId='+id,
        success: function (page) {
            if(page.pageCount<=0){
                layer.alert("发生错误", {icon:2});
            }else{
                var question = page.pageData[0];
                $("#modal-title").html('<textarea rows="5" name="content" style="width: 100%;">'+ question.content +'</textarea>');
                if(questionType=="choice") {
                    str = '<input name="answer1" value="'+ question.answer1 + '"/>'+
                        '<br/><input name="answer2" value="'+ question.answer2 + '"/>'+
                        '<br/><input name="answer3" value="'+ question.answer3 + '"/>'+
                        '<br/><input name="answer4" value="'+ question.answer4 + '"/>'+
                        '<br/>答案：<input name="rightAnswer" value="'+ question.rightAnswer + '"/><br/>';
                } else if(questionType=="fillin"){
                    str = '答案：<input name="correctionFill" value="'+ question.correctionFill + '"/><br/>';
                }

                if(question.imagepath!=null){
                    str = str + '<image name="" src=""/>';
                }

                str = str + '<hr/>科目：<select name="courseId" class="courseId" id="courseId"></select>'+
                    '<br/>章节：<input name="section" value="'+ question.section + '"/>'+
                    '<br/>难度：<input name="diff" value="'+ question.diff + '"/>';

                //隐藏域数据
                str = str + '<input type="hidden" name="id" value="'+ question.id + '"/>' +
                        '<input type="hidden" name="questionType" value="'+questionType+'"/>';
                $("#modal-body").html(str);
                $("#myModal").modal();

                $.ajax({
                    type:'get',
                    url: localhost + "/course/getAllCourseId.action",
                    dataType:"json",
                    success:function(data){
                        $.each(data,function(i,course){
                            var id = course.courseId;
                            var name = course.courseName;

                            var $option = $("<option></option>");
                            $option.attr("value",id);
                            $option.text(name);
                            $("#courseId").append($option);
                        });
                        $("#courseId option[value='"+ question.courseId +"']").attr("selected", "selected");
                    }
                });

            }
        }
    });
}

function updateQuestion(){
    $.ajax({
        type:'POST',
        url :localhost + '/admin/updateOneQuestion.action',
        data: $("#newQuestionForm").serialize(),
        dataType: 'json',
        success:function(result){
            var stateCode = result.stateCode;
            var exception = result.exception;
            var msg = result.msg;
            if(exception==null){
                layer.alert(msg+"("+stateCode+")",{icon:1});
                $("#myModal").modal('hide');
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

function deleteQusertion(questionType, id){
    layer.msg('你确定要删除这道题目吗？', {
        time: 0,
        btn: ['删除', '取消'],
        yes: function (index) {
            $.ajax({
                type: 'GET',
                url: localhost + '/admin/deleteOneQuestion.action',
                data: {questionId: id, questionType: questionType},
                dataType: 'json',
                success: function (result) {
                    var stateCode = result.stateCode;
                    var exception = result.exception;
                    var msg = result.msg;
                    if (exception == null) {
                        layer.alert(msg + "(" + stateCode + ")", {icon: 1});
                    } else {
                        layer.alert(msg + "(" + stateCode + ")", {icon: 2});
                    }
                },
                error: function (data) {
                    layer.alert("未知错误，请过段时间再尝试", {icon: 2});
                }
            });
            layer.close(index);
        },
        cancel: function (index) {
            layer.close(index);
        }
    });
}