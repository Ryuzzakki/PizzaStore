<%@page import="java.util.ArrayList"%>
<%@page import="model.db.ProductDao"%>
<%@page import="model.User"%>
<%@page import="model.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
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
		</tr>
		<c:forEach items="${applicationScope.products}" var="product">
			<tr>

				<td><c:out value="${ product.name }"></c:out></td>
				<td><c:out value="${ product.price } "></c:out></td>
				<td><img src="productPic"></td>
				<td><form action="addtocart" method="post">
						<input type="hidden" name="productId" value="${ product.id }" /> <input
							type="submit" name="login_submit" value="Add to Cart" />
					</form></td>
			</tr>
		</c:forEach>
	</table>

	<form action="logout" method="post">
		<input type="submit" value="Logout"><br>
	</form>

	<form action="cart" method="post">
		<input type="submit" value="MyCart"><br>
	</form>
	<br>

	<form action="avatar" method="post" enctype="multipart/form-data">
		<input type="file" name="avatar"><br> <input
			type="submit" value="Upload Avatar"><br>
	</form>
	<br>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>