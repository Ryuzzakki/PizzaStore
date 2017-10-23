<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Modify</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>


	<div>
		<table border="1" style="float: left;">
			<tr>
				<th>Name</th>
				<th>Price</th>
			</tr>
			<c:set var="currentProduct" value="${param.productId}" scope="page" />
			<c:forEach items="${applicationScope.ingredients}" var="ingredient">
				<tr>
					<td><c:out value="${ ingredient.name }"></c:out></td>
					<td><c:out value="${ ingredient.price } "></c:out></td>


					<td>
						<form action="modify" method="post">
							<input type="hidden" name="ingredientId" value="${ ingredient.id }" /> 
							<input type="hidden" name="productId" value="${ currentProduct }" /> 
							<input type="submit" name="cart_submit" value="Add to Pizza" />
						</form>
						<form action="removeing" method="post">
							<input type="hidden" name="ingredientId" value="${ ingredient.id }" /> 
							<input type="hidden" name="productId" value="${ currentProduct }" /> 
							<input type="submit" name="remove_ingredient" value="Remove" />
						</form>
						</td>

				</tr>
			</c:forEach>
		</table>
	</div>

	
	
	<div style="float: left;" >
			<h1>Choose type of Pizza:</h1><br>
		<form action="chooser" method="post">
			Select size of Pizza: <select name="size">
				<option value="small">Small</option>
				<option value="medium">Medium</option>
				<option value="large">Large</option>
			</select> <br> 
			<br> Select dought of Pizza: <select name="dough">
				<option value="thin">Thin</option>
				<option value="normal">Normal</option>
			</select> 
			<br> <input type="hidden" name="productId" value="${ currentProduct }" /> <br> 
				<input type="submit" name="pizzaSpecials" value="Choose Pizza">
		</form>
	</div>
	
	<div>
		<table border="1" style="float: left; ">
			<h1>Current Pizza:</h1>
			<c:forEach items="${sessionScope.order.products}" var="product">
			<tr>
				<th>Name</th>
				<th>Dough</th>
				<th>Size</th>
				<th>Ingredients</th>
			</tr>
				<tr>
					<td><c:out value="${ product.key.name }"></c:out></td>
					<td><c:out value="${ product.key.dough }"></c:out></td>
					<td><c:out value="${ product.key.size }"></c:out></td>
					<td><c:forEach var="ingredient" items="${product.key.ingredients}">
						<c:out value="${ingredient.name }"></c:out><br>

				</c:forEach>
					</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>



</body>
</html>