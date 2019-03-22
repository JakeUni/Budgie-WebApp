<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UID</title>
</head>
<body>
		<a href="main.jsp">back to main</a>

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
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement(); 

		String sql = "Select * from item where usersid ='" + UID + "'";
		ResultSet result = stmt.executeQuery(sql);
		%>
	<datalist id = "t">
		
		<%
		while(result.next()){
		%>
			<option value ="<%=result.getString("itemName")%>">
		<%
		}
		%>
		
		
		
		
		
	</datalist>
	
	<form action="inItem" method="post">
		Enter itemName please : <input type="text" name="itemName" list="t"><br>
	
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		Enter itemPrice : <input type="number" name="itemPrice"><br>
		<input type="submit" value="Add Item">
		<input type="checkbox" name="reacurring?" value="y"> Repeat this order weekly? <BR>
	</form>
	
	<form action="outItem" method="post">
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		<input type="submit" value="View all items">
	</form>
	<br>
	
	
</body>
</html>