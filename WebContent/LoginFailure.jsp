<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Failure</title>
</head>
<body>
<h1>Invalid CustomerID or Password. Please try Again!!</h1>

<form action="LoginController">

<table>

<tr>
<td>CustomerID</td>
<td><input type="text" name="cusid"/></td>
</tr>

<tr>
<td>Password</td>
<td><input type="password" name="pwd"/></td>
</tr>

<tr>
<td><input type="submit" value="LOGIN"/></td>
<td><a href="ForgotPassword.jsp">Forgot Password?</a></td>
</tr>

</table>

</form>
</body>
</html>