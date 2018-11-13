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
    
    <title>frame_left</title>
    
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
	
	<script type="text/javascript">
		function startTime(){   
			var today=new Date();//定义日期对象   
			var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年    
			var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年    
			var dd = today.getDate();//通过日期对象的getDate()方法返回年     
			var hh=today.getHours();//通过日期对象的getHours方法返回小时   
			var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟   
			var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒   
			// 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09   
			MM=checkTime(MM);
			dd=checkTime(dd);
			mm=checkTime(mm);   
			ss=checkTime(ss);    
			var day; //用于保存星期（getDay()方法得到星期编号）
			if(today.getDay()==0)   day   =   "星期日 " ;
			if(today.getDay()==1)   day   =   "星期一 " ;
			if(today.getDay()==2)   day   =   "星期二 " ;
			if(today.getDay()==3)   day   =   "星期三 " ;
			if(today.getDay()==4)   day   =   "星期四 " ;
			if(today.getDay()==5)   day   =   "星期五 " ;
			if(today.getDay()==6)   day   =   "星期六 " ;
			document.getElementById("bj_time").innerHTML=yyyy+"年"+MM +"月"+ dd +"日" + hh+":"+mm+":"+ss+"&nbsp;&nbsp;" + day;   
			setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法 
		}   
			
		function checkTime(i){   
			if (i<10){
				i="0" + i;
			}   
			  return i;
		}  
	</script>
	
  </head>
  
  <body bgcolor="#FFFFF0" onload="startTime()" >
	<h4>姓名	:<span id="fram_left_sname" >&nbsp;${sessionScope.sname }</span></h4>
	<h4>学号	:<span id="fram_left_sid" >&nbsp;${sessionScope.sid }</span></h4>
	<a href="/ZK_Manage/WorkSignServlet" target="frame_body" >
		<div class="div_button" align="center" >首页</div>
	</a>
    <a href="frame/frame_sign.jsp" target="frame_body" >
    	<div class="div_button" align="center" >签到</div>
    </a>
    <a href="frame/frame_relay.jsp" target="frame_body" >
    	<div class="div_button" align="center" >替班</div>
    </a>
    <a href="frame/frame_leave.jsp" target="frame_body" >
    	<div class="div_button" align="center" >请假</div>
    </a>
    <a href="frame/frame_check.jsp" target="frame_body" >
    	<div class="div_button" align="center" >查询</div>
    </a>
    <a href="frame/frame_table.jsp" target="frame_body" >
    	<div class="div_button" align="center" >值班表</div>
    </a>
    <a href="/ZK_Manage/CandyServlet" target="frame_body" >
    	<div class="div_button" align="center" >糖果池</div>
    </a>
    <a href="frame/frame_revise.jsp" target="frame_body" >
    	<div class="div_button" align="center" >修改密码</div>
    </a>
    <a href="/ZK_Manage/ExitServlet" target="_top" >
    	<div class="div_button" align="center" >退出登录</div>
    </a>
    <div align="right" style="position: fixed;bottom: 0;width: 160px;height: 50px;"><span id="bj_time" ></span></div>
  </body>
</html>
