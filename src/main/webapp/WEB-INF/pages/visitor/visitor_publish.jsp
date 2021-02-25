<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/22
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.lang.String" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
    <title>游客个人信息</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css"  media="all">
</head>
<body class="layui-bg-gray">
    <form class="layui-form layui-form-pane" action="/visitor/visitor_article_publish" style="text-align: center">

    <div class="layui-form-item">
        <label class="layui-form-label">标题：</label>
        <div class="layui-input-block">
          <input type="text" name="topic" autocomplete="off" placeholder="请输入标题(不能为空)" class="layui-input" value="">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">内容：</label>
        <div class="layui-input-block">
        <textarea name="content" placeholder="请输入内容" autocomplete="off" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item" >
          <button type="submit" class="layui-btn" lay-submit="" lay-filter="save">发布帖子</button>
          <button type="reset" class="layui-btn " lay-submit="" lay-filter="return">取消发布</button>
        </div>
    </form>

<%
    String msg = (String) request.getSession().getAttribute("publish_msg");
    if(msg != null){

%>
<script type="text/javascript">
    alert("<%=msg%>");
</script>

<% }%>
<script src="../../../layui/layui.js" charset="utf-8"></script>

<script>
layui.use(['form' ,'layer'], function(){
  var form = layui.form
  ,layer = layui.layer


  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [
      /^[\S]{6,12}$/
      ,'密码必须6到12位，且不能出现空格'
    ]
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });



  //监听提交
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });





});
</script>


</body>
</html>
