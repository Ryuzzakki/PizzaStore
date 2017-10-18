<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Register</title>
</head>
<body>
	<div id="login-box">
		<div class="left">
			<h2>Pizza Store</h2>
			<h1>Sign up</h1>
			<form action="register" method="post">
				<input type="text" name="first_name" placeholder="First name" /> <input
					type="text" name="last_name" placeholder="Last name" /> <input
					type="password" name="pass" placeholder="Password" /> <input
					type="password" name="passconfirm" placeholder="Retype password" />
				<input type="text" name="email" placeholder="E-mail" /> <input
					type="text" name="phone" placeholder="Phone" /> <input type="text"
					name="address" placeholder="Address" /> <input type="submit"
					name="signup_submit" value="Sign me up" />
			</form>
		</div>
		<div class="right">
			<span class="loginwith">Sign in with<br />social network
			</span>
			<button class="social-signin facebook">Log in with facebook</button>

			<h2>Already have an account?</h2>
			<form action="login.jsp">
				<input type="submit" value="Login here!">
			</form>

		</div>
		<div class="or">OR</div>
	</div>


</body>
</html>