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