function addId(id, type){
    var $btn = $(".btn[qId='"+id+"']");
    $.ajax({
        type:"get",
        url: localhost + "/teacher/joinIn.action",
        data:type + "Id=" + id,
        success:function(html){
            $btn.text("删除");
            $btn.attr("onclick", "delId('"+id+"','"+type+"')");
            $btn.css("background","#F75000");
        }
    });
}

function delId(id, type){
    var $btn = $(".btn[qId='"+id+"']");
    $.ajax({
        type:"get",
        url: localhost + "/teacher/deleteId.action",
        data:type + "Id=" + id,
        success:function(html){
            $btn.text("加入");
            $btn.attr("onclick", "addId('"+id+"','"+type+"')");
            $btn.css("background","#1A6BE6");
        }
    });
}

function getSelectIds(type){
//设置已选的题目的按钮样式
    $.ajax({
        type:'get',
        url: localhost + "/teacher/getSelectQuestionIds.action?type=" + type,
        dataType:"json",
        contentType:"application/json",
        success:function(data){
            $.each(data, function(i,id){
                var $btn = $(".btn[qId='"+id+"']");
                $btn.text("删除");
                $btn.attr("onclick", "delId('"+id+"','"+type+"')");
                $btn.css("background","#F75000");
            });
        }
    });
}
