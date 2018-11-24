<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品信息</title>
</head>
<body>
	<table cellspacing="0" border="1">
		<tr>
			<td style="width: 50px">图片</td>
			<td style="width: 80px">商品id</td>
			<td style="width: 80px">商品名称</td>
			<td style="width: 150px">生产厂商</td>
			<td style="width: 150px">类型</td>
			<td style="width: 150px">型号</td>
			<td style="width: 200px">产地</td>
			<td style="width: 200px">商品描述</td>
			<td style="width: 200px">上传</td>
		</tr>
		<c:forEach var="goods" items="${goodsList}">
			<form
				action="${pageContext.request.contextPath }/UploadServlet?method=update&&id=${goods.id}"
				enctype="multipart/form-data" method="post"
				onsubmit="return isNll(${goods.id})">
				<tr>
					<td><img src="Image/${goods.path}" width="50" height="50"></td>
					<td>${goods.id}</td>
					<td>${goods.name}</td>
					<td>${goods.product}</td>
					<td>${goods.type}</td>
					<td>${goods.typeNumber}</td>
					<td>${goods.address}</td>
					<td>${goods.description}</td>
					<td><input type="file" name="picture" id="a"><input
						type="submit" value="上传"></td>
				</tr>
			</form>
		</c:forEach>
	</table>
	<input type="button" value="添加" onclick="location.href='addGoods.jsp'">
	<br> 登录人数：${userCounts}
</body>
<script>
function isNull(id){
	var image = document.getElementById(id);
	if(image == null){
        alert("请选择图片");
        return false;
    }
    return true;
}
</script>
</html>
