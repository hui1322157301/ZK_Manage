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
    
    <title>backstage_set</title>
    
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
			width: 40%;
			height: 70%;
			margin-left: 5%;
			margin-top: 5%;
        }
        #check_div2{
            position: absolute;
			display: inline-block;
			width: 50%;
			height: 70%;
			margin-left: 45%;
			margin-top: 5%;
        }
	</style>

  </head>
  <body background="/ZK_Manage/img/frame_right.jpg" class="frame_right_bj" >
  	<div align="center" id="check_div1" >
  		<h3>签到IP设置</h3>
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>IP地址</th>
  			</tr>
  			<c:forEach items="${requestScope.iplist }" var="ip" >
	  			<tr>
	  				<form action="<%=request.getContextPath() %>/BackstageDeleteIpServlet" method="get" name="ip_form" >
						<td>${ip.ip }</td>
	  				</form>
	  			</tr>
  			</c:forEach>
  		</table>
  		<br/>IP :&nbsp;<input type="text" name="set_ip" id="set_ip" /><br/>
  		<br/><input type="button" onclick="deleteIp()" value="删除IP" />
  		<input type="button" onclick="addIp()" value="添加IP" /><br/>
  		<br/><h3>学生注册码设置</h3>
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>提示</th>
  				<th>注册码</th>
  			</tr>
  			<tr>
				<td>该注册码用于新生注册，为了系统安全，请使用完后及时更改！</td>
				<td><input type="text" id="registecode" name="registecode" value="${requestScope.registecode }" /></td>
  			</tr>
  		</table>
  		<br/><input type="button" onclick="setRegisteCode()" value="确认更改" />
  	</div>
  	<div align="center" id="check_div2" >
  		<h3>学生信息设置</h3>
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>提示</th>
  				<th>学生姓名</th>
  			</tr>
  			<tr>
				<td>此项将删除该学生在数据库中的所有记录（一般用于离职人员）</td>
				<td><input type="text" id="delete_sname" name="delete_sname" /></td>
  			</tr>
  		</table>
  		<br/><input type="button" onclick="deleteStudent()" value="确认删除" />
  		<br/><br/><br/><h3>值班时间及工时设置</h3>
  		<table align="center" border="1px" style="text-align: center; border-collapse: collapse;">
  			<tr>
  				<th>值班班次</th>
  				<th>开始时间</th>
  				<th>结束时间</th>
  				<th>班次工时</th>
  			</tr>
  			<c:forEach items="${requestScope.wtlist }" var="wt" >
	  			<tr>
					<td><input type="text" id="set_num" name="set_num" value="${wt.num }" /></td>
					<td><input type="text" id="set_begintime" name="set_begintime" value="${wt.begintime }" /></td>
					<td><input type="text" id="set_endtime" name="set_endtime" value="${wt.endtime }" /></td>
					<td><input type="text" id="set_worknum" name="set_worknum" value="${wt.worknum }" /></td>
	  			</tr>
	  		</c:forEach>
  		</table>
  		<br/><input type="button" onclick="setWorkTime()" value="确认更改" />
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
		function deleteIp(){
			var set_ip = document.getElementById("set_ip").value;
    		if(set_ip == ""){
    			alert("请输入IP地址！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/BackstageDeleteIpServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("set_ip="+set_ip);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(text == 1){
    					alert("删除成功！");
    				}
    				if(text == -1){
    					alert("你输入的IP地址无效！");
    				}
    			}
    		};
		};
		function addIp(){
			var set_ip = document.getElementById("set_ip").value;
    		if(set_ip == ""){
    			alert("请输入IP地址！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/BackstageAddIpServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("set_ip="+set_ip);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(text == 1){
    					alert("添加成功！");
    				}
    				if(text == -1){
    					alert("IP重复！");
    				}
    			}
    		};
		}
		function setRegisteCode(){
    		var registecode = document.getElementById("registecode").value;
    		if(registecode == ""){
    			alert("请输入注册码！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/BackstageRegisteCodeServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("registecode="+registecode);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(text == 1){
    					alert("更改成功！");
    				}
    			}
    		};
    	};
		function deleteStudent(){
    		var delete_sname = document.getElementById("delete_sname").value;
    		if(delete_sname == ""){
    			alert("请输入要删除的学生姓名！");
    			return;
    		}
    		if(delete_sname == "管理员"){
    			alert("无法删除管理员！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/BackstageDeleteStudentServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("delete_sname="+delete_sname);
    		xmlHttp.onreadystatechange = function(){
    			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
    				var text = xmlHttp.responseText;
    				if(text == 1){
    					alert("删除成功！");
    				}
    				if(text == -1){
    					alert("你要删除的学生不存在！");
    				}
    			}
    		};
    	};
    	function setWorkTime(){
    		var set_num = document.getElementById("set_num").value;
    		var set_begintime = document.getElementById("set_begintime").value;
    		var set_endtime = document.getElementById("set_endtime").value;
    		var set_worknum = document.getElementById("set_worknum").value;
    		if(set_num == "" || set_begintime == "" || set_endtime == "" || set_worknum == ""){
    			alert("请输入完整的值班时间信息！");
    			return;
    		}
    		var xmlHttp = createXMLHttpRequest();
    		xmlHttp.open("post","/ZK_Manage/BackstageSetWorkTimeServlet",true);
    		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    		xmlHttp.send("set_num="+set_num+"&set_begintime="+set_begintime+"&set_endtime="+set_endtime+"&set_worknum="+set_worknum);
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
  </body>
</html>
