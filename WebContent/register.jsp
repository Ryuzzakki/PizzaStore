<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<h1>Pizza Store</h1>
	<h3>Register here!</h3>
	<form action="register" method="post">
		First name:<br> <input type="text" name="first_name"><br>
		Last name:<br> <input type="text" name="last_name"> <br>
		Password : <br> <input type="password" name="pass"> <br>
		Confirm Password : <br> <input type="password" name="passconfirm"><br>
		Email: <br> <input type="text" name="email"> <br>
		Phone: <br> <input type="text" name="phone"> <br>
		Address: <br> <input type="text" name="address"> <br>

		<p>
			<input type="submit" value="Register!"> <br>
		<p>
	</form>



</body>
</html>