<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login/register</title>
</head>
<body>
<form action="register" method="Post">
    <h1>${data}</h1>
    用户名：<input type="text" name="username"/>
    密码<input type="password" name="password"/>
    <input type="submit" value="提交"/>

    <h2>账号是：${username}  密码是${password}</h2>
</form>
</body>
</html>