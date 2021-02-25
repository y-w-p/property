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

<%--头部工具栏--%>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="property_cost">缴费</a>
</script>


<script src="../../../layui/layui.js"  charset="utf-8"></script>
<script>
layui.use(['table','jquery','layer'], function(){
  var table = layui.table;
  var layer = layui.layer;
  var $ = layui.jquery;
  //第一个实例
  table.render({
    elem: '#test'
    ,url:'/user/user_property_cost_list' //数据接口
    ,method:'post'
    ,page: true
    ,cols: [
        [ //表头
      {field: 'property_id', title: '物业账单号', sort: true}
      ,{field: 'admin_name', title: '物业管理者'}
      ,{field: 'user_name', title: '业主名称'}
      ,{field: 'year', title: '年份'}
      ,{field: 'month', title: '月份',sort:true}
      ,{field: 'money', title: '物业费',sort: true}
      ,{field: 'publish_time', title: '发布时间',sort: true}
      ,{field: 'status', title: '缴费状态',sort: true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ]
      ]
  });


    //监听行工具事件
    table.on('tool(test)', function(obj){
        var checkStatus =obj.data;
        var property_id = checkStatus.property_id;
        if(obj.event === 'property_cost'){
            layer.confirm('确定要缴费吗？',{btn:["确定","取消"]},
            //确定事件
            function () {
                $.ajax({
                  url:"/user/delivery_property",
                  data:"property_id="+property_id,
                  method:'post',
                  traditional:true,
                  success:function (result) {
                      if(result.status){
                        table.reload('test',{});//重新加载数据
                      }else {
                          alert(result.message);
                      }
                      layer.closeAll('dialog');//有效
                  }
                })


            }

            //取消事件
            ,function () {
                layer.close();
            }



            )
        }

    });




});
</script>
</body>
</html>