<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/22
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.lang.String" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
    <title>游客个人信息</title>
    <link rel="stylesheet" href="../../../layui/css/layui.css"  media="all">
</head>
<body class="layui-bg-gray">
    <form class="layui-form layui-form-pane" action="/user/user_repaired" enctype="multipart/form-data" method="post">

    <div class="layui-form-item">
        <label class="layui-form-label">输入标题：</label>
        <div class="layui-input-inline">
          <input type="text" name="topic" id="topic" autocomplete="off" placeholder="请输入标题(不能为空),概述" class="layui-input" value="">
        </div>


        <label class="layui-form-label">选择楼栋：</label>
            <div class="layui-input-inline">
              <select name="location" id="location" lay-verify="required" lay-search="">
                <option value="">直接选择或搜索选择</option>
                <option value="1楼栋">1楼栋</option>
                <option value="2楼栋">2楼栋</option>
                <option value="3楼栋">3楼栋</option>
                <option value="4楼栋">4楼栋</option>
                <option value="5楼栋">5楼栋</option>
                <option value="6楼栋">6楼栋</option>
                <option value="7楼栋">7楼栋</option>
                <option value="8楼栋">8楼栋</option>
                <option value="9楼栋">9楼栋</option>
                <option value="10楼栋">10楼栋</option>
                <option value="11楼栋">11楼栋</option>
                <option value="12楼栋">12楼栋</option>
                <option value="13楼栋">13楼栋</option>
                <option value="14楼栋">14楼栋</option>
                <option value="15楼栋">15楼栋</option>
                <option value="16楼栋">16楼栋</option>
                <option value="17楼栋">17楼栋</option>
                <option value="18楼栋">18楼栋</option>
                <option value="19楼栋">19楼栋</option>
                <option value="20楼栋">20楼栋</option>
              </select>
       </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">上传图片：</label>
        <div class="layui-input-inline">
        <input type="file" name="file" onchange="showPreview(this)" /><br/><br/>
           	<img id="portrait" src="" style="display:none;width: 100px;height:100px;" /><br/><br/>
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">请描述内容：</label>
        <div class="layui-input-block">
        <textarea name="content" id="content" placeholder="请具体描述一下" autocomplete="off" class="layui-textarea"></textarea>
        </div>
    </div>


    <div class="layui-form-item" >
          <button type="submit" class="layui-btn" lay-submit="" lay-filter="save">上报维修</button>
          <button type="reset" class="layui-btn " lay-submit="" lay-filter="return">取消上报</button>
        </div>
    </form>

<%
    String msg = (String) request.getSession().getAttribute("repaired_msg");
    if(msg != null){

%>
<script type="text/javascript">
    alert("<%=msg%>");
</script>

<% }%>
<script src="../../../layui/layui.js" charset="utf-8"></script>

<script>
layui.use(['form' ,'layer','upload'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,upload = layui.upload

});

function showPreview(source) {
      var file = source.files[0];
      if(window.FileReader) {
          var fr = new FileReader();
          console.log(fr);
          var portrait = document.getElementById('portrait');
          fr.onloadend = function(e) {
            portrait.src = e.target.result;
          };
          fr.readAsDataURL(file);
          portrait.style.display = 'block';
      }
}
</script>


</body>
</html>
