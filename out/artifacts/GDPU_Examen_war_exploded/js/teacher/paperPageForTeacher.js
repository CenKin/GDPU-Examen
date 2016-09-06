function paperPage(curr) {
    var rows = 7;
    var str = "";
    $.ajax({
        type: 'get',
        url: localhost + "/paper/getPaperPage.action?offset=" + curr + "&rows=" + rows,
        dataType: "json",
        success: function (page) {
            var pages = page.pageCount;

            if (pages == 0) {
                layer.alert('没有任何记录', {icon: 2});
                return;
            }

            //表格内容
            $.each(page.pageData, function (i, paper) {
                str += '<tr>'
                    +'<td width="200px" class="my-table-id">'+paper.paperId+'</td>'
                    +'<td>'+paper.courseName+'</td>'
                    +'<td width="140px">'+paper.createtimeVo+'</td>'
                    +'<td width="100px">'+paper.vettedVo+'</td>'
                    +'<td width="380px">'
                    +'<div class="btn btn-primary btn-lg" onclick="createPaper('+"'"+paper.paperId+"'"+')" createId="'+paper.paperId+'">查看</div>'
                    +'<div class="btn btn-primary btn-lg" onclick="paper_change('+"'"+paper.paperId+"'"+')" changeId="'+paper.paperId+'">更改</div>'
                    +'<div class="btn btn-primary btn-lg" onclick="paper_vetted('+"'"+paper.paperId+"'"+')" checkId="'+paper.paperId+'">提交</div>'
                    +'<div class="btn btn-primary btn-lg" onclick="paper_delete('+"'"+paper.paperId+"'"+')" deleteId="'+paper.paperId+'">删除</div>';
                +'</td></tr>';
            });

            $("#paperTable").html(str);

            //调用分页
            laypage({
                cont: 'paperButtom',
                pages: pages,
                curr: curr || 1,
                skin: 'molv',
                jump: function (obj, first) {
                    if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                        paperPage(obj.curr);
                    }
                }
            })
        },
        error: function (data) {
            layer.alert("未知错误，请过段时间再尝试", {icon: 2});
        }
    });
}

function paper_change(id){
    layer.msg('更改需要重新提交，是否更改？', {
        btn: ['更改', '取消'],
        time: 0,
        yes: function(index){
            window.location.href = localhost + "/paper/getPaperInfoFromDb.action?paperId="+id,
                layer.close(index);
        }
    });
}

function paper_vetted(id){
    layer.msg('确认提交？', {
        btn: ['提交', '取消'],
        time: 0,
        yes: function(index){
            $.ajax({
                url: localhost + "/paper/check.action?paperId="+id,
                success:function(data){
                    window.location.href=window.location.href;
                }
            });
            layer.close(index);
        }
    });
}

function paper_delete(id){
    layer.msg('你确定要删除吗', {
        btn: ['删除', '取消'],
        time: 0,
        yes: function(index){
            $.ajax({
                url: localhost + "/paper/delete.action?paperId="+id,
                success:function(data){
                    window.location.href=window.location.href;
                }
            });
            layer.close(index);
        }
    });
}