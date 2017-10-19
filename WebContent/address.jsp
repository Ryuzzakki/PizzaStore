<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Chooser</title>
</head>
<body>
	<c:if test="${ sessionScope.user == null }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<jsp:include page="header.jsp"></jsp:include>

	<h3>Get from restaurant or delivery?</h3>

	<form action="address" method="post">
		<input type="radio" id="radio_1" name="address" value="restaurant" />
		Restaurant <input type="radio" id="radio_2" name="address"
			value="home" /> Home
		<button type="submit" value="Submit">Submit</button>
	</form>

</body>
</html>