<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a new budget</title>
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
		<h1>New budget</h1>
		
		<form action="budget" method="post">
		Enter Budget Amount please : <input type="text" name="amount"><br>
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		Enter Budget Length (in days) : <input type="number" name="length"><br>
		<input type="submit" value="Add Budget">
	</form>
</body>
</html>