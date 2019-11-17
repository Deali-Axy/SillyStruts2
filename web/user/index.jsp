<%--
  Created by IntelliJ IDEA.
  User: DealiAxy
  Date: 2019/11/17
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<h1>修改密码</h1>
<form action="User.action" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>旧密码：</td>
            <td><input type="text" name="origin-pwd"></td>
        </tr>
        <tr>
            <td>新密码：</td>
            <td><input type="password" name="new-pwd"></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input type="password" name="confirm-pwd"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="确定"></td>
        </tr>
    </table>
</form>
</body>
</html>
