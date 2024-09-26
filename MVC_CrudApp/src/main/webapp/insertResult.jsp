<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration MSG</title>
</head>
<body>
	<c:choose>
		<c:when test="${status eq 'success'}">
			<h1 style='color:green; text-align:center;'>REGISTRATION SUCESSFULL</h1>
		</c:when>

		<c:when test="${status eq failure }">
			<h1 style='color:red; text-align:center;'>REGISTRATION FALIED</h1>
		</c:when>
	</c:choose>
</body>
</html>