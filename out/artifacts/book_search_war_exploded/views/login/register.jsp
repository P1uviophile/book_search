<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<h1 class="w3ls">Authentication Signup Form</h1>
	<div class="content-agileits">
		<form action="<c:url value="/login/userRegister"/>" method="post">
			<div class="form-group  w3 w3l">
				<label for="username" class="control-label">账户名</label>
				<input type="text" class="form-control" name="username" id="username" >
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group agileits w3">
				<label for="password" class="control-label">密码</label>
				<input type="text" class="form-control" name="password" id="password">
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group agileits w3">
				<label for="password2" class="control-label">确认密码</label>
				<input type="text" class="form-control" name="password2" id="password2" >
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-lg">Submit</button>
			</div>
		</form>
    </div>
<p class="copyright-w3ls">©    欢迎注册 </p>
</body>
</html>