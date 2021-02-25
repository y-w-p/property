<%--
  Created by IntelliJ IDEA.
  User: 喻伟平
  Date: 2021/1/28
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>业主注册</title>
    <link rel="stylesheet" type="text/css" href="../../../css/user/user_registered.css">
    <script type="text/javascript">
        function toLogin(){
              document.form.action="/main/toLogin";
              document.form.submit();
        }

        function checkAll(){
            var name = checkName();
            var password = checkPassword();
            var same = checkSame();
            var idcard = checkIdCard();
            var phonenumber = checkPhoneNumber();
            var address = checkAddress();
            var area = checkArea();
            var carnumber = checkCarNumber();

            if(name&&password&&same&&idcard&&phonenumber&&address&&area&&carnumber){
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

        //检查身份证
        function checkIdCard() {
            var idcard = document.getElementById("idcard").value;
            var spanNode = document.getElementById("idcardspan");
            //身份证的规则： 18位的数字+X，不可以使用其他符号
            var reg =  /^[1-9][0-9]{5}([1][9][0-9]{2}|[2][0][0|1][0-9])([0][1-9]|[1][0|1|2])([0][1-9]|[1|2][0-9]|[3][0|1])[0-9]{3}([0-9]|[X])$/;
            if(reg.test(idcard)){
                //符合规则
                spanNode.innerHTML = "".fontcolor("green");
                document.getElementById("idcardclass").className = "icon ticker";
                return true;
            }else{
                //不符合规则
                spanNode.innerHTML = "身份证输入有误，请检查后重新输入！".fontcolor("red");
                document.getElementById("idcardclass").className = "icon into";
                return false;
            }
        }

        //检查手机号码
        function checkPhoneNumber() {
            var phonenumber = document.getElementById("phonenumber").value;
              var spanNode = document.getElementById("phonenumberspan");
              //手机的规则： 11位只能是数字，不可以使用其他符号
              var reg = /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/i;
              if(reg.test(phonenumber)){
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

        //检查地址
        function checkAddress() {
            var address = document.getElementById("address").value;
           var spanNode = document.getElementById("addressspan");
           //地址的规则： 地址的长度为1-20，包含任意的字母、数字、中文，不可以使用其他符号
           var reg = /^([\u4e00-\u9fa5]|[a-zA-Z0-9]){1,20}$/i;
           if(reg.test(address)){
               //符合规则
               spanNode.innerHTML = "".fontcolor("green");
               document.getElementById("addressclass").className = "icon ticker";
               return true;
           }else{
               //不符合规则
               spanNode.innerHTML = "家庭住址的长度为1-20，包含任意的字母、数字、中文，不可以使用其他符号".fontcolor("red");
               document.getElementById("addressclass").className = "icon into";
               return false;
           }

        }

         //检查面积
        function checkArea() {
            var area = document.getElementById("area").value;
             var spanNode = document.getElementById("areaspan");
             //面积的规则： 大于0小于1000，保留一位小数
             var reg = /^([1-9]\d{0,2}|1000)(\.\d{1})?$/;
             if(reg.test(area)){
                 //符合规则
                 spanNode.innerHTML = "".fontcolor("green");
                 document.getElementById("areaclass").className = "icon ticker";
                 return true;
             }else{
                 //不符合规则
                 spanNode.innerHTML = "住房面积大于0小于1000，保留一位小数".fontcolor("red");
                 document.getElementById("areaclass").className = "icon into";
                 return false;
             }
        }

        //检查车牌
        function checkCarNumber() {
            var carnumber = document.getElementById("carnumber").value;
            var spanNode = document.getElementById("carnumberspan");
            //车牌的规则： 正确填写
            var reg = /(^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[a-zA-Z](([DF]((?![IO])[a-zA-Z0-9](?![IO]))[0-9]{4})|([0-9]{5}[DF]))|[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1})?$)/;
           if (reg.test(carnumber)) {
                //符合规则
                spanNode.innerHTML = "".fontcolor("green");
                document.getElementById("carnumberclass").className = "icon ticker";
                return true;
            } else {
                //不符合规则
                spanNode.innerHTML = "请输入真实的汽车牌照或不输入任何".fontcolor("red");
                document.getElementById("carnumberclass").className = "icon into";
                return false;
            }
        }


    </script>
</head>
<body style="background: url(../../../images/登录注册背景图.jpg); background-size: 100% 100%">

    <div class="loginDiv">
        <form name="form" action="/user/user_registered" method="post" onsubmit="return checkAll()">
            <h1 style="color: rgba(100, 149, 237, .7);margin-left: 55px;">欢&nbsp;迎&nbsp;业&nbsp;主&nbsp;注&nbsp;册</h1>


            <p>用&nbsp; 户&nbsp; 名:<input type="text" name="user_name" placeholder="请输入用户名" autocomplete="off" onblur="checkName()" id="name"><a href="#" id="nameclass" class=""> </a></br>
                <span  style="font-size:13px" id="namespan"></span></p>

            <p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="password" name="user_password" placeholder="请输入密码" onblur="checkPassword()" id="password"><a href="#" id="passwordclass" class=""> </a></br>
                <span  style="font-size:13px" id="passwordspan"></span></p>

            <p>确认密码:<input type="password" name="password2" placeholder="请再次输入密码" onblur="checkSame()" id="password2"><a href="#" id="passwordclass2" class=""> </a></br>
               <span  style="font-size:13px" id="passwordspan2"></span>
            </p>

            <p>身份证号:<input type="text" name="user_idcard" placeholder="请输入身份证号码" autocomplete="off" onblur="checkIdCard()" id="idcard"><a href="#" id="idcardclass" class=""> </a></br>
                          <span  style="font-size:13px" id="idcardspan"></span>
            </p>

            <p>联系方式:<input type="text" name="user_phonenumber" placeholder="请输入11位电话号码" autocomplete="off" onblur="checkPhoneNumber()" id="phonenumber"><a href="#" id="phonenumerclass" class=""> </a></br>
               <span  style="font-size:13px" id="phonenumberspan"></span>
            </p>


            <p>家庭住址:<input type="text" name="user_address" placeholder="请输入住址,例如：光明小区9栋601" autocomplete="off" onblur="checkAddress()" id="address"><a href="#" id="addressclass" class=""> </a></br>
               <span  style="font-size:13px" id="addressspan"></span>
            </p>

            <p>住房面积:<input type="text" name="user_area" placeholder="请输入真实面积(单位:㎡),例如：120.0" autocomplete="off" onblur="checkArea()" id="area"><a href="#" id="areaclass" class=""> </a></br>
               <span  style="font-size:13px" id="areaspan"></span>
            </p>

            <p>车&nbsp; 牌&nbsp;号:<input type="text" name="user_carnumber" placeholder="请输入真实车牌号,没有就什么都不要填" autocomplete="off" autocomplete="off" onblur="checkCarNumber()" id="carnumber"><a href="#" id="carnumerclass" class=""> </a></br>
                <span  style="font-size:13px" id="carnumberspan"></span>
            </p>

            <div style="margin-top: 30px;margin-left: 90px">
                <input type="submit" class="button"  value="注册" >&nbsp;&nbsp;&nbsp;
                <input type="button" class="button" value="前往登录" onclick="toLogin()">
            </div>
        </form>
    </div>

</body>
</html>

