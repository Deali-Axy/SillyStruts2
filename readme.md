# SliiyStruts2

又名 sb-struts2，因为struts真的太难用了（也许在很多年前是很好用的，但是现在看来，被其他框架秒成渣）

## 前言

想不到我还是得和这struts框架打交道啊，从一开始学web的时候就十分抵制这类古老，使用反人类的框架，不过为了帮女朋友做个学校的作业，还是得搞一下，然而，半小时就写好的业务代码，因为我不熟悉这个框架和Java的这套体系，调试了半天才成功run起来……（心好累）

## 开始

首先使用idea创建struts2项目，但是坑来了，idea创建的少了一个包，请自行去maven仓库下载，具体是少了这个包

```
javassist-3.26.0-GA.jar
```

接着就可以开始写代码了……

关于Struts2框架的学习参考：

- [Struts2入门这一篇就够了](https://juejin.im/post/5aa3349ff265da23884caa6a#heading-17)

这篇写得很好，很详细，里面还有例子，跟着做就可以自己实现一个简单的struts框架了……

## 配置

先写一个最简单的IndexAction，这里主要是做数据库的初始化工作，代码如下：

```java
package cn.deali.action;

import cn.deali.utils.Database;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexAction extends ActionSupport {
    private final static Logger logger = LoggerFactory.getLogger(IndexAction.class);

    @Override
    public String execute() throws Exception {
        System.out.println("Index Action");
        logger.info("Index Action");

        // 初始化数据库
        if (Database.init()) {
            Database.eraseData = false;
            return SUCCESS;
        } else
            return ERROR;
    }
}
```

写完了Action之后还要给一个配套的jsp页面，这叫做MVC设计模式，前后端分离（伪）……

```jsp
<%--
  Created by IntelliJ IDEA.
  User: DealiAxy
  Date: 2019/11/17
  Time: 10:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理系统</title>
</head>
<body>
<h1>用户管理系统</h1>

<a href="Login.action">登录</a>
<a href="SignIn.action">注册</a>
<a href="User.action">修改密码</a>

</body>
</html>
```

其他的代码由于篇幅关系我就没放上来，本文最后有GitHub地址，有需要可以参考

之后就可以配置struts2了，就像这样……

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE struts PUBLIC
        "-//Apache Software Foundation//DTD Struts Configuration 2.5//EN"
        "http://struts.apache.org/dtds/struts-2.5.dtd">

<struts>
    <package name="cn.deali.action" namespace="/" extends="struts-default">
        <default-action-ref name="Index"/>
        <action name="Index" class="cn.deali.action.IndexAction" method="execute">
            <result name="success">main.jsp</result>
            <result name="error">error.jsp</result>
        </action>
    </package>
</struts>
```

其中的`<default-action-ref name="Index"/>`是我后来学到的，默认action，一开始我以为和其他框架一样，定义一个“/”路由就可以了，结果自己坑了自己，调试了半天都不行。

参考资料：

- [struts2设置默认首页]( https://blog.csdn.net/zhangpan19910604/article/details/46672519 )

## 日志记录

一开始用的是log4j，感觉有点坑啊，然后想起来之前用过slf4j，虽然两个不是同个概念的，不过`slf4j+slf4j-simple`，是真的好用，方便，（ps：需要性能更好的可以用logback，hhh），log4j配置真的麻烦，而且嵌入到tomcat服务器，反正我这只是做个作业，不用搞太麻烦。

而且slf4j的输出模板也很好用，至少不会像log4j那么麻烦要拼接字符串了。

关于日志记录的操作参考：

- [Log4j.properties配置详解加示例 - 简约人生的博客 - CSDN博客 ](https://blog.csdn.net/jianyuerensheng/article/details/50420747) 

- [log4j 配置](https://github.com/digoal/blog/blob/master/201701/20170126_01.md)

- [SLF4J使用和与Log4J对比](https://www.jianshu.com/p/32e2a7254c03)

- [使用SLF4J和Logback](https://www.liaoxuefeng.com/wiki/1252599548343744/1264739155914176)

- [Java日志框架：slf4j作用及其实现原理](https://www.cnblogs.com/xrq730/p/8619156.html)

关于我自己的Log4J的配置我这也放上来吧，虽然最后没用上。

```yaml
# 配置根Logger：设定日志记录的最低级别，
log4j.rootLogger=DEBUG, stdout, logfile,ERROR
log4j.category.org.springframework=ERROR
log4j.category.org.apache=INFO
log4j.logger.org.hibernate=ERROR
# 输出到控制台
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
# 指定日志信息的最低输出级别，默认为DEBUG
log4j.appender.stdout.Threshold=ERROR
# 表示所有消息都会被立即输出，设为false则不输出，默认值是true
log4j.appender.stdout.ImmediateFlush=true
# 可以灵活自定义布局模式
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
# 默认值是System.out。
log4j.appender.stdout.Target=System.out
# 设定以怎样的格式显示消息
log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n
```

## 数据库

数据库又是喜闻乐见的SQLite了，反正每次我都是用这个，hhh……

关于Java使用SQLite，可以参考：

- [在Java中使用Sqlite数据库](https://www.cnblogs.com/haoqipeng/p/sqlite-use-in-java.html)

附上我的`SQLiteHelper`实用类代码：

```java
package cn.deali.utils.sqlite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * sqlite帮助类，直接创建该类示例，并调用相应的接口即可对sqlite数据库进行操作
 * <p>
 * 本类基于 sqlite jdbc v56
 *
 * @author haoqipeng
 */
public class SQLiteHelper {
    private final static Logger logger = LoggerFactory.getLogger(SQLiteHelper.class);

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String dbFilePath;

    /**
     * 构造函数
     *
     * @param dbFilePath sqlite db 文件路径
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SQLiteHelper(String dbFilePath) throws ClassNotFoundException, SQLException {
        this.dbFilePath = dbFilePath;
        connection = getConnection(dbFilePath);
    }

    /**
     * 获取数据库连接
     *
     * @param dbFilePath db文件路径
     * @return 数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection(String dbFilePath) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        return conn;
    }

    /**
     * 执行sql查询
     *
     * @param sql sql select 语句
     * @param rse 结果集处理类对象
     * @return 查询结果
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public <T> T executeQuery(String sql, ResultSetExtractor<T> rse) throws SQLException, ClassNotFoundException {
        try {
            resultSet = getStatement().executeQuery(sql);
            T rs = rse.extractData(resultSet);
            return rs;
        } finally {
            destroyed();
        }
    }

    /**
     * 执行select查询，返回结果列表
     *
     * @param sql sql select 语句
     * @param rm  结果集的行数据处理类对象
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public <T> List<T> executeQuery(String sql, RowMapper<T> rm) throws SQLException, ClassNotFoundException {
        List<T> rsList = new ArrayList<T>();
        try {
            resultSet = getStatement().executeQuery(sql);
            while (resultSet.next()) {
                rsList.add(rm.mapRow(resultSet, resultSet.getRow()));
            }
        } finally {
            destroyed();
        }
        return rsList;
    }

    /**
     * 执行数据库更新sql语句
     *
     * @param sql
     * @return 更新行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        try {
            int c = getStatement().executeUpdate(sql);
            return c;
        } finally {
            destroyed();
        }

    }

    /**
     * 执行多个sql更新语句
     *
     * @param sqls
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(String... sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }

    /**
     * 执行数据库更新 sql List
     *
     * @param sqls sql列表
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(List<String> sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if (null == connection) connection = getConnection(dbFilePath);
        return connection;
    }

    private Statement getStatement() throws SQLException, ClassNotFoundException {
        if (null == statement) statement = getConnection().createStatement();
        return statement;
    }

    /**
     * 数据库资源关闭和释放
     */
    public void destroyed() {
        try {
            if (null != connection) {
                connection.close();
                connection = null;
            }

            if (null != statement) {
                statement.close();
                statement = null;
            }

            if (null != resultSet) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            logger.error("Sqlite数据库关闭时异常", e);
        }
    }
}

public interface ResultSetExtractor<T> {
    public abstract T extractData(ResultSet rs);
}

public interface RowMapper<T> {
    public abstract T mapRow(ResultSet rs, int index) throws SQLException;
}
```

还有我的数据库工厂类，哈哈：

```java
package cn.deali.utils;

import cn.deali.utils.sqlite.SQLiteHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
    private final static Logger logger = LoggerFactory.getLogger(Database.class);
    private static SQLiteHelper db;
    public static boolean eraseData = false;

    public static SQLiteHelper getInstance() {
        if (db != null)
            return db;

        try {
            db = new SQLiteHelper("test.db");
            return db;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    public static boolean init() {
        logger.info("初始化数据库！");
        db = getInstance();
        try {
            if (eraseData) {
                db.executeUpdate("drop table if exists user;");
                db.executeUpdate("create table user(username varchar(20), password varchar(20));");
                logger.info("创建数据表");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }
}
```

## 坑

首先是tomcat服务器在Windows上有控制台输出乱码的问题，很烦，解决的话可以参考：

- [解决Tomcat控制台输出信息乱码](https://blog.csdn.net/qq_35436635/article/details/86575943)
- [Tomcat乱码问题 catalina.bat设置为UTF-8 控制台出现乱码](https://blog.csdn.net/zhaoxny/article/details/79926333)

## 完整代码

最后附上GitHub地址，有需要自取，没啥技术含量，就是做个小记录。

地址： https://github.com/Deali-Axy/SillyStruts2 