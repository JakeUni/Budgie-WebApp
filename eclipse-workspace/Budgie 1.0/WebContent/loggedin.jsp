<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UID</title>
</head>
<body>
<%
	
		String UID = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user"))
			{ 
				UID = cookie.getValue();
			}
		}
		}else{
			response.sendRedirect("index.jsp");
		}
%>

	<form action="inItem" method="post">
		Enter itemName : <input type="text" name="itemName"><br>
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		Enter itemPrice : <input type="number" name="itemPrice"><br>
		<input type="submit" value="Add Item">
		<input type="checkbox" name="reacurring?" value="y"> Repeat this order weekly? <BR>
	</form>
	
	<form action="outItem" method="post">
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		<input type="submit" value="View all items">
	</form>
</body>
</html>