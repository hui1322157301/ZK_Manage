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
    
    <title>backstage_left</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.div_button{
			width: 100%;
			height: 5%;
			line-height: 250%;
			border: 2px solid #000;
			background-color: #ccffff;
			margin-top: 5%;
			cursor: pointer;
			border-radius:5px
		}
		a{
			text-decoration: none; 
			color: #000;
		}
	</style>
  </head>
  
  <body bgcolor="#FFFFF0" onload="startTime()" >
	<h4>姓名	:<span id="fram_left_sname" >&nbsp;${sessionScope.god_sname }</span></h4>
	<h4>学号	:<span id="fram_left_sid" >&nbsp;${sessionScope.god_sid }</span></h4>
	<a href="<%=request.getContextPath() %>/BackstageWorkSignServlet" target="backstage_body" >
		<div class="div_button" align="center" >首页</div>
	</a>
	<a href="backstage/backstage_work.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >值班</div>
    </a>
    <a href="backstage/backstage_relay.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >替班</div>
    </a>
    <a href="backstage/backstage_leave.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >请假</div>
    </a>
    <a href="backstage/backstage_late.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >迟到</div>
    </a>
    <a href="backstage/backstage_over.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >加班</div>
    </a>
    <a href="backstage/backstage_table.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >值班表</div>
    </a>
    <a href="<%=request.getContextPath() %>/BackstageCandyServlet" target="backstage_body" >
    	<div class="div_button" align="center" >糖果池</div>
    </a>
    <a href="backstage/backstage_showstu.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >在职人员</div>
    </a>
    <a href="backstage/backstage_check.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >核算工资</div>
    </a>
    <a href="<%=request.getContextPath() %>/BackstageSetServlet" target="backstage_body" >
    	<div class="div_button" align="center" >系统设置</div>
    </a>
    <a href="frame/frame_revise.jsp" target="backstage_body" >
    	<div class="div_button" align="center" >修改密码</div>
    </a>
    <a href="/ZK_Manage/ExitServlet" target="_top" >
    	<div class="div_button" align="center" >退出登录</div>
    </a>
  </body>
</html>
