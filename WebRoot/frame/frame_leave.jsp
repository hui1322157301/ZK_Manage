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
    
    <title>frame_leave</title>
    
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
		#leave_div{
            display: inline-block;
			width: 50%;
			height: 50%;
			margin-left: 20%;
			margin-top: 10%;
        }
        #leave_button{
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
    <div align="center" id="leave_div"  >
    	<h3 align="center" >请假</h3><br/>
    	<table align="center" border="1px" style="text-align: center; border-collapse: collapse;" >
    		<input type="hidden" value="${sessionScope.sname }" id="sname" />
    		<tr>
    			<th>值班地点</th>
    			<th>值班时间</th>
    			<th>具体时间</th>
    			<th>值班人员</th>
    			<th>请假理由</th>
    		</tr>
    		<tr>
    			<td>
    				<select name="leave_location" id="leave_location" >
			    		<option value="a" >A教</option>
			    		<option value="c" >C教</option>
    				</select>
    			</td>
    			<td>
					<input type="date" id="leave_date" />
				</td>
    			<td>
    				<select name="leave_time" id="leave_time" >
			    		<option value="one" >上午第一节</option>
			    		<option value="two" >上午第二节</option>
			    		<option value="three" >下午第一节</option>
			    		<option value="four" >下午第二节</option>
			    		<option value="five" >晚上</option>
			    	</select>
    			</td>
    			<td><input type="text" style="text-align:center;" id="leave_sname" /></td>
    			<td><input type="text" style="text-align:center;" id="leave_message" maxlength="55"/></td>
    		</tr>
    	</table>
    	<div align="center" id="leave_button" onclick="leave()" >申请请假</div>
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
		};
    	function leave(){
    		var sname = document.getElementById("sname").value;
    		var leave_location = document.getElementById("leave_location").value;
    		var leave_date = document.getElementById("leave_date").value;
    		var leave_time = document.getElementById("leave_time").value;
    		var leave_sname = document.getElementById("leave_sname").value;
    		var leave_message = document.getElementById("leave_message").value;
    		if(leave_location == "" || leave_date == "" || leave_time == "" || leave_sname == "" || leave_message == ""){
    			alert("请填写完整的请假信息！");
    			return;
    		}
    		if(sname != leave_sname){
    			alert("请假人与当前登录者不同！");
    			return;
    		}
    		if(leave_message.length > 30){
    			alert("请假理由超过30个字了！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/LeaveServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("leave_location="+leave_location+"&leave_date="+leave_date+"&leave_time="+leave_time+"&leave_sname="+leave_sname+"&leave_message="+leave_message);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(text == 1){
    					alert("请假成功！");
    				}
    			}
    		};
    	};
    </script>
  </body>
</html>
