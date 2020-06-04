<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/book/fileupload" method="post" enctype="multipart/form-data">
    选择文件:<<input type="file" name="upload"/><br>
    <input type="submit" value="上传">
</form>

</body>
</html>
