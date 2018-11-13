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
    
    <title>backstage_index</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <frameset rows="8%,*%" frameborder="yes" >
    	<frame name="backstage_top" src="backstage/backstage_top.jsp" noresize="noresize" >
    	<frameset cols="12%,*" frameborder="yes">
    		<frame name="backstage_left" src="backstage/backstage_left.jsp" noresize="noresize" >
    		<frame name="backstage_body" src="/ZK_Manage/BackstageWorkSignServlet" noresize="noresize" >
    	</frameset>
  </frameset>
  
  <body>
    
  </body>
</html>
