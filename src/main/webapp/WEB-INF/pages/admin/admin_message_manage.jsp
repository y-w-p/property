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
          <input class="layui-input" name="user_name" id="demoReload" autocomplete="off" placeholder="输入业主名称">
      </div>
      <div class="layui-inline" >
          <input class="layui-input" name="topic" id="demoReload1" autocomplete="off" placeholder="请输入主题">
      </div>
      <div class="layui-inline" >
          <input class="layui-input" name="content" id="demoReload2" autocomplete="off" placeholder="请输入相关内容">
      </div>
      <div class="layui-inline">
          <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
      </div>
</div>


<%--头部工具栏--%>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除选中帖子</button>
  </div>
</script>



<table class="layui-hide" id="test" lay-filter="test" ></table>



<script src="../../../layui/layui.js"  charset="utf-8"></script>
<script>
layui.use(['jquery', 'table', 'layer', 'tree'], function(){
    var	$ = layui.jquery,
     table = layui.table,
     layer = layui.layer,
     tree = layui.tree;


  //第一个实例
  table.render({
    elem: '#test'
    ,url:'/admin/admin_message_list' //数据接口
    ,method:'post'
    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
    ,page: true
    ,id: 'testReload'
    ,cols: [
        [ //表头
       {type: 'checkbox', fixed: 'left'} //选择框
      ,{field: 'message_id', title: '通告单号', sort: true}
      ,{field: 'admin_name', title: '管理员名名称'}
      ,{field: 'user_name', title: '业主名称',sort:true}
      ,{field: 'topic', title: '主题',sort:true}
      ,{field: 'content', title: '内容',sort:true}
      ,{field: 'publish_time', title: '发布时间',width:180,sort: true}
      ,{field: 'status', title: '阅读状态',sort: true}
        ]
      ]
  });





        var $ = layui.$, active = {
            reload: function () {
                var user_name = $('#demoReload');
                var topic = $('#demoReload1');
                var content = $('#demoReload2');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        user_name: user_name.val(),
                        topic: topic.val(),
                        content: content.val()
                    }
                }, 'data');
            }
        };

                $('.demoTable .layui-btn').on('click', function () {
                   var type = $(this).data('type');
                   active[type] ? active[type].call(this) : '';
               });





    //头工具栏事件
      table.on('toolbar(test)', function(obj){
          var checkStatus = table.checkStatus(obj.config.id);//不要写'test',换成'obj.config.id'
          var data = checkStatus.data; //当前选中的数据
          //确定勾选数据
          if(data.length > 0){
              if(obj.event === 'delete'){
               layer.confirm('想清楚了，确定删除选中的帖子吗？',{btn:["确定","取消"]},
                   //确定事件
                   function () {
                      //删除数据
                      var message_id = [];
                      for(var i in data){
                          message_id[i] = data[i].message_id;
                       }
                      $.ajax({
                          url:"/admin/admin_delete_message",
                          data:{message_id:message_id},
                          method:'post',
                          traditional:true,
                          success:function (result) {
                              if(result.status){
                                table.reload('testReload',{});//重新加载数据,不再是'test',而是表格新id：testReload
                              }else {
                                  alert(result.message);
                              }
                              layer.closeAll('dialog'); //关闭信息框,可以关闭，有效
                          }
                      })
                     }

                 //取消时间
               ,function () {
                       layer.close(layer.index);
                   }
               )}
          }




        });










});
</script>
</body>
</html>