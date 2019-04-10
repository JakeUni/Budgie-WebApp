<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.eigdub.totalItem,java.io.OutputStream, java.awt.Color,java.awt.BasicStroke, com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.eigdub.chartMake"%>
    




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MAIN</title>		

<style>
#myProgress {
  width: 100%;
  height: 30px;
  background-color: red;
}

</style>

<script 
src="https://canvasjs.com/assets/script/canvasjs.min.js">
</script>

</head>

<body>

	<%
	
		// SUPER IMPORTANT MAKES POPUP
		//		<button type="button" onclick="alert('You pressed the button!')">Click me!</button>
		// SUPER IMPORTANT MAKES POPUP
		//DefaultCategoryDataset  dataset =  new DefaultCategoryDataset( );  
		int budget = 0;
		String linked = getServletContext().getRealPath(".") + "/main.png";

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
		
		String sql = "Select * from userbudget where usersid ='" + UID + "'";

		ResultSet result = stmt.executeQuery(sql);
		Statement newDate = conn.createStatement();
		ResultSet newDateRes;
		boolean empty = true;
		
		Statement users = conn.createStatement();
		
		//String sql = "Select * from userbudget where usersid ='" + UID + "'";

		ResultSet userr = users.executeQuery("Select * from user where userid ='" + UID + "'");
		userr.next();
		int dark = userr.getInt("dark");
		int size = userr.getInt("size");
		int tips = userr.getInt("tips");
		int graph = userr.getInt("graph");
		
		
		switch(graph) {
		  case 1:
				chartMake.makePie(linked,Integer.parseInt(UID),1) ;
			  	break;
		  case 2:
		    	chartMake.makeIncomePie(linked,Integer.parseInt(UID),1) ;
			    break;
		  case 3:
		    	chartMake.makeLine(linked,Integer.parseInt(UID),1) ;

			    break;
		  case 4:
				chartMake.makeBar(linked,Integer.parseInt(UID),1) ;
			    break;
		  default:
		}
		userr.close();
		
		
		while (result.next()) {
			
			if(result.getInt("length") == 0){
				newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 WEEK)");
			}else{
				newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 MONTH)");
			}
			newDateRes.next();

			budget = result.getInt("amount");
			empty = false;
	%>
	<h2>Budget</h2>
	<p>
		Current budget is for
		<%=result.getString("amount")%></p>
	<p>
		It started on
		<%=result.getString("daterec")%>
		and will end on
		<%=
			newDateRes.getString(1)
		%>

		</p>
	<hr></hr>
	<%
		Statement stmt0 = conn.createStatement();
			ResultSet result0 = stmt0.executeQuery("SELECT * FROM useritems where userID ='" + UID + "' ORDER BY datepurchased DESC");
	%>
	<h2>Recent Purchases</h2>
	<%
		for (int i = 0; i < 3; i++) {
			if (!result0.next() ) {
			    
			}else{
				Statement stmt10 = conn.createStatement();
				ResultSet result10 = stmt10.executeQuery("SELECT * FROM item where itemId ='" + result0.getInt("itemID") + "'");
				result10.next();
				%>
				<p>
					Recent purchase of
					<%=result10.getString("itemName")%>
					on
					<%=result0.getString("datepurchased")%>
					a total of
					<%=result0.getString("cost")%>
				</p>
				<%
					}
		}
				%>
				
	<hr></hr>
	<h2>Budget Breakdown</h2>


	<%
	List<totalItem> totalItems = new ArrayList<totalItem>();

		Statement stmt2 = conn.createStatement();
			ResultSet result3 = stmt2.executeQuery("Select * from item where usersid='" + UID + "'");
			int rTotal = 0;
			float rpercent = 0;
			int rfTotal = 0;
			ResultSet result2;
			Statement stmt3 = conn.createStatement();
		
			
			
			// HERE HERE HERE 
			while (result3.next()) {
				rTotal = 0;

				result2 = stmt3.executeQuery("SELECT * FROM useritems where itemID ='" + result3.getInt("itemId")
						+ "' and userID = '" + UID + "' and datepurchased <= '" + newDateRes.getString(1)
						+ "' and datepurchased >= '" + result.getString("dateRec") + "'");
				while (result2.next()) {
					
					rTotal = rTotal + result2.getInt("cost");
				}
				result2.close();

				rfTotal = rfTotal + rTotal;
				rpercent = ((rTotal * 100 / budget));
				totalItems.add(new totalItem(result3.getString("itemName"),rTotal));
				
			
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
			
			float rfpercent = (rfTotal * 100) / budget;
			String test =( String.valueOf(rfpercent) + "%");
			
	%>
		
	<p>
		You have spent
		<%=rfTotal%>
		,
		<%=(rfTotal * 100) / budget%>% of your budget leaving you with
		<%=budget - rfTotal%>
		
		left to spend
	</p>																				
	
	<%




		%>
			         <img src="main.png" WIDTH="600" HEIGHT="400" BORDER="0">
		
		<%
	
	
	
		}
		
		if (empty) {
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
		
		Statement goalStmt = conn.createStatement();
		ResultSet goalResult = goalStmt.executeQuery("SELECT * FROM usergoals where userid =" + UID);
		int f = 0;
		while(goalResult.next()){
			f++;
			if(goalResult.getInt("type") == 0){
				int currTotal = 0 ;
				Statement userGoalStmt = conn.createStatement();
				
				ResultSet usergoal = userGoalStmt.executeQuery("SELECT * FROM useritems WHERE userID = '" + UID + "' AND datepurchased < NOW() AND datepurchased > '" + goalResult.getString("date") + "'");
				while (usergoal.next()){
					currTotal = currTotal + usergoal.getInt("cost");
				}
				float percent = 0;
				percent = ((goalResult.getInt("amount") - currTotal)*100 / goalResult.getInt("amount"));
				if(percent < 0){
					percent = 0;
				}
				String width = (String.valueOf(percent) + "%");
				System.out.println(width);
				%>
				<style>
					#<%=goalResult.getString("goalName")%> {
				 		width: <%=width%>;
						height: 30px; 
						background-color: green;
						
					}
				</style>
				<p>Goal named <%=goalResult.getString("goalName")%> you have <%= (goalResult.getInt("amount") - currTotal) %> left to spend out of <%= goalResult.getInt("amount") %></p>
				<div id="myProgress">
	 			<div id="<%=goalResult.getString("goalName")%>"></div>
				</div>
				
				<%
				}else {
					int currTotal = 0 ;
					Statement userGoalStmt = conn.createStatement();
					ResultSet usergoal = userGoalStmt.executeQuery("SELECT * FROM userincome  WHERE userID = '" + UID + "' AND date < NOW() AND date > '" + goalResult.getString("date") + "'");
					while (usergoal.next()){
						currTotal = currTotal + usergoal.getInt("amount");
					}
					float percent = 0;
					percent = 100 - ((goalResult.getInt("amount") - currTotal)*100 / goalResult.getInt("amount"));
					String width = (String.valueOf(percent) + "%");
					System.out.println(width);
					%>
					<style>
						#<%=goalResult.getString("goalName") + f%> {
					 		width: <%=width%>;
							height: 30px; 
							background-color: green;
						}
					</style>
					<p>Goal named <%=goalResult.getString("goalName")%> you have <%= (goalResult.getInt("amount") - currTotal) %> left to make out of <%= goalResult.getInt("amount") %></p>
					<div id="myProgress">
		 			<div id="<%=goalResult.getString("goalName")%>"></div>
					</div>
					
					<%
				}   
		}
		
		
	%>
	<a href="addGoal.jsp">add Goal</a>
		<hr>
		<hr>
	<a href="addincome.jsp">add income</a>
		<hr>
		<hr>
	<a href="loggedin.jsp">add items</a>
		<hr>
		<hr>
	<a href="display.jsp">add items</a>
		<hr>
		<hr>
	<form action="wherebudget" method="post">
		<input type="submit" value="Change or Add a budget">
			<hr>
			<hr>	
	</form>
	<%
		String link = getServletContext().getRealPath(".");
		link = link.replace(" ", "" + (char) 132);

		
	%>
	
	<form action="getCharts" method="post">
			<input type="hidden" name="UID" value="<%=UID%>">
			<input type="hidden" name="Date" value="1">
			<input type="hidden" name="link" value=<%=(link)%>>
			<input type="submit" value="Charts">
				<hr>
				<hr>
	</form>
	
	<form action="logout" method="post">
			<input type="submit" value="logout" class="inputA">
				<hr>
				<hr>
	</form>
	<h3>Daily tip:</h3>
	<p>Donate to budgie!</p>
	<a href="accountDetails.jsp">Account Details</a>
	
</body>

</html>