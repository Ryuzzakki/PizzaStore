<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<title>Login Form</title>
</head>
<body>
	<h1>Pizza Store</h1>
	<h3>Login here!</h3>
	<form action="login" method="post">
		Email: <br> <input type="text" name="email"> <br>
		Password : <br> <input type="password" name="pass"> <br>
		<p>
			<input type="submit" value="Login!"> <br>
		<p>
	</form>

	<a href="http://localhost:8080/Dominos/register.jsp">Register here!</a>
</body>
</html>