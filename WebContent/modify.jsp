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
	
	
	
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Price</th>
		</tr>
		<c:set var="currentProduct" value="${param.productId}" scope="session" />
		
				<c:forEach items="${applicationScope.ingredients}" var="ingredient">
			<tr>
				<td><c:out value="${ ingredient.name }"></c:out></td>
				<td><c:out value="${ ingredient.price } "></c:out></td>
				
				<td><form action="modify" method="post">
						<input type="hidden" name="ingredientId" value="${ ingredient.id }" />
						<input type="hidden" name="productId" value="${ currentProduct }" />
						<input type="submit" name="cart_submit" value="Add to Pizza" />
					</form></td>
					
			</tr>
		</c:forEach>
	</table>

</body>
</html>