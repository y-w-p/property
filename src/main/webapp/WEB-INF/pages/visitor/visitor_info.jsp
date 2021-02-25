<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/22
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <form class="layui-form layui-form-pane" action="/visitor/visitor_info_update?visitor_id=${visitor.visitor_id}" style="text-align: center">
      <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">游客名称：</label>
        <div class="layui-input-inline">
          <input type="text" name="visitor_name" autocomplete="off" placeholder="${visitor.visitor_name}" class="layui-input" value="${visitor.visitor_name}">
        </div>
          <div class="layui-form-mid layui-word-aux">要改，请务必填写游客名称！！！</div>
      </div>
      </div>
    <div class="layui-form-item" >
        <div class="layui-inline">
           <label class="layui-form-label">游客密码：</label>
           <div class="layui-input-inline">
             <input type="text" name="visitor_password" autocomplete="off" placeholder="${visitor.visitor_password}" class="layui-input" value="${visitor.visitor_password}">
           </div>
            <div class="layui-form-mid layui-word-aux">要改，请务必填写游客密码！！！</div>
        </div>
    </div>
    <div class="layui-form-item" >
        <div class="layui-inline">
           <label class="layui-form-label">联系方式：</label>
           <div class="layui-input-inline">
             <input type="text" name="visitor_phonenumber" autocomplete="off" placeholder="${visitor.visitor_phonenumber}" class="layui-input" value="${visitor.visitor_phonenumber}">
           </div>
            <div class="layui-form-mid layui-word-aux">要改，请务必填写联系方式！！！</div>
        </div>
     </div>
    <div class="layui-form-item" >
       <div class="layui-inline">
          <label class="layui-form-label">车牌号：</label>
          <div class="layui-input-inline">
            <input type="text" name="visitor_carnumber" autocomplete="off" placeholder="${visitor.visitor_carnumber}" class="layui-input" value="${visitor.visitor_carnumber}">
          </div>
           <div class="layui-form-mid layui-word-aux">要改，请务必填写汽车牌号！！！</div>
       </div>
    </div>

    <div class="layui-form-item" >
        <div class="layui-inline">
          <button type="submit" class="layui-btn" lay-submit="" lay-filter="save">确认更改</button>
          <button type="reset" class="layui-btn " lay-submit="" lay-filter="return">取消更改</button>
        </div>
      </div>
    </form>


<%
    String msg = (String) request.getSession().getAttribute("update_info_msg");
    if(msg != null){

%>
<script type="text/javascript">
    window.alert("<%=msg%>");
</script>

<% }%>
<script src="../../../layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use(['form' ,'layer'], function(){
  var form = layui.form
  ,layer = layui.layer


  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [
      /^[\S]{6,12}$/
      ,'密码必须6到12位，且不能出现空格'
    ]
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });



  //监听提交
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });





});
</script>


</body>
</html>
