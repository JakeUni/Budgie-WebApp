<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MAIN</title>
</head>
<body>
	<%
		String UID = null;
		int budget = 0;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					UID = cookie.getValue();
				}
			}
		} else {
			response.sendRedirect("index.jsp");
		}
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "Select * from userbudget where usersid ='" + UID + "'";

		ResultSet result = stmt.executeQuery(sql);
		boolean empty = true;
		while (result.next()) {
			System.out.println("fucking cunt");
			budget = result.getInt("amount");
			empty = false;
	%>
	<h2>Budget</h2>
	<p>
		Current budget is for
		<%=result.getInt("amount")%></p>
	<p>
		It started on
		<%=result.getString("budgetstart")%>
		and will end on
		<%=result.getString("budgetend")%>
		</p>
	<hr></hr>
	<%
		Statement stmt0 = conn.createStatement();
			ResultSet result0 = stmt0.executeQuery(
					"SELECT * FROM useritems where userID ='" + UID + "' ORDER BY datepurchased DESC");
	%>
	<h2>Recent Purchases</h2>
	<%
		for (int i = 0; i < 3; i++) {
				result0.next();
				Statement stmt10 = conn.createStatement();
				ResultSet result10 = stmt10
						.executeQuery("SELECT * FROM item where itemId ='" + result0.getInt("itemID") + "'");
				result10.next();
	%>
	<p>
		Recent purchase of
		<%=result10.getString("itemName")%>
		on
		<%=result0.getString("datepurchased")%>
		a total of
		<%=result0.getInt("cost")%>
	</p>
	<%
		}
	%>
	
	<hr></hr>
	<h2>Budget Breakdown</h2>


	<%
		Statement stmt2 = conn.createStatement();
			ResultSet result3 = stmt2.executeQuery("Select * from item where usersid='" + UID + "'");
			int rTotal = 0;
			float rpercent = 0;
			int rfTotal = 0;
			ResultSet result2;
			Statement stmt3 = conn.createStatement();

			while (result3.next()) {
				rTotal = 0;

				result2 = stmt3.executeQuery("SELECT * FROM useritems where itemID ='" + result3.getInt("itemId")
						+ "' and userID = '" + UID + "' and datepurchased <= '" + result.getString("budgetend")
						+ "' and datepurchased >= '" + result.getString("budgetstart") + "'");
				while (result2.next()) {
					System.out.println("fuck yes");
					rTotal = rTotal + result2.getInt("cost");
				}
				result2.close();
				rfTotal = rfTotal + rTotal;
				rpercent = ((rTotal * 100 / budget));
	%>
	<p>
		You have spent
		<%=rpercent%>% of your budget on
		<%=result3.getString("itemName")%>
		a total of
		<%=rTotal%>
	</p>
	<%
		}
	%>
	<p>
		You have spent
		<%=rfTotal%>
		,
		<%=(rfTotal * 100) / budget%>% of your budget leaving you with
		<%=budget%>
		left to spend
	</p>
	<%
		}
		if (empty) {
			System.out.println("fucking whore");
			response.sendRedirect("addBudget.jsp");
		}
		Statement stmt4 = conn.createStatement();
		Statement stmt5 = conn.createStatement();
		ResultSet result5 = stmt5.executeQuery("Select * from itemrec where userid='" + UID + "'");
		ResultSet result4;
		%>
		<hr></hr>
		<h2>Upcomming Purchases</h2>
		<%
		while (result5.next()) {
			result4 = stmt4.executeQuery("Select * from item where itemId ='" + result5.getInt("itemID") + "'");
			result4.next();
	%>
	<p>
		Don't forget about the
		<%=result4.getString("itemName")%>
		of price
		<%=result5.getString("Price")%>
		that will be taken out on the
		<%=result5.getString("daterec")%>
	</p>

	<%
		result4.close();
		}
		result5.close();
		
	%>
	<a href="loggedin.jsp">add items</a>
	<h3>Daily tip:</h3>
	<p>Donate to budgie!</p>
</body>
</html>