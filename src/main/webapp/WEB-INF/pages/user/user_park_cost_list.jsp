<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/25
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
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

<table class="layui-hide" id="cost" lay-filter="cost" ></table>

<script src="../../../layui/layui.js"  charset="utf-8"></script>
<script>
layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#cost'
    ,url:'/user/user_park_cost_list' //数据接口
    ,method:'post'
    ,page: true
    ,cols: [
        [ //表头
       {field: 'cost_id', title: '停车账单号', sort: true}
      ,{field: 'user_name', title: '业主名称'}
      ,{field: 'user_carnumber', title: '业主牌号'}
      ,{field: 'park_start_time', title: '开始停车时间',sort: true}
      ,{field: 'park_end_time', title: '结束停车时间',sort: true}
      ,{field: 'period', title: '时长（分钟）',sort: true}
      ,{field: 'cost', title: '金额（单位：元，5元/天）：',sort: true}
      ,{field: 'status', title: '缴费状态',sort: true}
        ]
      ]
  });

});
</script>
</body>
</html>