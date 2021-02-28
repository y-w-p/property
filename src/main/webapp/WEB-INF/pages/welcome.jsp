<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>显示格式：年月日 时分秒 星期</title>
		<meta http-equiv="content-type" content="text/html; charset=gb2312"/>
        <script src="../../layui/layui.js"></script>
        <link rel="stylesheet" href="../../layui/css/layui.css">
        <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript">
			function Clock() {
				var date = new Date();
				this.year = date.getFullYear();
				this.month = date.getMonth() + 1;
				this.date = date.getDate();
				this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date.getDay()];
				this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
				this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
				this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

				this.toString = function() {
					return this.year + "年" + this.month + "月" + this.date + "日 " + this.hour + ":" + this.minute + ":" + this.second + " " + this.day;
				};

				this.toSimpleDate = function() {
					return this.year + "-" + this.month + "-" + this.date;
				};

				this.toDetailDate = function() {
					return this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;
				};

				this.display = function(ele) {
					var clock = new Clock();
					ele.innerHTML = clock.toString();
					window.setTimeout(function() {clock.display(ele);}, 1000);
				};
			}
		</script>
	</head>
	<body class="layui-bg-green">
    <div class="layui-layout-right"> <span id="clock" style="font-size: medium"></span></div>

    <div class="layui-show" style="margin-top: 20%;margin-left: 10%">
        <h1 style="font-size:60px;color: #01AAED;font-style: italic">欢迎"<%=request.getSession().getAttribute("name")%>"使用本系统</h1>
    </div>

    <script type="text/javascript">
        var clock = new Clock();
        clock.display(document.getElementById("clock"));
    </script>

	</body>

</html>