<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    $(function(){
        getCollId();
        getCourseId();
    });

    function paper_delete(id){
        layer.msg('你确定要删除吗', {
            btn: ['删除', '取消'],
            time: 0,
            yes: function(index){
                $.ajax({
                    url: localhost + "/paper/delete.action?paperId="+id,
                    success:function(data){
                        layer.alert("删除完成", {icon:1}, function(){
                            paperPage(1);
                        });
                    }
                });
                layer.close(index);
            }
        });
    }
</script>
<!--检索-->
<script type="text/javascript">
    var rows = 5;
    function paperPage(curr){
        var str = "";
        $("#searchResult").html(str);
        $.ajax({
            type: "POST",
            url : "${pageContext.request.contextPath}/paper/getPaperPage.action?offset="+curr+"&rows="+rows,
            data: $("#paperform").serialize(),
            dataType: "json",
            success: function(page){
                var pages = page.pageCount;

                //表头
                str = '<table class="table table-bordered my-table" style="margin:100px 0 0;">'
                    +'<tr>'
                    +'<th width="200px" class="my-table-id">试卷编号</th>'
                    +'<th>科目</th>'
                    +'<th width="200px">使用班级</th>'
                    +'<th width="100px">创建者</th>'
                    +'<th width="120px">创建时间</th>'
                    +'<th width="150px">联系方式</th>'
                    +'<th width="100px">审核状态</th>'
                    +'<th width="200px">操作</th>'
                    +'</tr>'
                    +'</table>'
                    +'<table class="table table-striped table-bordered my-table-hover my-table" style="margin:0;">'
                    +'<tbody id="paperTable">';

                if(pages==0){
                    layer.alert('没有任何记录',{icon:2});
                    str += '</tbody></table>';
                    $("#searchResult").html(str);
                    return;
                }

                //表格正文
                $.each(page.pageData, function(i,paper){
                    str += '<tr>'
                    +'<td width="200px">'+paper.paperId+'</td>'
                    +'<td>'+paper.courseName+'</td>'
                    +'<td width="200px">'+paper.useClasses+'</td>'
                    +'<td width="100px">'+paper.userRealname+'</td>'
                    +'<td width="120px">'+paper.createtimeVo+'</td>'
                    +'<td width="150px">'+paper.phone+'</td>'
                    +'<td width="100px">'+paper.vettedVo+'</td>'
                    +'<td width="200px">'
                    +'<div class="btn btn-primary btn-lg" onclick="createPaper('+"'"+paper.paperId+"'"+')" createId="'+paper.paperId+'">查看</div>&nbsp;&nbsp;'
                    +'<div class="btn btn-primary btn-lg" onclick="paper_delete('+"'"+paper.paperId+"'"+')" deleteId="'+paper.paperId+'">删除</div>';
                    +'</td></tr>';
                });

                //表格结尾
                str += '</tbody></table>'
                        +'<div id="paperButtom" style="text-align: center;margin-top: 40px;"></div>';
                $("#searchResult").html(str);

                //调用分页
                laypage({
                    cont: 'paperButtom',
                    pages: pages,
                    curr: curr || 1,
                    skin: 'molv',
                    jump: function(obj, first){
                        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                            paperPage(obj.curr);
                        }
                    }
                });
            },
            error : function(data){
                layer.alert("未知错误，请过段时间再尝试",{icon:2});
            }
        });
    }
</script>

<div class="main">
    <h1>试卷列表</h1>
    <form class="form-horizontal" action="#" id="paperform">
        <div class="form-group">
            <label class="col-xs-2 control-label">审核状态</label>
            <div class="col-xs-10">
                <select name="vetted" id="vetted">
                    <option value="3">审核通过</option>
                    <option value="4">审核不通过</option>
                    <option value="2">等待审核</option>
                    <option value="1">未提交</option>
                    <option value="">全部</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 control-label">学院</label>
            <div class="col-xs-10">
                <select id="collId" name="collId">
                    <option value="">--------------------</option>
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
            <div class="col-xs-offset-2 col-xs-10">
                <div class="button">
                    <label>
                        <input type="button" class="btn btn-default" onclick="paperPage(1)" value="查看">
                    </label>
                </div>
            </div>
        </div>
    </form>
</div>

<div id="searchResult" style="width: 1450px;"></div>
