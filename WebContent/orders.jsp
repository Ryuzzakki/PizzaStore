<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${ sessionScope.user == null }">
			<c:redirect url="login.jsp"></c:redirect>
		</c:if>
		
		<jsp:include page="header.jsp"></jsp:include>
		
	<h2>Your previous orders:</h2>
	
	<a href="sortOrders?sort=desc"><button>Newest to oldest</button></a>
	<a href="sortOrders?sort=asc"><button>Oldest to newest</button></a>
	
		<c:forEach items="${ sessionScope.user.orders }" var="order">
			<h4>${ order.order_date }</h4>
			<table border="1">
			<tr>
				<th>Name</th>
				<th>Dough</th>
				<th>Size</th>
				<th>Quantity</th>
			</tr>
			<c:forEach items="${ order.products }" var="productEntry">
				<tr>
					<td>${productEntry.key.name }</td>
					<td>${productEntry.key.dough }</td>
					<td>${productEntry.key.size }</td>
					<td>${productEntry.value }</td>
				</tr>
			</c:forEach>
			</table>
			<hr>
		</c:forEach>
</body>
</html>