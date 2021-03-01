<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.lang.String" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
    <title>停车登记</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css"  media="all">
</head>
<body class="layui-bg-gray">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>停车登记</legend>
</fieldset>
    <form class="layui-form layui-form-pane" action="/admin/admin_parking" enctype="multipart/form-data" method="post">

    <div class="layui-form">
    <div class="layui-form-item">

        <div class="layui-inline">
         <label class="layui-form-label">输入ID：</label>
            <div class="layui-input-inline">
           <input type="text" name="id" id="id" class="layui-input" placeholder="例如：1" autocomplete="off">
            </div>
        </div>



        <div class="layui-inline">
        <label class="layui-form-label">选择角色：</label>
            <div class="layui-input-inline">
              <select name="role" id="role" lay-verify="required" lay-search="" >
                <option value="">直接选择或搜索选择</option>
                <option value="业主">业主</option>
                <option value="游客">游客</option>
              </select>
            </div>
        </div>

        </br>
        </br>
        </br>

        <div class="layui-inline">
        <label class="layui-form-label">输入车牌：</label>
        <div class="layui-input-inline">
          <input type="text" name="carnumber" id="carnumber" class="layui-input" placeholder="例如：赣F66666" autocomplete="off">
        </div>
        </div>


        <div class="layui-inline">
           <label class="layui-form-label">选择车位：</label>
            <div class="layui-input-inline">
              <select name="park_location" id="park_location" lay-verify="required" lay-search="">
                <option value="">直接选择或搜索选择</option>
                <option value="1号车位">1号车位</option>
                <option value="2号车位">2号车位</option>
                <option value="3号车位">3号车位</option>
                <option value="4号车位">4号车位</option>
                <option value="5号车位">5号车位</option>
                <option value="6号车位">6号车位</option>
                <option value="7号车位">7号车位</option>
                <option value="8号车位">8号车位</option>
                <option value="9号车位">9号车位</option>
                <option value="10号车位">10号车位</option>
                <option value="11号车位">11号车位</option>
                <option value="12号车位">12号车位</option>
                <option value="13号车位">13号车位</option>
                <option value="14号车位">14号车位</option>
                <option value="15号车位">15号车位</option>
                <option value="16号车位">16号车位</option>
                <option value="17号车位">17号车位</option>
                <option value="18号车位">18号车位</option>
                <option value="19号车位">19号车位</option>
                <option value="20号车位">20号车位</option>
                <option value="21号车位">21号车位</option>
                <option value="22号车位">22号车位</option>
                <option value="23号车位">23号车位</option>
                <option value="24号车位">24号车位</option>
                <option value="25号车位">25号车位</option>
                <option value="26号车位">26号车位</option>
                <option value="27号车位">27号车位</option>
                <option value="28号车位">28号车位</option>
                <option value="29号车位">29号车位</option>
                <option value="30号车位">30号车位</option>
              </select>
            </div>
        </div>


    </div>

       </br>



    <div class="layui-form-item" style="margin-left: 210px">
          <button type="submit" class="layui-btn" lay-submit="" lay-filter="save">开始停车</button>
          <button type="reset" class="layui-btn " lay-submit="" lay-filter="return">取消登记</button>
        </div>


    </div>
    </form>


<%
    String msg = (String) request.getSession().getAttribute("park_msg");
    if(msg != null){

%>
<script type="text/javascript">
    alert("<%=msg%>");
</script>

<% }%>
<script src="../../../layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['form' ,'layer'], function(){
  var form = layui.form
  ,layer = layui.layer

});


</script>


</body>
</html>
