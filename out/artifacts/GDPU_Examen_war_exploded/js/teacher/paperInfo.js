function paperInfo_collChange(collId){
    $("#majorId").empty();
    $("#majorId").html("<option value=''>--------------------------------------------</option>");
    $("#courseId").empty();
    $("#courseId").html("<option value=''>--------------------------------------------</option>");

    if(collId!=null && collId!=""){
        $.ajax({
            type:'get',
            url: localhost + "/course/getMajorByCollId.action",
            datatype:"json",
            data:"collId="+collId,
            contentType:"application/json",
            async:false,
            success:function(data){
                $.each(data,function(i,major){
                    var id = major.majorId;
                    var name = major.majorName;

                    var $option = $("<option></option>");
                    $option.attr("value",id);
                    $option.text(name);
                    $("#majorId").append($option);
                });
            }
        });
    }
}

function paperInfo_majorChange(majorId){
    //修改科目下拉框
    $("#courseId").empty();
    $("#courseId").html("<option value=''>--------------------------------------------</option>");

    if(majorId!=null && majorId!=""){
        $.ajax({
            type:'get',
            url: localhost + "/course/getCourseByMajorId.action",
            datatype:"json",
            data:"majorId="+majorId,
            contentType:"application/json",
            async:false,
            success:function(data){
                $.each(data, function(i,course){
                    var id = course.courseId;
                    var name = course.courseName;

                    var $option = $("<option></option>");
                    $option.attr("value",id);
                    $option.text(name);
                    $("#courseId").append($option);
                });
            }
        });
    }
}

function paperInfo_initPage(){
    //获取学院列表
    $.ajax({
        type:'post' ,
        url:localhost + "/course/getCollegeId.action",
        dataType:"json",
        success:function(data){
            $.each(data,function(i,coll){
                var id = coll.collId;
                var name = coll.collName;

                var $option = $("<option></option>");
                $option.attr("value",id);
                $option.text(name);
                $("#collId").append($option);
            });
        }
    });
    //自动填写表单信息,先获取session中的paper
    $.ajax({
        type:'get',
        url:localhost + "/paper/getPaperJson.action",
        dataType:"json",
        success:function(data){
            //获取专业列表
            paperInfo_collChange(data.collId);
            //获取科目列表
            paperInfo_majorChange(data.majorId);
            //填写班级信息
            $("#useClasses").attr("value", data.useClasses);
            $("#collId option[value='"+ data.collId +"']").attr("selected", "selected");
            $("#majorId option[value='"+ data.majorId +"']").attr("selected", "selected");
            $("#courseId option[value='"+ data.courseId +"']").attr("selected", "selected");
            $("#paperType option[value='"+ data.paperType +"']").attr("selected", "selected");
            $("#fromYear option[value='"+ data.fromYear +"']").attr("selected", "selected");
            $("#toYear option[value='"+ data.toYear +"']").attr("selected", "selected");
            $("#useTerm option[value='"+ data.useTerm +"']").attr("selected", "selected");
        }
    });
}