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
			<th></th>
		</tr>
		<c:forEach var="product" items="${sessionScope.productsInCart}">
			<tr>
				<td><c:out value="${ product.key.name }"></c:out></td>
				<td><c:out value="${ product.key.price }"></c:out></td>
				<td><c:out value="${ product.value }"></c:out></td>

				<td>
					<form>
						<input type="hidden" name="productId" value="${ product.key.id }" />
						<input type="hidden" name="productValue" value="${ product.value }" />
						<input type="number" name="productValue" min="1" max="10" />
						<input type="submit" name="cart_submit" value="Remove" />
					</form>
						<c:if test="${ product.key.pizza == true }">
							<form action="modify" method="post">
							<input type="hidden" name="productId" value="${ product.key.id }" />
							<input type="submit" name="cart_submit" value="Modify" />
					</form>
						</c:if>
				</td>
			</tr>
			
		</c:forEach>
	</table>
	
	<form action="sortOrders" method="post">
			<input type="submit" name="cart_submit" value="Make Order!" />
	</form>

	<h2>
		Total price is :
		<c:out value="${ sessionScope.totalPrice }"></c:out>
	</h2>


</body>
</html>