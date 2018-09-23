<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
<%out.println("Hello, "+session.getAttribute("name")+" You have Successfully Logged In"); %>
<table>
<tr>
<td><a href="Transfer.jsp">Third Party Transfer</a></td>
</tr>
<tr>
<td><a href="BalanceController">Check Balance</a></td>
</tr>
<tr>
<td><a href="Loan.jsp">Apply Loan</a></td>
</tr>
<tr>
<td><a href="ChangePassword.jsp">Change Password</a></td>
</tr>
<tr>
<td><a href="StatementController">Mini Statement</a></td>
</tr>
<tr>
<td><a href="Logout.jsp">Logout</a></td>
</tr>
</table>
</body>
</html>