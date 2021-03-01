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
    <label class="layui-form-label">查询业主:</label>
    <div class="layui-inline" >
        <input class="layui-input" name="user_name" id="demoReload" autocomplete="off" placeholder="请输入业主名称">
    </div>
    <div class="layui-inline" >
        <input class="layui-input" name="user_idcard" id="demoReload1" autocomplete="off" placeholder="请输入身份证号">
    </div>
    <div class="layui-inline" >
       <input class="layui-input" name="user_carnumber" id="demoReload2" autocomplete="off" placeholder="请输入汽车牌号">
   </div>
    <div class="layui-inline">
    <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
    </div>
</div>



<table class="layui-hide" id="test" lay-filter="test" ></table>



<%--头部工具栏--%>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="user_del">删除</a>
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
    ,url:'/admin/admin_user_manage' //数据接口
    ,method:'get'
    ,page: true
    ,id: 'testReload'
    ,cols: [
        [ //表头
      {field: 'user_id', title: '业主编号', sort: true}
      ,{field: 'user_name', title: '业主名称',sort:true}
      ,{field: 'user_idcard', title: '身份证号',width:180,sort:true}
      ,{field: 'user_phonenumber', title: '联系电话'}
      ,{field: 'user_address', title: '家庭住址',sort: true}
      ,{field: 'user_area', title: '住房面积',width:180,sort: true}
      ,{field: 'user_carnumber', title: '汽车牌号',sort: true}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]
      ]
  });





        var $ = layui.$, active = {
            reload: function () {
                var user_name = $('#demoReload');
                var user_idcard = $('#demoReload1');
                var user_carnumber = $('#demoReload2');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        user_name: user_name.val(),
                        user_idcard:user_idcard.val(),
                        user_carnumber:user_carnumber.val()
                    }
                }, 'data');
            }
        };
            $('.demoTable .layui-btn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });





        //删除操作
        //监听行工具事件
         table.on('tool(test)', function(obj){
             var checkStatus =obj.data;
             var user_id = checkStatus.user_id;
             if(obj.event === 'user_del'){
                 layer.confirm('确定要删除该业主吗？',{btn:["确定","取消"]},
                 //确定事件
                 function () {
                     $.ajax({
                       url:"/admin/admin_delete_user",
                       data:"user_id="+user_id,
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