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
    
    <title>frame_relay</title>
    
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
		#relay_div{
            display: inline-block;
			width: 50%;
			height: 50%;
			margin-left: 20%;
			margin-top: 10%;
        }
        #relay_button{
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
    <div align="center" id="relay_div"  >
    	<h3 align="center" >替班</h3><br/>
    	<table align="center" border="1px" style="text-align: center; border-collapse: collapse;" >
    		<input type="hidden" value="${sessionScope.sname }" id="sname" />
    		<input type="hidden" value="${sessionScope.sid }" id="sid" />
    		<tr>
    			<th>值班地点</th>
    			<th>值班时间</th>
    			<th>具体时间</th>
    			<th>原值班人员</th>
    			<th>替班人员</th>
    		</tr>
    		<tr>
    			<td>
    				<select name="relay_location" id="relay_location" >
			    		<option value="a" >A教</option>
			    		<option value="c" >C教</option>
    				</select>
    			</td>
    			<td>
					<input type="date" id="relay_date" />
				</td>
    			<td>
    				<select name="relay_time" id="relay_time" >
			    		<option value="one" >上午第一节</option>
			    		<option value="two" >上午第二节</option>
			    		<option value="three" >下午第一节</option>
			    		<option value="four" >下午第二节</option>
			    		<option value="five" >晚上</option>
			    	</select>
    			</td>
    			<td><input type="text" id="relay_prename" name="relay_prename" style="text-align:center;" /></td>
    			<td><input type="text" id="relay_sname" name="relay_sname" style="text-align:center;" /></td>
    		</tr>
    	</table>
    	<div align="center" id="relay_button" onclick="relay()" >替班签到</div>
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
    	function relay(){
    		var sname = document.getElementById("sname").value;
    		var sid = document.getElementById("sid").value;
    		var relay_date = document.getElementById("relay_date").value;
    		var relay_time = document.getElementById("relay_time").value;
    		var relay_prename = document.getElementById("relay_prename").value;
    		var relay_sname = document.getElementById("relay_sname").value;
    		var relay_location = document.getElementById("relay_location").value;
    		if(relay_date == "" || relay_time == "" || relay_location == "" || relay_prename == "" || relay_sname == ""){
    			alert("请填写完整的替班信息！");
    			return;
    		}
    		if(relay_sname != sname){
    			alert("替班者与当前登录用户不同！");
    			return;
    		}
    		if(relay_sname == relay_prename){
    			alert("替班者与原值班者相同！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/RelayServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("relay_location="+relay_location+"&relay_date="+relay_date+"&relay_time="+relay_time+"&relay_prename="+relay_prename+"&relay_sname="+relay_sname+"&sid="+sid);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(1 == text){
    					alert("替班签到成功！祝你值班愉快！");
    				}else if(2 == text){
    					alert("签太早了！再等等！");
    				}else if(-1 == text){
    					alert("你所选的当天时间有误！");
    				}else if(-2 == text){
    					alert("你所选的值班班次有误！");
    				}else if(-3 == text){
    					alert("本节班次你已经签过到了！");
    				}
    			}
    		};
    	};
   	</script>
  </body>
</html>
