<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="styles2.css">

<title>Login Form</title>
</head>
<body>
	<div id="login-box">
		<div class="left">
			<h3>Pizza Store</h3>
			<h1>Login here!</h1>
			<form action="login" method="post">
				<input type="text" name="email" placeholder="E-mail" /> <input
					type="password" name="pass" placeholder="Password" /> <input
					type="submit" name="login_submit" value="Login" />
			</form>
		</div>
		<div class="right">
			<h2>Don't have an account?</h2>
			<form action="register.jsp">
				<input type="submit" value="Register here!">
			</form>
		</div>
		<div class="or">OR</div>
	</div>


</body>
</html>