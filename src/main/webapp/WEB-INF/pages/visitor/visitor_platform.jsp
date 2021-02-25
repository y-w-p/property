<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/19
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ywp.entity.Article" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.logging.SimpleFormatter" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../../../layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>欢迎来到小区贴吧</legend>
</fieldset>
<div class="layui-collapse" lay-filter="test">

    <%
       //java代码能输出
       List<Article> articles = (List<Article>) request.getSession().getAttribute("visitor_articles");
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       for (Article article :articles) {
           String publish_time = formatter.format(article.getPublish_time()); //将日期时间格式化
    %>
  <div class="layui-colla-item ">
      <h2 class="layui-colla-title layui-bg-green">
          主题：<%=article.getTopic()%>
      </h2>

    <div class="layui-colla-content layui-bg-gray">
        <p>内容：<%=article.getContent()%></p><br>
        <p>发布者：<%=article.getPeople_name()%></p><br>
       <p> 发布时间：<%=publish_time%></p>
    </div>
  </div>
    <%}%>

</div>

<script src="../../../layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use(['element', 'layer'], function(){
  var element = layui.element;
  var layer = layui.layer;

});
</script>

</body>
</html>