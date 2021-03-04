<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/25
  Time: 11:00
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

<div class="demoTable" >
      <label class="layui-form-label">查询停车:</label>
      <div class="layui-inline" >
          <input class="layui-input" name="user_carnumber" id="demoReload1" autocomplete="off" placeholder="请输入车牌号">
      </div>
      <div class="layui-inline" >
          <input class="layui-input" name="park_location" id="demoReload2" autocomplete="off" placeholder="请输入相关车位">
      </div>
      <div class="layui-inline">
          <button class="layui-btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</button>
      </div>
</div>

<table class="layui-hide" id="test" lay-filter="test" ></table>

<script src="../../../layui/layui.js"  charset="utf-8"></script>
<script>
layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#test'
    ,url:'/user/user_park_list' //数据接口
    ,method:'post'
    ,id: 'testReload'
    ,page: true
    ,cols: [
        [ //表头
      {field: 'park_id', title: '停车单号', sort: true}
      ,{field: 'user_name', title: '业主名称'}
      ,{field: 'user_carnumber', title: '业主车牌号'}
      ,{field: 'park_location', title: '停车位',  sort: true}
      ,{field: 'park_start_time', title: '开始停车时间',sort: true}
      ,{field: 'park_end_time', title: '结束停车时间',sort: true}
        ]
      ]
  });




        var $ = layui.$, active = {
              reload: function () {
                  var user_carnumber = $('#demoReload1');
                  var park_location = $('#demoReload2');
                  //执行重载
                  table.reload('testReload', {
                      page: {
                          curr: 1 //重新从第 1 页开始
                      }
                      , where: {
                          user_carnumber: user_carnumber.val(),
                          park_location: park_location.val()
                      }
                  }, 'data');
              }
          };

                  $('.demoTable .layui-btn').on('click', function () {
                     var type = $(this).data('type');
                     active[type] ? active[type].call(this) : '';
                 });











});
</script>
</body>
</html>
