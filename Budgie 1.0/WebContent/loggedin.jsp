<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UID</title>
</head>
<body>

	<form action="inItem" method="post">
		<%
			String UID = (request.getAttribute("test").toString());
			System.out.println(UID);
		%>
		
		
		Enter itemName : <input type="text" name="itemName"><br>
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		Enter itemPrice : <input type="number" name="itemPrice"><br>
		<input type="submit" value="Add Item">
		
	</form>
	
	<form action="outItem" method="post">
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		<input type="submit" value="View all items">
	</form>
</body>
</html>