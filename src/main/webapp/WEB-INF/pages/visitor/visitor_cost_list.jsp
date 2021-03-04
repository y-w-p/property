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


<div class="demoTable" >
    <label class="layui-form-label">查询停车单:</label>
    <div class="layui-inline" >
        <input class="layui-input" name="park_id" id="demoReload1" autocomplete="off" placeholder="请输入停车单号">
    </div>
    <div class="layui-inline" >
        <input class="layui-input" name="visitor_carnumber" id="demoReload2" autocomplete="off" placeholder="请输入车牌号">
    </div>
    <div class="layui-inline">
    <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
    </div>
</div>



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
    ,url:'/visitor/visitor_park_cost_list' //数据接口
    ,method:'post'
    ,id: 'testReload'
    ,page: true
    ,cols: [
        [ //表头
       {field: 'park_id', title: '停车账单号', sort: true}
      ,{field: 'visitor_name', title: '游客名称'}
      ,{field: 'visitor_carnumber', title: '游客车牌号'}
      ,{field: 'park_start_time', title: '开始停车时间',width:160,sort: true}
      ,{field: 'park_end_time', title: '结束停车时间',width:160,sort: true}
      ,{field: 'period', title: '时长（分钟）',width:130,sort: true}
      ,{field: 'cost', title: '金额（单位：元，5元/h）：',sort: true}
      ,{field: 'status', title: '缴费状态',sort: true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]
      ]
  });






    var $ = layui.$, active = {
          reload: function () {
              var park_id = $('#demoReload1');
              var visitor_carnumber = $('#demoReload2');
              //执行重载
              table.reload('testReload', {
                  page: {
                      curr: 1 //重新从第 1 页开始
                  }
                  , where: {
                      park_id:park_id.val(),
                      visitor_carnumber: visitor_carnumber.val()
                  }
              }, 'data');
          }
      };
          $('.demoTable .layui-btn').on('click', function () {
              var type = $(this).data('type');
              active[type] ? active[type].call(this) : '';
          });






    //监听行工具事件
     table.on('tool(test)', function(obj){
         var checkStatus =obj.data;
         var park_id = checkStatus.park_id;
         if(obj.event === 'park_cost'){
             layer.confirm('停车单号为"'+park_id+'"的账单确定缴费吗？',{btn:["确定","取消"]},
             //确定事件
             function () {
                 $.ajax({
                   url:"/visitor/delivery_park",
                   data:"park_id="+park_id,
                   method:'post',
                   traditional:true,
                   success:function (result) {
                       if(result.status){
                         table.reload('testReload',{});//重新加载数据
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