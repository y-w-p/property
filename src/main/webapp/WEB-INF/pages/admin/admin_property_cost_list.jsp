<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/25
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
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

<div class="demoTable" >
    <label class="layui-form-label">查询物业单:</label>
    <div class="layui-inline" >
        <input class="layui-input" name="user_name" id="demoReload" autocomplete="off" placeholder="输入业主名称">
    </div>
    <div class="layui-inline" >
        <input class="layui-input" name="year" id="demoReload1" autocomplete="off" placeholder="请输入年份">
    </div>
    <div class="layui-inline" >
        <input class="layui-input" name="month" id="demoReload2" autocomplete="off" placeholder="请输入月份">
    </div>
    <div class="layui-inline">
    <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
    </div>
</div>



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
    ,method:'get'
    ,page: true
    ,id: 'testReload'
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





        var $ = layui.$, active = {
            reload: function () {
                var user_name = $('#demoReload');
                var year = $('#demoReload1');
                var month = $('#demoReload2');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        user_name: user_name.val(),
                        year: year.val(),
                        month: month.val()
                    }
                }, 'data');
            }
        };
            $('.demoTable .layui-btn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });







});
</script>
</body>
</html>