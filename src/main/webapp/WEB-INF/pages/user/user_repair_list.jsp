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
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除选中维修单</button>
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
    ,url:'/user/user_repaired_list' //数据接口
    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
    ,method:'post'
    ,page: true
    ,cols: [
        [ //表头
      {type: 'checkbox', fixed: 'left'} //选择框
      ,{field: 'repaired_id', title: '维修单号', sort: true}
      ,{field: 'user_name', title: '发布者名称'}
      ,{field: 'topic', title: '概述'}
      ,{field: 'content', title: '具体描述'}
      ,{field: 'location', title: '楼栋',sort:true}
      ,{field: 'publish_time', title: '发布时间',sort: true}
      ,{field: 'picture_path', title: '详情图片',
            templet:function (d) {
                                                    //tomcat已配置虚拟路径，（/ssm）
                return '<div οnclick="photograph(this)"><img src='+/ssm/+d.picture_path+'></div>'
            }
       }
      ,{field: 'status', title: '审核状态',sort: true}
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
                layer.confirm('想清楚了，确定删除吗？',{btn:["确定","取消"]},
                    function () {
                       //删除数据
                       var repaired_id = [];
                        for(var i in data){
                            repaired_id[i] = data[i].repaired_id;
                        }
                       $.ajax({
                           url:"/user/user_delete_repaired",
                           data:{repaired_id:repaired_id},
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


function photograph(t) {

    var t = $(t).find("img");
    if (t == null || t == '') {
        return;
    }
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['75%', '85%'], //宽高
        shadeClose: true, //开启遮罩关闭
        end: function (index, layero) {
            return false;
        },
        content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
    })
}

</script>
</body>
</html>
