<%--
  User: 唐国翔
  Date: 2017/7/24 0024
  Time: 9:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
</head>
<body>
    <form action="/callBack" method="post">
        用户名：<input type="text" name="account"/><br>
        密码：<input type="password" name="password"/><br>
        <input type="hidden" name="openid" value="${requestScope.openid}"/><br>
        <input type="hidden" name="nickname" value="${requestScope.nickname}"/><br>
        <input type="submit" value="登录并绑定"/><br>
    </form>
</body>
</html>
