<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/25
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ywp.data.ResultData" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../../../layui/css/layui.css" media="all">
</head>
<body>






<table class="layui-hide" id="test" lay-filter="test" ></table>




<script src="../../../layui/layui.js"  charset="utf-8"></script>
<script>
layui.use(['jquery', 'form', 'table', 'layer', 'tree'], function(){
    var	$ = layui.jquery,
             form = layui.form,
             table = layui.table,
             layer = layui.layer,
             tree = layui.tree;


  //第一个实例
  table.render({
    elem: '#test'
    ,url:'/admin/admin_property_cost_list' //数据接口
    ,method:'post'
    ,page: true
    ,id: 'test'
    ,cols: [
        [ //表头
      {field: 'property_id', title: '物业账单号', sort: true}
      ,{field: 'admin_name', title: '物业管理者'}
      ,{field: 'user_name', title: '业主名称',sort:true}
      ,{field: 'year', title: '年份',sort:true}
      ,{field: 'month', title: '月份',sort:true}
      ,{field: 'money', title: '物业费(元)',sort: true}
      ,{field: 'publish_time', title: '发布时间',width:180,sort: true}
      ,{field: 'status', title: '缴费状态',sort: true}
        ]
      ]
  });









});
</script>
</body>
</html>