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
    
    <title>backstage_leave</title>
    
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
			width: 30%;
			height: 50%;
			margin-left: 10%;
			margin-top: 10%;
        }
        #check_div2{
            position: absolute;
			display: inline-block;
			width: 60%;
			height: 50%;
			margin-left: 40%;
			margin-top: 5%;
        }
	</style>

  </head>
  <body background="/ZK_Manage/img/frame_right.jpg" class="frame_right_bj" >
  	<div align="center" id="check_div1" >
  		<h3>请假情况查询</h3><br/>
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>起始时间</th>
  				<th>结束时间</th>
  				<th>学生姓名</th>
  			</tr>
  			<tr>
  				<form action="<%=request.getContextPath() %>/BackstageLeaveServlet" method="post" id="leave_form" >
  					<td><input type="date" id="begin_time" name="begin_time" /></td>
	  				<td><input type="date" id="end_time" name="end_time" /></td>
	  				<td><input type="text" id="leave_sname" name="leave_sname" /></td>
  				</form>
  			</tr>
  		</table>
  		<br/><input type="button" value="查询" onclick="check4()" />
  	</div>
  	<div align="center" id="check_div2" >
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>请假人员</th>
  				<th>值班时间</th>
  				<th>值班班次</th>
  				<th>值班地点</th>
  				<th>请假理由</th>
  			</tr>
  			<c:forEach items="${requestScope.lmlist }" var="rm" >
	  			<tr>
	  				<td>${rm.sname }</td>
	  				<td>${rm.workdate }</td>
	  				<td>${rm.worktime }</td>
	  				<td>${rm.location }</td>
	  				<td>${rm.message }</td>
	  			</tr>
  			</c:forEach>
  		</table>
  	</div>
  	<script type="text/javascript">
    	function check4(){
    		var begin_time = document.getElementById("begin_time").value;
    		var end_time = document.getElementById("end_time").value;
    		var leave_form = document.getElementById("leave_form");
    		if(begin_time == "" || end_time == ""){
    			alert("请输入完整的时间");
    			return;
    		}
    		leave_form.submit();
    	};
   	</script>
  </body>
</html>
