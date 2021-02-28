<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/22
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.lang.String" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
    <title>管理员发布物业费用</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css"  media="all">
</head>
<body class="layui-bg-gray">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>发布物业管理费用</legend>
</fieldset>
    <form class="layui-form layui-form-pane" action="/admin/admin_property_publish" enctype="multipart/form-data" method="post">

    <div class="layui-form">
    <div class="layui-form-item">

        <div class="layui-inline">
         <label class="layui-form-label">输入年份：</label>
         <div class="layui-input-inline">
           <input type="text" name="year" id="year" class="layui-input" placeholder="例如：2020" autocomplete="off">
         </div>
        </div>


        <div class="layui-inline">
        <label class="layui-form-label">选择月份：</label>
            <div class="layui-input-inline">
              <select name="month" id="month" lay-verify="required" lay-search="">
                <option value="">直接选择或搜索选择</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
              </select>
            </div>
        </div>


        <div class="layui-inline">
        <label class="layui-form-label">输入单价：</label>
        <div class="layui-input-inline">
          <input type="text" name="price" id="price" class="layui-input" placeholder="每平方米价格(元)，例如：2" autocomplete="off">
        </div>
        </div>


    </div>


    <div class="layui-form-item" >
          <button type="submit" class="layui-btn" lay-submit="" lay-filter="save">确认发布</button>
          <button type="reset" class="layui-btn " lay-submit="" lay-filter="return">取消发布</button>
        </div>


    </div>
    </form>

<%
    String msg = (String) request.getSession().getAttribute("property_publish_msg");
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

});


</script>


</body>
</html>
