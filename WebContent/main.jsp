<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body>
	<%
		String name = "Stranger";
		User user = null;
		user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			name = user.getFirst_name() + " " + user.getLast_name();
		}
	%>
	<h1>
		Hello
		<%=name%></h1>

	<form action="logout" method="post">

		<p>
			<input type="submit" value="Logout"> <br>
		<p>
	</form>

</body>
</html>