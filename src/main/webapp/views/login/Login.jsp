<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>图书馆书目检索系统</h1>
			
			<form class="form" action="login/userLogin" method="post">
				<input type="text" name="username" placeholder="username">
				<input type="password" name="password" placeholder="password">
				<button type="submit" id="login-button">Login</button>
			</form>
		</div>
		
		<div style="margin:60px 650px; font:normal 7px/12px 'MicroSoft YaHei';color:#000000">
				<h1 >  
			
				<a href="login/register" style="color:#000000;">点击注册 </a> </h1>
		</div>
	</div>


</div>
</body>
</html>