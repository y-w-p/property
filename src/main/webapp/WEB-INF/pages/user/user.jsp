<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/1
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>小区物业管理系统</title>
  <link rel="stylesheet" href="../../../layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">小区物业管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item">
       <a href="javascript:;" onclick="welcome()">主页</a>
     </li>
      <li class="layui-nav-item">
        <a href="javascript:;">通知</a>
        <dl class="layui-nav-child">
          <dd><a href="">消息管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;"><i class="layui-icon">&#xe770;</i>
     <%=request.getSession().getAttribute("name")%>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="<c:url value="/user/toUserInfo" />" target="towhere">个人信息</a></dd>
          <dd><a href="<c:url value="/main/doChange" />">切换账号</a></dd>
          <dd><a href="<c:url value="/main/doLogout" />">退出</a></dd>
        </dl>
      </li>
    </ul>
  </div>

  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item ">
          <a href="javascript:;">
            <i class="layui-icon">&#xe62e;</i>
            <cite>停车管理</cite>
          </a>
          <dl class="layui-nav-child">
            <dd>
              <a href="javascript:;" onclick="parking()">
                <i class="layui-icon">&#xe602;</i>停车详情</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">
             <i class="layui-icon" >&#xe659;</i>
             <cite>账单管理</cite>
          </a>
          <dl class="layui-nav-child">
            <dd>
              <a href="javascript:;" onclick="park_cost()">
                <i class="layui-icon">&#xe602;</i>停车账单详情</a></dd>
              <dd>
                <a href="javascript:;" onclick="property_cost()">
                  <i class="layui-icon">&#xe602;</i>物业账单详情</a></dd>
          </dl>
        </li>
          <li class="layui-nav-item">
           <a href="javascript:;">
              <i class="layui-icon" >&#xe631;</i>
              <cite>维修管理</cite>
           </a>
           <dl class="layui-nav-child">
             <dd>
               <a href="javascript:;" onclick="repaired_list()">
                 <i class="layui-icon">&#xe602;</i>维修详情</a></dd>
               <dd>
                 <a href="javascript:;" onclick="repaired()">
                   <i class="layui-icon">&#xe602;</i>上报维修</a></dd>
           </dl>
         </li>
        <li class="layui-nav-item">
          <a href="javascript:;">
            <i class="layui-icon">&#xe63a;</i>
            <cite>小区贴吧</cite>
          </a>

          <dl class="layui-nav-child">
            <dd>
              <a href="javascript:;" onclick="platform()">
                <i class="layui-icon">&#xe602;</i>浏览帖子</a></dd>
            <dd>
              <a href="javascript:;" onclick="visitor_publish()">
                <i class="layui-icon">&#xe602;</i>发表帖子</a></dd>
            <dd>
              <a href="javascript:;" onclick="article_manage()">
                  <i class="layui-icon">&#xe602;</i>管理帖子</a></dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>

  <div class="layui-body layui-show">
    <!-- 内容主体区域 -->
        <iframe id="towhere" name="towhere" src="/main/toWelcome" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  </div>

  <div class="layui-footer layui-bg-gray">
    <!-- 底部固定区域 -->
    Copyrigth ©2021 ywp
  </div>
</div>
<script src="../../../layui/layui.js"></script>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>

<script>
//JavaScript代码区域
  layui.use(['element','jquery'],function(){
    var element = layui.element;
    var $ = layui.jquery;
  });
  function Info() {
    $("#towhere").attr("src", '/user/toUserInfo');
  }
  function welcome() {
    $("#towhere").attr("src", '/main/toWelcome');
  }

  function parking() {
    $("#towhere").attr("src", '/user/toUserParking');
  }
  function park_cost() {
      $("#towhere").attr("src", '/user/toUserParkCost');
    }

function property_cost() {
     $("#towhere").attr("src", '/user/toUserPropertyCost');
   }

function repaired_list() {
     $("#towhere").attr("src", '/user/toUserRepairedList');
   }

function repaired() {
     $("#towhere").attr("src", '/user/toUserRepaired');
   }


    //公共论坛，共用代码
  function platform() {
      $("#towhere").attr("src", '/visitor/visitor_article_look');
    }
function visitor_publish() {
      $("#towhere").attr("src", '/visitor/toVisitorPublish');
    }
function article_manage() {
      $("#towhere").attr("src", '/visitor/toVisitorArticleManage');
    }
</script>
</body>
</html>
