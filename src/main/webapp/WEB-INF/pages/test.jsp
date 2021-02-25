<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/1/28
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ywp.eums.VisitorRegisteredEnum"%>
<html>
<head>
    <title>游客注册</title>
    <link rel="stylesheet" type="text/css" href="../../css/visitor/visitor_registered.css">

    <script type="text/javascript">
        function toLogin(){
              document.form.action="/main/toLogin";
              document.form.submit();
        }

        function checkAll(){
                    var name = checkName();
                    var password = checkPassword();
                    var same = checkSame();
                    var phonenumber = checkPhoneNumber();
                    var carnumber = checkCarNumber();
                    if(name&&password&&same&&phonenumber&&carnumber){
                        return true;
                    }else{
                        return false;
                    }
                }

        // 检查用户名
        function checkName() {
           var name = document.getElementById("name").value;
           var spanNode = document.getElementById("namespan");
           //用户名的规则： 昵称的长度为1-15，包含任意的字母、数字、中文，不可以使用其他符号
           var reg = /^([\u4e00-\u9fa5]|[a-zA-Z0-9]){1,15}$/i;
           if(reg.test(name)){
               //符合规则
               spanNode.innerHTML = "".fontcolor("green");
               document.getElementById("nameclass").className = "icon ticker";
               return true;
           }else{
               //不符合规则
               spanNode.innerHTML = "用户名的长度为1-15，包含任意的字母、数字、中文，不可以使用其他符号".fontcolor("red");
               document.getElementById("nameclass").className = "icon into";
               return false;
           }
        }

        //检查密码
        function checkPassword() {
           var password = document.getElementById("password").value;
             var spanNode = document.getElementById("passwordspan");
             //密码的规则： 6-16，包含任意的字母、数字，不可以使用其他符号
             var reg = /^([a-zA-Z0-9]){6,16}$/i;
             if(reg.test(password)){
                 //符合规则
                 spanNode.innerHTML = "".fontcolor("green");
                 document.getElementById("passwordclass").className = "icon ticker";
                 return true;
             }else{
                 //不符合规则
                 spanNode.innerHTML = "密码的长度为 6-16，包含任意的字母、数字，不可以使用其他符号".fontcolor("red");
                 document.getElementById("passwordclass").className = "icon into";
                 return false;
             }
        }

        //检查确认密码
        function checkSame() {
         var password = document.getElementById("password").value;
         var password2 = document.getElementById("password2").value;
         var spanNode = document.getElementById("passwordspan2");
         //密码的规则： 6-16，包含任意的字母、数字，不可以使用其他符号
         var reg = /^([a-zA-Z0-9]){6,16}$/i;
         //确认密码输入符合规则
         if(reg.test(password2)){
            //符合规则
            if(password == password2){
                spanNode.innerHTML = "".fontcolor("green");
                document.getElementById("passwordclass2").className = "icon ticker";
                return true;
            }else{
                spanNode.innerHTML = "两次输入的密码不一致，请重新输入！".fontcolor("red");
                document.getElementById("passwordclass2").className = "icon into";
                return false;
            }
         } else{  //确认输入密码不符合规则
              spanNode.innerHTML = "密码的长度为 6-16，包含任意的字母、数字，不可以使用其他符号".fontcolor("red");
              document.getElementById("passwordclass2").className = "icon into";
              return false;
         }
        }


        //检查手机号码
        function checkPhoneNumber() {
            var password = document.getElementById("phonenumber").value;
              var spanNode = document.getElementById("phonenumberspan");
              //手机的规则： 11位只能是数字，不可以使用其他符号
              var reg = /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/i;
              if(reg.test(password)){
                  //符合规则
                  spanNode.innerHTML = "".fontcolor("green");
                  document.getElementById("phonenumberclass").className = "icon ticker";
                  return true;
              }else{
                  //不符合规则
                  spanNode.innerHTML = "手机号码为11位，请检查后重新输入真实的手机号码！".fontcolor("red");
                  document.getElementById("phonenumberclass").className = "icon into";
                  return false;
              }
        }

        //检查车牌
        function checkCarNumber() {
           var carnumber = document.getElementById("carnumber").value;
           var spanNode = document.getElementById("carnumberspan");
           //车牌的规则： 正确填写
           var reg = /^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[a-zA-Z](([DF]((?![IO])[a-zA-Z0-9](?![IO]))[0-9]{4})|([0-9]{5}[DF]))|[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1})$/
           ;
           if (reg.test(carnumber)) {
               //符合规则
               spanNode.innerHTML = "".fontcolor("green");
               document.getElementById("carnumberclass").className = "icon ticker";
               return true;
           } else {
               //不符合规则
               spanNode.innerHTML = "请输入真实的汽车牌照，必须要填写".fontcolor("red");
               document.getElementById("carnumberclass").className = "icon into";
               return false;
           }
        }
    </script>

</head>
<body style="background: url(../../images/登录注册背景图.jpg); background-size: 100% 100%">
<c:if test="${(param.info_msg != '') || !(empty param.info_msg) }">
    <script> alert("${param.info_msg}");</script>
</c:if>
    <div class="loginDiv">
        <form name="form" action="/visitor/visitor_registered" method="post" onsubmit="return checkAll()">
            <h1 style="color: rgba(100, 149, 237, .7);margin-left: 30px">欢&nbsp;迎&nbsp;游&nbsp;客&nbsp;注&nbsp;册</h1>
            <p>用&nbsp; 户&nbsp; 名:<input type="text" name="visitor_name" placeholder="请输入用户名" autocomplete="off" onblur="checkName()" id="name"><a href="#" id="nameclass" class=""> </a></br>
                <span  style="font-size:13px" id="namespan"></span>
            </p>

            <p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="password" name="visitor_password" placeholder="请输入密码" onblur="checkPassword()" id="password"><a href="#" id="passwordclass" class=""> </a></br>
                <span  style="font-size:13px" id="passwordspan"></span>
            </p>

            <p>确认密码:<input type="password" name="password2" placeholder="请再次输入密码" onblur="checkSame()" id="password2"><a href="#" id="passwordclass2" class=""> </a></br>
                <span  style="font-size:13px" id="passwordspan2"></span>
            </p>

            <p>联系方式:<input type="text" name="visitor_phonenumber" placeholder="请输入11位电话号码" autocomplete="off" onblur="checkPhoneNumber()" id="phonenumber"><a href="#" id="phonenumerclass" class=""> </a></br>
                <span  style="font-size:13px" id="phonenumberspan"></span>
            </p>

            <p>车&nbsp; 牌&nbsp;号:<input type="text" name="visitor_carnumber" placeholder="请输入真实车牌号" autocomplete="off" onblur="checkCarNumber()" id="carnumber"><a href="#" id="carnumerclass" class=""> </a></br>
                <span  style="font-size:13px" id="carnumberspan"></span>
            </p>

            <div style="margin-top: 30px;margin-left: 30px">
                <input type="submit" class="button"  value="注册" >&nbsp;&nbsp;&nbsp;
                <input type="button" class="button" value="前往登录" onclick="toLogin()">
            </div>
        </form>
    </div>

</body>
</html>

