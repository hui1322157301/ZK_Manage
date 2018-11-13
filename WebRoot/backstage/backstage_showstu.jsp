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
    
    <title>backstage_showstu</title>
    
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
  		<h3>在职学生查询</h3><br/>
  		<form action="<%=request.getContextPath() %>/BackstageShowStudentServlet" method="post" id="student_form" >  		
  		学生姓名 :&nbsp;<input type="text" id="student_sname" name="student_sname" />
  		<input type="button" value="查询" onclick="checkstu()" />
  		</form>
  	</div>
  	<div align="center" id="check_div2" >
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>学生姓名</th>
  				<th>学生学号</th>
  				<th>学生密码</th>
  			</tr>
  			<c:if test="${fn:length(stulist) == 1 }">
	  			<c:forEach items="${requestScope.stulist }" var="stu" >
		  			<tr>
		  				<td>${stu.sname }</td>
		  				<td>${stu.sid }</td>
		  				<td>${stu.pwd }</td>
		  			</tr>
	  			</c:forEach>
	  		</c:if>
	  		<c:if test="${fn:length(stulist) != 1 }">
	  			<c:forEach items="${requestScope.stulist }" var="stu" begin="1" >
		  			<tr>
		  				<td>${stu.sname }</td>
		  				<td>${stu.sid }</td>
		  				<td>${stu.pwd }</td>
		  			</tr>
	  			</c:forEach>
	  		</c:if>
  		</table>
  	</div>
  	<script type="text/javascript">
    	function checkstu(){
    		var student_form = document.getElementById("student_form");
    		var student_sname = document.getElementById("student_sname").value;
    		student_form.submit();
    	};
   	</script>
  </body>
</html>
