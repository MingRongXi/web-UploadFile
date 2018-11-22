<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<script>
function check(){
	var idNode = document.getElementById("id");
	var id = idNode.value;
	if(id.lenth == 0){
		alert("请输入账号");
		return false;
	}
	var pwNode = document.getElementById("id");
	var pw = pwNode.value;
	if(pw.lenth == 0){
		alert("请输入密码");
		return false;
	}
	return true;
}

function isNull()
</script>
</body>
</html>