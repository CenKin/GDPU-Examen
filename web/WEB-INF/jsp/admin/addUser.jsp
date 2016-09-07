<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function(){
       getCollId();
    });

    function addUser(){
        $.ajax({
            type: 'POST',
            url : '${pageContext.request.contextPath}/admin/addUser.action',
            data: $('#userForm').serialize(),
            dataType : 'json',
            success:function(result){
                var stateCode = result.stateCode;
                var exception = result.exception;
                var msg = result.msg;
                if(exception==null){
                    layer.alert(msg+"("+stateCode+")",{icon:1}, function(index){
                        layer.close(index);
                        jump('/admin/toAddUser.action');
                    });
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
    <h1>增加用户</h1>
    <form class="form-horizontal" onsubmit="return addUser();" id="userForm">
        <div class="form-group">
            <label class="col-xs-2 control-label">身份</label>
            <div class="col-xs-10">
                <select name="userType">
                    <option value="2">教师</option>
                    <option value="3">主任</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">学院</label>
            <div class="col-xs-10">
                <select name="collId" id="collId">
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">真实姓名</label>
            <div class="col-xs-10">
                <input name="realname" type="text" class="form-control" placeholder="输入真实姓名">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">用户名</label>
            <div class="col-xs-10">
                <input name="username" type="text" class="form-control" placeholder="输入用户名">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">密码</label>
            <div class="col-xs-10">
                <input name="password" type="text" class="form-control" placeholder="输入密码">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">电话号码</label>
            <div class="col-xs-10">
                <input name="phone" type="text" class="form-control" placeholder="输入电话号码">
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