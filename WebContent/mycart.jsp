<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<title>Cart</title>

</head>
<body>

	<table border="1">
		<tr>
			<th>Name</th>
			<th>Price</th>
		</tr>
		<c:forEach var="product" items="${requestScope.productsInCart}">
			<tr>
				<td><c:out value="${ product.key.name }"></c:out></td>
				<td><c:out value="${ product.key.price }"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>