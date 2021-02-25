<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta content="text/html" charset="utf-8">
    <title>小区物业管理系统</title>
    <style type="text/css">
      .rigth_top{
        float: right;
      }
      .center{
        position: absolute;
        top:50%;
        left:50%;
      }
      :focus {outline:none;} /*for IE*/
      ::-moz-focus-inner {border-color: transparent;} /*for mozilla*/

    </style>
  </head>
  <body style="background: url(images/系统背景图片.jpg); background-size: 100% 100%">
  <script type="text/javascript">
    function login(){
           document.form.action="/main/toLogin";
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

  <form name="form" action=""  method="post" >
    <div class="rigth_top">
        <button value="登录" onclick="login()" style="background-color: #02246C;border:none">登录</button>
        <button value="注册" onclick="userRegistered()" style="background-color: #02246C;border:none">业主注册</button>
        <button value="注册" onclick="visitorRegistered()" style="background-color: #02246C;border:none">游客注册</button>
    </div>
    <div class="center">
        <h1 style="color: cyan;font-style:italic">欢迎使用小区业务管理系统</h1>
      </div>
  </form>


  </body>
</html>



