<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function(){
        getCollId();
    });

    function selectUser(curr){
        var str = "";
        var rows = 7;
        $("#searchResult").html(str);
        $.ajax({
            type: 'POST',
            url : localhost + '/admin/selectUser.action?offset='+curr+'&rows='+rows,
            data: $('#userForm').serialize(),
            dataType : 'json',
            success:function(page){
                var pages = page.pageCount;

                if(pages==0){
                    layer.alert('没有任何记录',{icon:2});
                    return;
                }

                //表头
                str = '<table class="table table-bordered my-table">'
                        +'<tr>'
                        +'<th width="180px">用户ID</th>'
                        +'<th width="130px">真实姓名</th>'
                        +'<th width="130px">所属学院</th>'
                        +'<th>用户名</th>'
                        +'<th width="100px">用户类型</th>'
                        +'<th width="150px">联系方式</th>'
                        +'<th width="130px">最后登录时间</th>'
                        +'<th width="150px">操作</th>'
                        +'</tr>'
                        +'</table>'
                        +'<table class="table table-striped table-bordered my-table-hover my-table">'
                        +'<tbody id="courseTable">';

                //表格内容
                $.each(page.pageData, function(i,user){
                    str += '<tr>'
                            +'<td width="180px" class="my-table-id">'+user.userId+'</td>'
                            +'<td width="130px">'+user.realname+'</td>'
                            +'<td width="130px">'+user.collIdName+'</td>'
                            +'<td>'+user.username+'</td>'
                            +'<td width="100px">'+user.userTypeVo+'</td>'
                            +'<td width="150px">'+user.phone+'</td>'
                            +'<td width="130px">'+user.loginTimeVo+'</td>'
                            +'<td width="150px">'
                            +'<button onclick="deleteUser(\''+user.userId+'\')" class="btn btn-primary">删除</button>'
                            +'<button onclick="updateUser('+user+')" class="btn btn-primary">修改</button>'
                            +'</td>'
                });

                str += '</tbody></table>';
                $("#searchResult").html(str);

                //调用分页
                laypage({
                    cont: 'userButtom',
                    pages: pages,
                    curr: curr || 1,
                    skin: 'molv',
                    jump: function(obj, first){
                        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                            selectUser(obj.curr);
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
    <h1>检索用户</h1>
    <form class="form-horizontal" onsubmit="return selectUser(1);" id="userForm">
        <div class="form-group">
            <label class="col-xs-2 control-label">id</label>
            <div class="col-xs-10">
                <input name="userId" type="text" class="form-control" placeholder="输入id">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">学院</label>
            <div class="col-xs-10">
                <select name="collId" id="collId">
                    <option value="">----------</option>
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
            <label class="col-xs-2 control-label">电话号码</label>
            <div class="col-xs-10">
                <input name="phone" type="text" class="form-control" placeholder="输入电话号码">
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
<div id="userButtom" class="table-buttom"></div>