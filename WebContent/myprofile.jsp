<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

	<h3>My Profile</h3>
	<div class="left">

		<form class="navi" action="about.jsp">
			<input type="submit" value="About Us">
		</form>
		<form class="navi" action="contact.jsp">
			<input type="submit" value="Contacts">
		</form>
		<form class="navi" action="main.jsp">
			<input type="submit" value="Products">
		</form><br>

	<img id="avatar" src="avatar">
	<form action="avatar" method="post" enctype="multipart/form-data">
		<input type="file" name="avatar"><br>
		 <input type="submit" value="Upload Avatar"><br>
	</form>
	</div>
	<div class="right">
			<form action="login" method="post">
				<input type="text" name="email" placeholder="E-mail" /> <input
					type="password" name="pass" placeholder="Password" /> <input
					type="submit" name="login_submit" value="Login" />
			</form>
		</div>
</body>
</html>