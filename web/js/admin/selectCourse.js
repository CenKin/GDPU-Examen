var curWwwPath = window.document.location.href;
var pathName =  window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var localhostPath = curWwwPath.substring(0,pos);
var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var localhost = localhostPath + projectName;

function submitForm(curr){
    var rows = 7;
    var str = "";
    $("#searchResult").html(str);
    $.ajax({
        type:"POST",
        url: localhost + "/admin/selectCourse.action?offset="+curr+"&rows="+rows,
        data: $("#courseForm").serialize(),
        dataType:"json",
        success:function(page){
            var pages = page.pageCount;

            if(pages==0){
                layer.alert('没有任何记录',{icon:2});
                return;
            }

            //表头
            str = '<table class="table table-bordered my-table">'
                +'<tr>'
                +'<th width="380px">课程号</th>'
                +'<th>科目名称</th>'
                +'<th width="150px">操作</th>'
                +'</tr>'
                +'</table>'
                +'<table class="table table-striped table-bordered table-hover my-table">'
                +'<tbody id="courseTable">';

            //表格内容
            $.each(page.pageData, function(i,course){
                var courseId = course.courseId;
                var courseName = course.courseName;

                str += '<tr>'
                    +'<td width="380px">'+courseId+'</td>'
                    +'<td>'+courseName+'</td>'
                    +'<td width="150px">'
                    +'<button onclick="deleteCourse(\''+courseId+'\')" class="btn btn-primary">删除</button>&nbsp;&nbsp;'
                    +'<button onclick="getCourseInfo(\''+ courseId +'\',\''+courseName+'\')" class="btn btn-primary">查看</button>'
                    +'</td>';
            });

            str += '</tbody></table>';
            $("#searchResult").html(str);

            //调用分页
            laypage({
                cont: 'courseButtom',
                pages: pages,
                curr: curr || 1,
                skin: 'molv',
                jump: function(obj, first){
                    if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                        submitForm(obj.curr);
                    }
                }
            })
        },
        error:function(data){
            layer.alert("未知错误，请过段时间再尝试",{icon:2});
        }
    });
    return false;
}

function getCourseInfo(courseId, courseName){

    var str = '科目名称：<input name="courseName" value="'+ courseName + '"/>' +
        '<input type="hidden" name="courseId" value="'+ courseId + '"/>';
    $("#modal-body").html(str);
    $("#myModal").modal();
}

function updateCourse(){
    $.ajax({
        type:'POST',
        url :localhost + '/admin/updateCourse.action',
        data: $("#newCourseForm").serialize(),
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

function deleteCourse(courseId){
    layer.msg('你确定要删除这个科目吗？这个操作使这个科目下所有的题目以及相关试卷等信息都会被删除！', {
        time: 0,
        btn: ['删除', '取消'],
        yes: function (index) {
            $.ajax({
                type: 'POST',
                url: localhost + '/admin/deleteCourse.action',
                data: {courseId: courseId},
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

