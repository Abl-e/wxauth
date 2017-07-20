<%@ page import="com.alibaba.fastjson.JSONObject" %><%--
  User: 唐国翔
  Date: 2017/7/20 0020
  Time: 14:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    JSONObject userInfo = (JSONObject) request.getAttribute("userInfo");
%>
<center>
    <table>
        <tr>
            <th>用户名：</th>
            <td>${requestScope.userInfo.nickname}</td>
        </tr>
        <tr>
            <th>头像：</th>
            <td><img src="${requestScope.userInfo.headimgurl}" style="width: 100px;height: 100px"></td>
        </tr>
        <tr>
            <th>性别：</th>
            <td>${requestScope.userInfo.sex}</td>
        </tr>
    </table>
</center>
</body>
</html>
