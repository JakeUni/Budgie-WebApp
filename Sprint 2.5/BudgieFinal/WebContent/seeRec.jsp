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
		ResultSet result5 = stmt5.executeQuery("Select * from itemrec where userid='" + UID + "'");
		ResultSet result4;
		%>
		<h2>Upcomming Purchases</h2>
		<a href="main.jsp">main</a>
		
		<%
		while (result5.next()) {
			result4 = stmt4.executeQuery("Select * from item where itemId ='" + result5.getInt("itemID") + "'");
			result4.next();
			
	%>

		<form action="changeitemrec" method="post">
		Dont forget about:<%=result4.getString("itemName")%>
		Purchase cost: 
		<input type="number"  step="0.01" name="cost" value='<%=result5.getString("Price")%>'> 
		<input type="hidden" name="userID" id="UID" value="<%=UID%>"> 
		<input type="hidden" name="itemrecid" id="itemrecid" value="<%=result5.getInt("itemrecid")%>">	
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
		
		
		Next on :	<input type="datetime-local" id="datetime" name="datetime" min="2017-06-07T00:00" max="2020-06-14T00:00" value="<%=result5.getString("daterec").replace(" ","T").substring(0,16)%>">				
		<br>
		
		<input type="submit" value="change">
		
		</form>
		<form action="deleteitemrec" method="post">
				<input type="submit" value="DELETE">
			    <input type="hidden" name="itemrecid" id="itemrecid" value="<%=result5.getInt("itemrecid")%>">
		</form>
		<hr>
<%
		}
%>
</body>
</html>