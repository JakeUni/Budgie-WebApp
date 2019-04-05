<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page
	import="com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
	ResultSet result = stmt.executeQuery("Select * from user where userid='" + UID + "'");
	result.next();
	%>
	<h1>Welcome <%= result.getString("username") %></h1>
	
	<form action="changeuserdetails" method="post">
		Enter Username (or leave blank to keep current username) <input type="text" name="user" ><br>
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		Email address : <input type="email" name="email" value=<%=  result.getString("email") %>>						
		Password :	<input type="password" name="password" >				
		<br>
		<input type="submit" value="Change">
	
	</form>
	<a href="main.jsp">main</a> <br>
	<a href="changepass.jsp">Change Password</a> <br>
	<a href="clear.jsp">Clear Data</a> <br>
	
</body>
</html>