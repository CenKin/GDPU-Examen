var curWwwPath = window.document.location.href;
var pathName =  window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var localhostPath = curWwwPath.substring(0,pos);
var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var localhost = localhostPath + projectName;
    
//共用方法
function toIndex(){
	window.location.href = localhost + "/index.jsp";
}
function toPaperDetail() {
	window.location.href = localhost + "/toPaperDetail.action";
}
function toPaperInfo() {
	window.location.href = localhost + "/toPaperInfo.action";
}
//查看报表
function createPaper(id) {
	//创建虚拟表单post过去
	var $form = $("<form></form>");
	$form.attr("action", localhost + "/ReportServer?reportlet=examen.cpt");
	$form.attr("method", "post");
	
	var $input1 = $("<input/>");
	$input1.attr("type", "hidden");
	$input1.attr("name", "paperId");
	$input1.attr("value", id);
	
	var $input2 = $("<input/>");
	$input2.attr("type", "hidden");
	$input2.attr("name", "localhost");
	$input2.attr("value", localhost);
	
	$form.append($input1);
	$form.append($input2);
	$form.submit();	
}
