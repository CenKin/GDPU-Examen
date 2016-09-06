<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function(){
        getCollId();
        getMajorId();
    });
</script>
<script>
    function submitForm(){
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/admin/addCourse.action",
            data: $("#courseForm").serialize(),
            dataType:"json",
            success:function(result){
                var stateCode = result.stateCode;
                var exception = result.exception;
                var msg = result.msg;
                if(exception==null){
                    layer.alert(msg+"("+stateCode+")",{icon:1});
                    jump('/admin/toAddCourse.action');
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
    <h1>增加科目</h1>
    <form class="form-horizontal" id="courseForm" onsubmit="return submitForm();">
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
                <input name="courseName" type="text" class="form-control" placeholder="输入科目名">
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