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
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除选中帖子</button>
  </div>
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
    ,url:'/admin/admin_article_list' //数据接口
    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
    ,method:'post'
    ,page: true
    ,cols: [
        [ //表头
      {type: 'checkbox', fixed: 'left'} //选择框
      ,{field: 'article_id', title: '帖子ID', sort: true}
      ,{field: 'people_name', title: '发布者名称'}
      ,{field: 'topic', title: '主题'}
      ,{field: 'content', title: '内容'}
      ,{field: 'publish_time', title: '发布时间',sort: true}
        ]
      ]
  });
    //头工具栏事件
      table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus("test");
        switch(obj.event){
          case 'delete':
            var data = checkStatus.data; //当前选中的数据
            if(data.length > 0){
                layer.confirm('想清楚了，确定删除选中的帖子吗？',{btn:["确定","取消"]},
                    function () {
                       //删除数据
                       var article_id = [];
                        for(var i in data){
                            article_id[i] = data[i].article_id;
                        }
                       $.ajax({
                           url:"/admin/admin_delete_article",
                           data:{article_id:article_id},
                           method:'post',
                           traditional:true,
                           success:function (result) {
                               if(result.status){
                                 table.reload('test',{});//重新加载数据
                               }else {
                                   alert(result.message);
                               }
                               layer.closeAll('dialog'); //关闭信息框,可以关闭，有效
                           }
                       })
                      }
                ,function () {
                        layer.close(layer.index);
                    }
                )
            }
            break;
        };
      });
});
</script>
</body>
</html>