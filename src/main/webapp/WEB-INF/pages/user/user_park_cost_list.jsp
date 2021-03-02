<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/25
  Time: 11:02
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
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="park_cost">缴费</a>
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
    ,url:'/user/user_park_cost_list' //数据接口
    ,method:'post'
    ,page: true
    ,cols: [
        [ //表头
       {field: 'park_id', title: '停车账单号', sort: true}
      ,{field: 'user_name', title: '业主名称'}
      ,{field: 'user_carnumber', title: '汽车牌号'}
      ,{field: 'park_start_time', title: '开始停车时间',width:160,sort: true}
      ,{field: 'park_end_time', title: '结束停车时间',width:160,sort: true}
      ,{field: 'period', title: '时长（分钟）',width:130,sort: true}
      ,{field: 'cost', title: '金额（单位：元，5元/天）：',sort: true}
      ,{field: 'status', title: '缴费状态',sort: true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]
      ]
  });




    //监听行工具事件
     table.on('tool(test)', function(obj){
         var checkStatus =obj.data;
         var park_id = checkStatus.park_id;
         if(obj.event === 'park_cost'){
             layer.confirm('停车账单号为"'+park_id+'"的账单确定缴费吗？',{btn:["确定","取消"]},
             //确定事件
             function () {
                 $.ajax({
                   url:"/user/delivery_park",
                   data:"park_id="+park_id,
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