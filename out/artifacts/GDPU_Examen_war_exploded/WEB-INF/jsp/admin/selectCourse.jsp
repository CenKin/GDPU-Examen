<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--初始化页面-->
<script>
    $(function(){
        getCollId();
        getMajorId();
    });
</script>
<!--检索科目-->
<script>
    var rows = 7;
    function submitForm(curr){
        var str = "";
        $("#searchResult").html(str);
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/admin/selectCourse.action?offset="+curr+"&rows="+rows,
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
                            +'<button onclick="deleteCourse(\''+courseId+'\')" class="btn btn-primary">删除</button>'
                            +'<button onclick="updateCourse('+course+')" class="btn btn-primary">修改</button>'
                            + '</td>'
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
</script>

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