<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/UpdateServlet" enctype="multipart/form-data" method="post">
  上传用户：<input type="text" name="username"><br/>
  上传用户：<input type="text" name="username1"><br/>
  上传文件1：<input type="file" name="file1"><br/>
  上传文件2：<input type="file" name="file2"><br/>
  <input type="submit" value="提交">
 </form>
</body>
</html>