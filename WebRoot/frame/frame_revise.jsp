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
    
    <title>frame_revise</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
		.frame_right_bj{
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: 100% 100%;
		}
		#revise_div{
            display: inline-block;
			width: 50%;
			height: 50%;
			margin-left: 20%;
			margin-top: 10%;
        }
        #revise_button{
            width: 150px;
			height: 30px;
			line-height: 30px;
			border: 2px solid #000;
			background-color: #ccccff;
			margin-top: 30px;
			cursor: pointer;
			border-radius:5px;
        }
        #revise_button{
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
    <div align="center" id="revise_div" >
    	<h3 align="center" >修改密码</h3><br/>
    	<form>
    		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;" >
	    		<tr>
	    			<td>学号：</td>
	    			<td><input type="text" id="sid" name="sid" /></td>
	    		</tr>
	    		<tr>
	    			<td>原密码：</td>
	    			<td><input type="text" id="prepwd" name="prepwd" /></td>
	    		</tr>
	    		<tr>
	    			<td>新密码：</td>
	    			<td><input type="text" id="newpwd" name="newpwd" /></td>
	    		</tr>
	    		<tr>
	    			<td>确认密码：</td>
	    			<td><input type="text" id="qrpwd" name="qrpwd" /></td>
	    		</tr>
	    	</table>
    	</form>
    	<div align="center" id="revise_button" onclick="revisepwd()" >确认修改</div>
    </div>
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
		}
    	function revisepwd(){
    		var sid = document.getElementById("sid").value;
    		var prepwd = document.getElementById("prepwd").value;
    		var newpwd = document.getElementById("newpwd").value;
    		var qrpwd = document.getElementById("qrpwd").value;
    		if(newpwd != qrpwd){
    			alert("两次输入的密码不一致");
    			return;
    		}
    		if(newpwd == ""){
				alert("请输入新密码！");
				return;
			}else{//正则验证
				var reg = /^[a-z0-9]{5,20}$/i;
				if(!reg.test(newpwd)){
					alert("密码为5-20位字母或数字！");
					return;
				}
			}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/RevisePwdServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("sid="+sid+"&prepwd="+prepwd+"&newpwd="+newpwd);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if("1" == text){
    					alert("密码修改成功！");
    					return;
    				}
    				if("0" == text){
    					alert("原密码错误！");
    					return;
    				}
    			}
    		}
    	};
    </script>
  </body>
</html>
