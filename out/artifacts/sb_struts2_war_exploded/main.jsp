<%--
  Created by IntelliJ IDEA.
  User: DealiAxy
  Date: 2019/11/17
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理系统</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap-reboot.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="h1 text-center">用户管理系统</div>

    <div class="list-group">
        <a class="list-group-item list-group-item-action" href="Login.action">登录</a>
        <a class="list-group-item list-group-item-action" href="SignIn.action">注册</a>
        <a class="list-group-item list-group-item-action" href="User.action">修改密码</a>
        <a class="list-group-item list-group-item-action" href="Message.action">留言板</a>
    </div>

    <div class="text-center text-muted mb-lg-4">
        Copyright @ hellochouchou 2019
    </div>
</div>

</body>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</html>