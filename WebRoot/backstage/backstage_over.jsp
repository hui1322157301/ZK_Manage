<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>backstage_over</title>
    
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
		#check_div1{
            position: absolute;
			display: inline-block;
			width: 50%;
			height: 50%;
			margin-left: 5%;
			margin-top: 10%;
        }
        #check_div2{
            position: absolute;
			display: inline-block;
			width: 40%;
			height: 50%;
			margin-left: 50%;
			margin-top: 5%;
        }
	</style>

  </head>
  <body background="/ZK_Manage/img/frame_right.jpg" class="frame_right_bj" >
  	<div align="center" id="check_div1" >
  		<h3>加班情况查询</h3><br/>
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>起始时间</th>
  				<th>结束时间</th>
  				<th>学生姓名</th>
  			</tr>
  			<tr>
  				<form action="<%=request.getContextPath() %>/BackstageOverServlet" method="post" id="over_form" >
  					<td><input type="date" id="begin_time" name="begin_time" /></td>
	  				<td><input type="date" id="end_time" name="end_time" /></td>
	  				<td><input type="text" id="over_sname" name="over_sname" /></td>
  				</form>
  			</tr>
  		</table>
  		<br/><input type="button" value="查询" onclick="check6()" />
  		<h3>学生加班设置</h3>
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>加班人员</th>
  				<th>值班时间</th>
  				<th>加班理由</th>
  				<th>加班工时</th>
  			</tr>
  			<tr>
				<td><input type="text" id="over_sname1" name="over_sname1" /></td>
				<td><input type="date" id="over_date" name="over_date" /></td>
				<td><input type="text" id="over_message" name="over_message" /></td>
				<td><input type="text" id="over_num" name="over_num" /></td>
  			</tr>
  		</table>
  		<br/><input type="button" onclick="setOver()" value="确认设置" />
  	</div>
  	<div align="center" id="check_div2" >
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>加班人员</th>
  				<th>值班时间</th>
  				<th>加班理由</th>
  				<th>加班工时</th>
  			</tr>
  			<c:forEach items="${requestScope.omlist }" var="om" >
	  			<tr>
	  				<td>${om.sname }</td>
	  				<td>${om.workdate }</td>
	  				<td>${om.message }</td>
	  				<td>${om.worknum}</td>
	  			</tr>
  			</c:forEach>
  		</table>
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

    	function setOver(){
    		var over_sname1 = document.getElementById("over_sname1").value;
    		var over_date = document.getElementById("over_date").value;
    		var over_message = document.getElementById("over_message").value;
    		var over_num = document.getElementById("over_num").value;
    		if(over_sname1 == "" || over_date == "" || over_message == "" || over_num == ""){
    			alert("请填写加班完整信息！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/BackstageSetOverServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("over_sname1="+over_sname1+"&over_date="+over_date+"&over_message="+over_message+"&over_num="+over_num);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(text == 1){
    					alert("设置成功！");
    				}
    			}
    		};
    	};
   	</script>
  	<script type="text/javascript">
    	function check6(){
    		var begin_time = document.getElementById("begin_time").value;
    		var end_time = document.getElementById("end_time").value;
    		var over_form = document.getElementById("over_form");
    		if(begin_time == "" || end_time == ""){
    			alert("请输入完整的时间");
    			return;
    		}
    		over_form.submit();
    	};
   	</script>
  </body>
</html>
