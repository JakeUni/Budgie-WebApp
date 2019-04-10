<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ page
	import="com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Budget</title>
</head>
<body>
<h1>Change budget</h1>
<%
		String UID = null;
	Cookie[] cookies = request.getCookies();
	boolean hascookie = false;
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				UID = cookie.getValue();
				hascookie = true;
			}
		}
	}else{
		response.sendRedirect("index.jsp");
		return;
	}
	if (!hascookie){
		response.sendRedirect("index.jsp");
		return;
	}
	dbManager db = new dbManager();
	Connection conn = db.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet result = stmt.executeQuery("Select * from userbudget where usersID='" + UID + "'");
	result.next(); 
	%>
	<form action="changebudget" method="post">
		
		<%if(result.getInt("length")==0){
				%>
				<input type="radio" name="length" value="0" checked>					Weakly Repeat
				<input type="radio" name="length" value="1">						Monthly Repeat		<br><%
			}else{
				%>
				<input type="radio" name="length" value="0">					Weakly Repeat
				<input type="radio" name="length" value="1" checked>						Monthly Repeat		<br><%
			}
		%>
	
		Enter New Budget Amount please : <input type="number"  step="0.01" name="amount"><br>
		Next on :	<input type="datetime-local" id="datetime" name="datetime" min="2017-06-07T00:00" max="2020-06-14T00:00" value="<%=result.getString("daterec").replace(" ","T").substring(0,16)%>">				
		
		<input type="submit" value="Change Budget">
	</form>
</body>
</html>