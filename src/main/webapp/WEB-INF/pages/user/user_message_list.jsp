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
    <label class="layui-form-label">查询通告:</label>
    <div class="layui-inline" >
        <input class="layui-input" name="topic" id="demoReload" autocomplete="off" placeholder="请输入主题">
    </div>
    <div class="layui-inline" >
        <input class="layui-input" name="content" id="demoReload1" autocomplete="off" placeholder="请输入相关内容">
    </div>
    <div class="layui-inline">
    <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
    </div>
</div>



<table class="layui-hide" id="test" lay-filter="test" ></table>



<%--头部工具栏--%>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="user_message_look">查看详情</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="user_message_delete">删除</a>
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
    ,url:'/user/user_message_list' //数据接口
    ,method:'get'
    ,page: true
    ,id: 'testReload'
    ,cols: [
        [ //表头
       {field: 'message_id', title: '通告单号', sort: true}
      ,{field: 'admin_name', title: '发送者', sort: true}
      ,{field: 'user_name', title: '接受者',sort: true,hide:true}
      ,{field: 'topic', title: '主题',sort: true}
      ,{field: 'content', title: '内容', sort: true}
      ,{field: 'publish_time', title: '发布时间',hide:true}
      ,{field: 'status', title: '状态', sort: true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]
      ]
  });





        var $ = layui.$, active = {
            reload: function () {
                var topic = $('#demoReload');
                var content = $('#demoReload1');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        topic:topic.val(),
                        content:content.val()
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
             var message_id = checkStatus.message_id;
             var admin_name = checkStatus.admin_name;
             var user_name = checkStatus.user_name;
             var topic = checkStatus.topic;
             var content = checkStatus.content;
             var publish_time = checkStatus.publish_time;
             var status = checkStatus.status;
             //删除操作
             if(obj.event === 'user_message_delete'){
                 layer.confirm('确定删除主题为"'+topic+'"的通告吗？',{btn:["确定","取消"]},
                 //确定事件
                 function () {
                     $.ajax({
                       url:"/user/user_delete_message",
                       data:{message_id:message_id,status:status},
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


            //查看详情弹出内容
            var contentAll =
                 '<div class="layui-col-md12">'+
                    '<div class="layui-card">'+
                         '<div class="layui-card-header">发送者：'+admin_name+'</div>'+
                    '</div>'+
                 '</div>'+
                 '<div class="layui-col-md12">'+
                     '<div class="layui-card">'+
                          '<div class="layui-card-header">主题：'+topic+'</div>'+
                     '</div>'+
                 '</div>'+
                '<div class="layui-col-md12">'+
                     '<div class="layui-card">'+
                          '<div class="layui-card-header">内容：</div>'+
                            '<div class="layui-card-body">'+
                                content+
                            '</div>'+
                     '</div>'+
                 '</div>'+
                '<div class="layui-col-md12">'+
                     '<div class="layui-card">'+
                          '<div class="layui-card-header">发布时间：'+publish_time+'</div>'+
                     '</div>'+
                 '</div>'
                ;


             //查看详情操作
             if(obj.event === 'user_message_look'){
                 layer.confirm(contentAll,{
                     title:'通告详情', //标题
                     btn:["我已阅读","取消"],
                     area:'700px', //宽度
                     skin:'layui-layer-molv', //墨绿皮肤
                     anim:1  //从上掉落
                     },
                 //确定事件
                 function () {
                     $.ajax({
                       url:"/user/user_message_look",
                       data:{message_id:message_id,status:status},
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