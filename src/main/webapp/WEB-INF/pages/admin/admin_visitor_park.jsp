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
    <label class="layui-form-label">查询记录:</label>
    <div class="layui-inline" >
        <input class="layui-input" name="park_location" id="demoReload1" autocomplete="off" placeholder="请输入停车位">
    </div>
    <div class="layui-inline" >
       <input class="layui-input" name="visitor_carnumber" id="demoReload2" autocomplete="off" placeholder="请输入汽车牌号">
   </div>
    <div class="layui-inline">
    <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
    </div>
</div>



<table class="layui-hide" id="test" lay-filter="test" ></table>



<%--头部工具栏--%>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="visitor_stop_park">结束</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="visitor_pay_park">缴费</a>
</script>



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
    ,url:'/admin/admin_visitor_park_list' //数据接口
    ,method:'get'
    ,page: true
    ,id: 'testReload'
    ,cols: [
        [ //表头
       {field: 'park_id', title: '停车单号', sort: true}
      ,{field: 'visitor_id', title: '游客编号', sort: true}
      ,{field: 'visitor_carnumber', title: '车牌号',sort: true}
      ,{field: 'park_location', title: '停车位',sort: true}
      ,{field: 'park_start_time', title: '开始停车时间',width:170, sort: true}
      ,{field: 'park_end_time', title: '结束停车时间',width:170, sort: true}
      ,{field: 'cost', title: '金额(元)', width:100, sort: true}
      ,{field: 'status', title: '状态', sort: true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]
      ]
  });





        var $ = layui.$, active = {
            reload: function () {
                var park_location = $('#demoReload1');
                var visitor_carnumber = $('#demoReload2');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        park_location:park_location.val(),
                        visitor_carnumber:visitor_carnumber.val()
                    }
                }, 'data');
            }
        };
            $('.demoTable .layui-btn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });





        //操作
        //监听行工具事件
         table.on('tool(test)', function(obj){
             var checkStatus =obj.data;
             var park_id = checkStatus.park_id;
             var visitor_carnumber = checkStatus.visitor_carnumber;
             //结束操作
             if(obj.event === 'visitor_stop_park'){
                 layer.confirm('该车'+visitor_carnumber+'确定结束停车吗？',{btn:["确定","取消"]},
                 //确定事件
                 function () {
                     $.ajax({
                       url:"/admin/admin_visitor_stop_park",
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



            //缴费操作
              if(obj.event === 'visitor_pay_park'){
                  layer.confirm('停车单号为'+park_id+'的账单确定缴费吗？',{btn:["确定","取消"]},
                  //确定事件
                  function () {
                      $.ajax({
                        url:"/admin/admin_visitor_pay_park",
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