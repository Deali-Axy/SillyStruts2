<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: DealiAxy
  Date: 2019/11/21
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap-reboot.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }

        .card {
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="h1 text-center">留言板</div>

    <s:if test="alert.visible">
        <div class="alert alert-<s:property value="alert.level"/>" role="alert">
            <s:property value="alert.content"/>
        </div>
    </s:if>

    <div class="card">
        <div class="card-header">快速留言</div>
        <div class="card-body">
            <form action="" method="post">
                <div class="form-group">
                    <label for="messageTextarea">请输入留言内容</label>
                    <textarea class="form-control" id="messageTextarea" rows="3"></textarea>
                </div>
                <div class="float-right">
                    <input type="submit" class="btn btn-outline-primary" value="提交">
                </div>
            </form>
        </div>
    </div>

    <s:iterator value="messages">
        <div class="card">
            <div class="card-header">
                <s:property value="user.name"/>
                <span class="float-right">
                    <s:property value="time.toString()"/>
                </span>
            </div>
            <div class="card-body">
                <s:property value="content"/>
            </div>
        </div>
    </s:iterator>

</div>
</body>
</html>
