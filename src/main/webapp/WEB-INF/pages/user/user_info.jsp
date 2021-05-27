<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/2/25
  Time: 11:00
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
    <form class="layui-form layui-form-pane" action="/user/user_info_update" style="text-align: center">
      <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">业主名称：</label>
            <div class="layui-input-inline">
               <input type="text" name="user_name" autocomplete="off" placeholder="${user.user_name}" class="layui-input" value="${user.user_name}">
            </div>
            <div class="layui-form-mid layui-word-aux">要改，请务必填写业主名称！！！</div>
          </div>
      </div>
      <div class="layui-form-item" >
          <div class="layui-inline">
              <label class="layui-form-label">业主密码：</label>
              <div class="layui-input-inline">
                 <input type="text" name="user_password" autocomplete="off" placeholder="${user.user_password}" class="layui-input" value="${user.user_password}">
              </div>
              <div class="layui-form-mid layui-word-aux">要改，请务必填写业主密码！！！</div>
          </div>
      </div>
    <div class="layui-form-item" >
       <div class="layui-inline">
          <label class="layui-form-label">身份证号：</label>
          <div class="layui-input-inline">
            <input type="text" name="user_idcard" autocomplete="off" placeholder="${user.user_idcard}" class="layui-input" value="${user.user_idcard}">
          </div>
           <div class="layui-form-mid layui-word-aux">要改，请务必填写身份证号！！！</div>
       </div>
    </div>
    <div class="layui-form-item" >
        <div class="layui-inline">
           <label class="layui-form-label">联系方式：</label>
           <div class="layui-input-inline">
             <input type="text" name="user_phonenumber" autocomplete="off" placeholder="${user.user_phonenumber}" class="layui-input" value="${user.user_phonenumber}">
           </div>
            <div class="layui-form-mid layui-word-aux">要改，请务必填写联系方式！！！</div>
        </div>
     </div>
    <div class="layui-form-item" >
        <div class="layui-inline">
           <label class="layui-form-label">房间号码：</label>
           <div class="layui-input-inline">
             <input type="text" name="user_address" autocomplete="off" placeholder="${user.user_address}" class="layui-input" value="${user.user_address}">
           </div>
            <div class="layui-form-mid layui-word-aux">要改，请务必填写房间号码！！！</div>
        </div>
     </div>
    <div class="layui-form-item" >
        <div class="layui-inline">
           <label class="layui-form-label">住房面积：</label>
           <div class="layui-input-inline">
             <input type="text" name="user_area" autocomplete="off" placeholder="${user.user_area}" class="layui-input" value="${user.user_area}">
           </div>
            <div class="layui-form-mid layui-word-aux">要改，请务必填写住房面积！！！</div>
        </div>
     </div>
    <div class="layui-form-item" >
       <div class="layui-inline">
          <label class="layui-form-label">车牌号：</label>
          <div class="layui-input-inline">
            <input type="text" name="user_carnumber" autocomplete="off" placeholder="${user.user_carnumber}" class="layui-input" value="${user.user_carnumber}">
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
