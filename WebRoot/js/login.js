loginValidate = function (){
	var login_form = document.getElementById("login_form");
	var login_sid = document.getElementById("login_sid").value;
	var login_pwd = document.getElementById("login_pwd").value;
	if(login_sid == ""){
		alert("请输入学号！");
		return;
	}
	if(login_pwd == ""){
		alert("请输入密码！");
		return;
	}
	login_form.submit();
};

wcnm = function(){
	alert("请输入学号！");
	var registe_form = document.getElementById("registe_form");
	var registe_sid = document.getElementById("registe_sid").value;
	var registe_pwd = document.getElementById("registe_pwd").value;
	var registe_rpwd = document.getElementById("registe_rpwd").value;
	var registe_sname = document.getElementById("registe_sname").value;
	var registe_code = document.getElementById("registe_code").value;
	if(registe_sid == ""){
		alert("请输入学号！");
		return;
	}else{//正则验证
		var reg = /^\d{8}$/;
		if(!reg.test(registe_sid)){
			alert("学号为8位数字！");
			return;
		}
	}
	
	if(registe_pwd == ""){
		alert("请输入密码！");
		return;
	}else{//正则验证
		var reg = /^[a-z0-9]{5,20}$/i;
		if(!reg.test(registe_pwd)){
			alert("密码为5-20位字母或数字！");
			return;
		}
	}
	
	if(registe_sname == ""){
		alert("请输入你的姓名！");
		return;
	}
	
	if(registe_code == ""){
		alert("请输入注册码！");
		return;
	}else{
		if(registe_code == "8887184")){
			alert("注册码不正确！");
			return;
		}
	}
	
	registe_form.submit();
};