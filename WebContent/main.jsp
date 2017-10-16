<%@page import="java.util.ArrayList"%>
<%@page import="model.db.ProductDao"%>
<%@page import="model.User"%>
<%@page import="model.Product"%>
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
		ArrayList<Product> products = ProductDao.getInstance().getAllProducts();
	%>
	<h1>
		Hello
		<%=name%></h1>

	<table>
		<%for (Product p : products) {%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getPrice()%> leva</td>
			<td>
			<form action="order" method="post">
			<input type="submit" value="Add to Cart!"></form>
			</td>
		</tr>

		<%}%>

	</table>
	
	<form action="logout" method="post">
		<p><input type="submit" value="Logout"><br><p>
	</form>
	<form action="cart" method="post">
		<p><input type="submit" value="MyCart"><br><p>
	</form>

</body>
</html>