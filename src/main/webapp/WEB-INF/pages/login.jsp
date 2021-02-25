<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/1
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="../../css/login.css"/>
    <script type="text/javascript">
        function toIndex(){
              document.form.action="/main/toIndex";
              document.form.submit();
           }
        function userRegistered(){
              document.form.action="/main/toUserRegistered";
              document.form.submit();
           }
       function visitorRegistered(){
              document.form.action="/main/toVisitorRegistered";
              document.form.submit();
           }
    </script>
</head>
<body style="background: url(../../images/登录注册背景图.jpg); background-size: 100% 100%">
    <div class="loginDiv">
        <form name="form" action="/main/mainLogin" method="post">
            <h1 style="text-align: center;color: rgba(100, 149, 237, .7)">欢&nbsp;迎&nbsp;登&nbsp;录</h1>
            <p>用&nbsp; 户&nbsp; 名:&nbsp;<input type="text" name="name" placeholder="请输入用户名" autocomplete="off" ></p>

            <p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input type="password" name="password" placeholder="请输入密码"></p>

            <p>选择角色： <select name="role" class="button">
                              <option value="管理员">管理员</option>
                              <option value="业主">业主</option>
                              <option value="游客">游客</option>
                           </select></p>

            <div style="text-align: center;margin-top: 30px;">
                <input type="submit" class="button"  value="登录" >
                <input type="button" class="button" value="返回" onclick="toIndex()">
                <input type="button" class="button" value="业主注册" onclick="userRegistered()">
                <input type="button" class="button" value="游客注册" onclick="visitorRegistered()">

            </div>
        </form>
    </div>

</body>
</html>




