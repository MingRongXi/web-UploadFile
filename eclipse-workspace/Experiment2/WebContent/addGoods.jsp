<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加商品</title>
</head>
<body>
<form action = "${pageContext.request.contextPath }/UpdateServlet?method=add" enctype="multipart/form-data" method = "post">
<table>
<tr><td>文件:</td> <td> <input type = "file" name = "image"> </td> </tr>
<tr><td>商品id</td> <td> <input type = "text" name = "id"> </td> </tr>
<tr><td>名称</td> <td> <input type = "text" name = "name"> </td> </tr>
<tr><td>生产厂商</td> <td><input type = "text" name = "product"> </td> </tr>
<tr><td>类型</td> <td> <input type = "text" name = "type"> </td> </tr>
<tr><td>型号</td> <td> <input type = "text" name = "typeNumber"> </td> </tr>
<tr><td>描述</td> <td> <input type = "text" name = "id"> </td> </tr>
<tr><td><input type="submit" value = "上传">
</table>
</form>
</body>
</html>