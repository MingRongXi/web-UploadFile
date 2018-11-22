<%@page import="org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>请登录</title>
</head>
<body>
	<form action="LoginServlet" method = "post" onsubmit = "return check();">
	<table>
	<tr><td>账号：<input type = "text" name = "id" id = "id"></td></tr>
	<tr><td>密码：<input type = "password" name = "password" id = "password">	${session.getId()} </td></tr>
	<c:set var="id" value="a" scope="session"/>
	<tr><td><input type = "submit" value = "登录" name = "login" ></td></tr>
	</table>
	</form>	
</body>

<script>
function check(){
	var idNode = document.getElementById("id");
	var id = idNode.value;
	if(isNull(id)){
		alert("请输入账号");
		return false;
	}
	var pwNode = document.getElementById("password");
	var pw = pwNode.value;
	if(isNull(pw)){
		alert("请输入密码");
		return false;
	}
	return true;
}
function isNull(val){
	var str = val.replace(/(^\s*)|(\s*$)/g, ''); //把val首尾的空格去掉。

        if (str == '' || str == undefined || str == null) {//输入框中输入空格也为空
            return true;
        } else {
            return false;
		}
}
</script>
</html>