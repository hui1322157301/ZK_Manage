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
    
    <title>backstage_right</title>
    
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
			-moz-background-size: 100% 100%;
			-webkit-background-size: 100% 100%;
		}
		#right_div1{
			position: absolute;
			display: inline-block;
			width: 30%;
			height: 10%;
			margin-left: 20%;
			margin-top: 10%;
		}
		#right_div2{
			position: absolute;
			display: inline-block;
			width: 30%;
			height: 10%;
			margin-left: 50%;
			margin-top: 10%;
		}
	</style>

  </head>
  
  <body background="/ZK_Manage/img/frame_right.jpg" class="frame_right_bj" >
    <div id="right_div1" >
    	<h3 align="center" >A教值班情况</h3>
    	<table align="center" border="1px" style="text-align: center; border-collapse: collapse;" >
    		<tr>
    			<th width="100px">值班时间</th>
    			<th width="100px">值班人员</th>
    			<th width="100px">签到时间</th>
    			<th width="100px">签到状态</th>
    		</tr>
    		<c:forEach items="${requestScope.alist }" var="ws" >
	    		<tr>
	    			<td>${ws.worktime }</td>
	    			<td>${ws.sname }</td>
	    			<td>${ws.workdate }</td>
	    			<td>${ws.workstate }</td>
	    		</tr>
    		</c:forEach>
    	</table>
    </div>
    <div id="right_div2" >
    	<h3 align="center" >C教值班情况</h3>
    	<table align="center" border="1px" style="text-align: center; border-collapse: collapse;" >
    		<tr>
    			<th width="100px">值班时间</th>
    			<th width="100px">值班人员</th>
    			<th width="100px">签到时间</th>
    			<th width="100px">签到状态</th>
    		</tr>
    		<c:forEach items="${requestScope.clist }" var="ws" >
	    		<tr>
	    			<td>${ws.worktime }</td>
	    			<td>${ws.sname }</td>
	    			<td>${ws.workdate }</td>
	    			<td>${ws.workstate }</td>
	    		</tr>
    		</c:forEach>
    	</table>
    </div>
  </body>
</html>
