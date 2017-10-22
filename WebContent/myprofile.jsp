<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

	<c:if test="${ sessionScope.user == null }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>


	<h3>My Profile</h3>
	<div class="navi">

		<form class="navi" action="myprofile.jsp">
			<input type="submit" value="View My profile">
		</form>
		<form class="navi" action="orders.jsp">
			<input type="submit" value="View My orders">
		</form>
		<form class="navi" action="profileaddress.jsp">
			<input type="submit" value="Addresses">
		</form>
		<form class="navi" action="main.jsp">
			<input type="submit" value="Products">
		</form>
		</div>
		
		
		<img id="avatar2" src="avatar">
		<form action="avatar" method="post" enctype="multipart/form-data">
			<input type="file" name="avatar"><br>
			 <input type="submit" value="Upload Avatar"><br>
		</form>
	
	<div class="right">
		<form action="register" method="post">
				<input type="text" name="first_name" placeholder="First name" />
				<input type="text" name="last_name" placeholder="Last name" /> 
				<input type="password" name="pass" placeholder="Password" /> 
				
				<input type="password" name="passconfirm" placeholder="Retype password" />
				<input type="text" name="email" placeholder="E-mail" />
				<input type="text" name="phone" placeholder="Phone" />
				<input type="text" name="address" placeholder="Address" />
				<input type="submit name="signup_submit" value="Sign me up" />
			</form>
	</div>
</body>
</html>