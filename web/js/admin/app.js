var curWwwPath = window.document.location.href;
var pathName =  window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var localhostPath = curWwwPath.substring(0,pos);
var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var localhost = localhostPath + projectName;

function init(){
    $.ajax({
        url: localhost + "/admin/indexBody.action",
        type: 'post',
        success: function (data) {
            $('#main').html(data);
        }
    });
}

function jump(url){
    $.ajax({
        type:'POST',
        url:localhost +"/"+ url,
        success: function(data){
            $('#main').html(data);
        }
    });
}

function getCourseId(){
    $.ajax({
        type:'get',
        url: localhost + "/course/getAllCourseId.action",
        dataType:"json",
        success:function(data){
            $.each(data,function(i,course){
                var id = course.courseId;
                var name = course.courseName;

                var $option = $("<option></option>");
                $option.attr("value",id);
                $option.text(name);
                $(".courseId").append($option);
            });
        }
    });
}

function getCollId(){
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
            $("#collId").on("change",function(){
                var collId = $("#collId").val();
                getMajorId(collId);
            });
        }
    });
}

function getMajorId(collId){
    $("#majorId").empty();
    $("#majorId").html("<option value=''>--------------------------------------------</option>");

    if(collId!=null && collId!=""){
        $.ajax({
            type:'get',
            url: localhost + "/course/getMajorByCollId.action",
            datatype:"json",
            data:"collId="+collId,
            contentType:"application/json",
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

function logout(id) {
    layer.msg('确认登出？', {
        time: 0,
        btn: ['登出', '取消'],
        yes: function(index){
            $.ajax({
                url: localhost + "/logout.action",
            });
            layer.close(index);
        }
    });
}

