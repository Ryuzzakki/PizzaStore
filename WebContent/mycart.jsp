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

	<c:if test="${ sessionScope.user == null }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<jsp:include page="header.jsp"></jsp:include>


	<table border="1">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		<c:forEach var="product" items="${requestScope.productsInCart}">
			<tr>
				<td><c:out value="${ product.key.name }"></c:out></td>
				<td><c:out value="${ product.key.price }"></c:out></td>
				<td><c:out value="${ product.value }"></c:out></td>

			</tr>
		</c:forEach>

		<tr>
			<c:out value="${ sessionScope.order.total_price }"></c:out>
		</tr>
	</table>

</body>
</html>