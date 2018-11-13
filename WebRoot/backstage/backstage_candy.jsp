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
    
    <title>backstage_candy</title>
    
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
		#back_div{
            display: inline-block;
			width: 50%;
			height: 50%;
			margin-left: 20%;
			margin-top: 10%;
        }
        .back_button{
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
	<script type="text/javascript">
    	function candy1(){
    		var form_candy = document.getElementById("form_candy");
    		var candy_num = document.getElementById("candy_num").value;
    		if(candy_num == ""){
    			alert("请输入糖果数！");
    			return;
    		}
    		form_candy.submit();
    	}
    </script>

  </head>
  
  <body background="/ZK_Manage/img/frame_right.jpg" class="frame_right_bj" >
    <div align="center" id="back_div">
	    <form action="<%=request.getContextPath() %>/BackstageSetCandyServlet" method="post" id="form_candy" >
		   	<h2 align="center" >糖果池</h2>
		   	<table align="center" border="0px" style="text-align: center; border-collapse: collapse;" >
		   		<tr>
		   			<th><span style="font-size: 50px; color: #ff3366;">${requestScope.candy }</span>个工时</th>
		   		</tr>
		   		<tr>
		   			<td><img alt="candy" src="img/candy.png" width="300px" height="200px" ></td>
		   		</tr>
		   		<tr>
		   			设置糖果数 ：<input type="text" name="candy_num" id="candy_num" />&nbsp;
		   			<input type="button" id="candy" value="提交" onclick="candy1()" />
		   		</tr>
		   	</table>
		</form>    
    </div>
	
  </body>
</html>
