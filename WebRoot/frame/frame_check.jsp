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
    
    <title>frame_back</title>
    
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
		#check_div{
            display: inline-block;
			width: 50%;
			height: 50%;
			margin-left: 20%;
			margin-top: 10%;
        }
        #check_button{
            width: 150px;
			height: 30px;
			line-height: 30px;
			border: 2px solid #000;
			background-color: #ccccff;
			margin-top: 30px;
			cursor: pointer;
			border-radius:5px
        }
	</style>

  </head>
  <body background="/ZK_Manage/img/frame_right.jpg" class="frame_right_bj" >
  	<div align="center" id="check_div" >
  		<h3>查询工时</h3><br/>
  		<input type="hidden" id="sname" value="${sessionScope.sname }" />
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>起始时间</th>
  				<th>结束时间</th>
  				<th>值班工时</th>
  				<th>替班工时</th>
  				<th>迟到工时</th>
  				<th>加班工时</th>
  				<th>总计工时</th>
  			</tr>
  			<tr>
  				<td><input type="date" id="begin_time" /></td>
  				<td><input type="date" id="end_time" /></td>
  				<td><span id="work_time" ></span></td>
  				<td><span id="relay_time" ></span></td>
  				<td><span id="late_time" ></span></td>
  				<td><span id="over_time" ></span></td>
  				<td><span id="sum_time" ></span></td>
  			</tr>
  		</table>
  		<div align="center" id="check_button" onclick="check()" >查询</div><br/>
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
    	function check(){
    		var sname = document.getElementById("sname").value;
    		var begin_time = document.getElementById("begin_time").value;
    		var end_time = document.getElementById("end_time").value;
    		var work_time = document.getElementById("work_time");
    		var relay_time = document.getElementById("relay_time");
    		var late_time = document.getElementById("late_time");
    		var over_time = document.getElementById("over_time");
    		var sum_time = document.getElementById("sum_time");
    		if(begin_time == "" || end_time == ""){
    			alert("请填写完整的查询时间！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/CheckServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("sname="+sname+"&begin_time="+begin_time+"&end_time="+end_time);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText.split("#");
    				work_time.innerHTML = text[0];
    				relay_time.innerHTML = text[1];
    				late_time.innerHTML = text[2];
    				over_time.innerHTML = text[3];
    				sum_time.innerHTML = text[4];
    			}
    		};
    	};
   	</script>
  </body>
</html>
