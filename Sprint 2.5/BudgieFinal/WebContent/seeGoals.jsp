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
		int budget = 0;
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
		Statement stmt4 = conn.createStatement();
		Statement stmt5 = conn.createStatement();
		ResultSet result5 = stmt5.executeQuery("Select * from usergoals where userid='" + UID + "'");

		%>
		<h2>Goals</h2>
		<a href="main.jsp">main</a>
		
		<%
		while (result5.next()) {
		%>

		<form action="changegoal" method="post">
		Goal Name : <input type="text" name="name" value='<%=result5.getString("goalName")%>'> <br>
		
		Goal Amount: 
		<input type="number" name="amount" step="0.01" value='<%=result5.getString("amount")%>'> 
		<input type="hidden" name="userID" id="UID" value="<%=UID%>"> 
		<input type="hidden" name="id" id="id" value="<%=result5.getInt("goalID")%>">	
		<%
			if(result5.getInt("length")==0){
				%>
				<input type="radio" name="length" value="0" checked>					Weakly Repeat
				<input type="radio" name="length" value="1">						Monthly Repeat		<br><%
			}else{
				%>
				<input type="radio" name="length" value="0">					Weakly Repeat
				<input type="radio" name="length" value="1" checked>						Monthly Repeat		<br><%
			}
%>
		
		
		Next on :	<%=result5.getString("date")%>				
		<br>
		
		<input type="submit" value="change">
		
		</form>
		<form action="deletegoal" method="post">
				<input type="submit" value="DELETE">
			    <input type="hidden" name="id" id="id" value="<%=result5.getInt("goalID")%>">
		</form>
		<hr>
<%
		}
%>
</body>
</html>