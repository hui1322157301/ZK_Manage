<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>frame_sign</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
	<meta http-equiv="X-UA-Compatible" content="IE=10"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
		.frame_right_bj{
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: 100% 100%;
		}
		#sign_div{
            display: inline-block;
			width: 50%;
			height: 50%;
			margin-left: 20%;
			margin-top: 10%;
        }
        #sign_button{
            width: 150px;
			height: 30px;
			line-height: 30px;
			border: 2px solid #000;
			background-color: #ccccff;
			margin-top: 30px;
			cursor: pointer;
			border-radius:5px;
        }
	</style>

  </head>
  
  <body background="/ZK_Manage/img/frame_right.jpg" class="frame_right_bj" >
    <div align="center" id="sign_div" >
   	    <h3>签到页面</h3><br/>
    	<input type="date" id="sign_date" />
    	<select name="sign_time" id="sign_time" >
    		<option value="one" >上午第一节</option>
    		<option value="two" >上午第二节</option>
    		<option value="three" >下午第一节</option>
    		<option value="four" >下午第二节</option>
    		<option value="five" >晚上</option>
    	</select>
    	<select name="sign_location" id="sign_location" >
    		<option value="a" >A教</option>
    		<option value="c" >C教</option>
    	</select>
    	<div align="center" id="sign_button" onclick="sign()" >值班签到</div><br/>
    	<p>提示：你本月已迟到<span id="sign_late" ></span>次！</p>
    </div>
    <script type="text/javascript">
    	function createXMLHttpRequest() {
			try {
				return new XMLHttpRequest();//大多数浏览器
			} catch (e) {
				try {
					return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
				} catch (e) {
					try {
						return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
					} catch (e) {
						alert("哥们儿，您用的是什么浏览器啊？");
						throw e;
					}
				}
			}
		}
    	function sign(){
    		var sid = ${sessionScope.sid };
    		var sign_date = document.getElementById("sign_date").value;
    		var sign_time = document.getElementById("sign_time").value;
    		var sign_location = document.getElementById("sign_location").value;
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/SignServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("sid="+sid+"&sign_date="+sign_date+"&sign_time="+sign_time+"&sign_location="+sign_location);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(text == 1){
    					alert("签到成功！祝你值班愉快！");
    				}
    				if(text == 2){
    					alert("别急！时间没到呢！一会儿再签！");
    				}
    				if(text == -1){
    					alert("请选择正确的日期!");
    				}
    				if(text == -2){
    					alert("本节班次你已经签过到了！");
    				}
    				if(text == -3){
    					alert("你又迟到了！糖果池又加钱了！");
    				}
    				if(text == -4){
    					alert("你所选的班次错误！");
    				}
    				if(text == -5){
    					alert("你迟到了！下次早一点哟！");
    				}
    				if(text == 10){
    					alert("请在指定的电脑签到！");
    				}
    			}
    		};
    	};
    </script>
  	<script type="text/javascript">
	  	// 创建异步对象
		function createXMLHttpRequest() {
			try {
				return new XMLHttpRequest();//大多数浏览器
			} catch (e) {
				try {
					return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
				} catch (e) {
					try {
						return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
					} catch (e) {
						alert("哥们儿，您用的是什么浏览器啊？");
						throw e;
					}
				}
			}
		};
  		window.onload = function(){
  			var sign_late = document.getElementById("sign_late");
  			var xmlHttp = createXMLHttpRequest();
  			var sid = ${sessionScope.sid };
  			xmlHttp.open("POST", "/ZK_Manage/LateNum", true);
			xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xmlHttp.send("sid=" + sid);
			xmlHttp.onreadystatechange = function() {
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
					var text = xmlHttp.responseText;
					sign_late.innerHTML = text;
				}
			};
  		};
  	</script>
  </body>
</html>
