var rows = 5;

function vetted_pass(id) {
    layer.msg('要让这份试卷通过吗？', {
        time: 0,
        btn: ['通过', '取消'],
        yes: function(index){
            $.ajax({
                url: localhost + "/paper/pass.action?paperId="+id,
                success:function(data){
                    window.location.href=window.location.href;
                }
            });
            layer.close(index);
        }
    });
}

function vetted_fail(id) {
    layer.msg('要让这份试卷不通过吗？', {
        time: 0,
        btn: ['不通过', '取消'],
        yes: function(index){
            $.ajax({
                url: localhost + "/paper/fail.action?paperId="+id,
                success:function(data){
                    window.location.href=window.location.href;
                }
            });
            layer.close(index);
        }
    });
}

function getPaperByVetted(curr, vetted){
    var str = "";
    $.ajax({
        type:'get',
        url: localhost + "/paper/getPaperPage.action?vetted="+vetted+"&offset=" + curr + "&rows=" + rows,
        dataType:"json",
        success:function(page){
            var pages = page.pageCount;

            if (pages == 0) {
                layer.alert('没有任何记录', {icon: 2});
                return;
            }

            //表格内容
            $.each(page.pageData, function (i, paper) {
                str += '<tr>'
                    +'<td width="200px" class="paper-id">'+paper.paperId+'</td>'
                    +'<td>'+paper.courseName+'</td>'
                    +'<td width="140px">'+paper.createtimeVo+'</td>'
                    +'<td width="100px">'+paper.vettedVo+'</td>'
                    +'<td width="100px">'+paper.userRealname+'</td>'
                    +'<td width="180px">'+paper.phone+'</td>'
                    +'<td width="350px">'
                    +'<div class="btn btn-primary btn-lg" onclick="createPaper('+"'"+paper.paperId+"'"+')">查看</div>'
                    +'<div class="btn btn-primary btn-lg" onclick="vetted_pass('+"'"+paper.paperId+"'"+')" passId="'+paper.paperId+'"}">通过</div>'
                    +'<div class="btn btn-primary btn-lg" onclick="vetted_fail('+"'"+paper.paperId+"'"+')" failId="'+paper.paperId+'">不通过</div>';
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
                        getPaperByVetted(obj.curr, vetted);
                    }
                }
            })
        },
        error: function (data) {
            layer.alert("未知错误，请过段时间再尝试", {icon: 2});
        }
    });
}
