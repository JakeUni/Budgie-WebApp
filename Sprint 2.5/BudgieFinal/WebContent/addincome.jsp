<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement"%>



<!DOCTYPE html>

<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Adding income</title>

<script>
	jQuery(
			'<div class="quantity-nav"><div class="quantity-button quantity-up">+</div><div class="quantity-button quantity-down">-</div></div>')
			.insertAfter('.quantity input');
	jQuery('.quantity')
			.each(
					function() {
						var spinner = jQuery(this), input = spinner
								.find('input[type="number"]'), btnUp = spinner
								.find('.quantity-up'), btnDown = spinner
								.find('.quantity-down'), min = input
								.attr('min'), max = input.attr('max');

						btnUp.click(function() {
							var oldValue = parseFloat(input.val());
							if (oldValue >= max) {
								var newVal = oldValue;
							} else {
								var newVal = oldValue + 1;
							}
							spinner.find("input").val(newVal);
							spinner.find("input").trigger("change");
						});

						btnDown.click(function() {
							var oldValue = parseFloat(input.val());
							if (oldValue <= min) {
								var newVal = oldValue;
							} else {
								var newVal = oldValue - 1;
							}
							spinner.find("input").val(newVal);
							spinner.find("input").trigger("change");
						});

					});
</script>
<style>
.btn_1_1 {
	border-radius: 10px;
	border: none;
	color: #003300;
	padding: 8px 16px;
	text-align: center;
	-webkit-transition-duration: 0.4s; /* Safari */
	transition-duration: 0.4s;
	transition: 0.3s;
	margin: 2px 0 !important;
	text-decoration: none;
	cursor: pointer;
	opacity: 0.7;
	background-color: #00ffcc;
}

.btn_1_1:hover {
	background-color: #00ffcc;
	opacity: 1;
}

html {
	font-family: Helvetica, sans-serif;
	background-color: #ccffcc;
}

table {
	margin-top: -20px;
}

.itemBox {
	background: linear-gradient(to bottom right, #00cc99 0%, #00cc66 100%);
	border-style: solid;
	border-color: #003300;
	padding: 0px 15px 15px 10px;
	border-radius: 20px;
	margin: 100px;
	position: absolute;
}

.input_text {
	border-radius: 5px;
	background: inherit;
	visibility: hidden border-bottom: 2px solid #333300;
	width: 100px;
	border-color: rgba(0, 0, 0, 0) rgba(0, 0, 0, 0) rgba(0, 127, 0, 1.0)
		rgba(0, 0, 0, 0);
}
input
[
type
=
"
h
</style>
</head>
<body>
	<h1>Adding Income</h1>
	<button class="btn_1_1" class="">
		<a href="main.jsp">BACK TO MAIN</a>
	</button>

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
		} else {
			response.sendRedirect("index.jsp");
			return;
		}
		if (!hascookie) {
			response.sendRedirect("index.jsp");
			return;
		}
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "Select * from income where userid ='" + UID + "'";
		ResultSet result = stmt.executeQuery(sql);
	%>
	<datalist id="t">

		<%
			while (result.next()) {
		%>
		<option value="<%=result.getString("incomeName")%>">
			<%
				}
			%>
			</p>
	</datalist>
	<h1></h1>
	<div class="itemBox">
		<table>
			<form action="inIncome" method="post">
				<tr>
					<td id="td-right">Enter the income title:</td>
					<td><input class="input_text" type="text" name="incomeName"
						list="t"></td>
					<br>
					<input type="hidden" name="userID" id="UID" value="<%=UID%>">
				</tr>
				<tr>
					<td>Enter the amount you earned:</td>
					<td><input class="input_text" type="number"  step="0.01" name="amount"
						data-role="spinner">
						</div></td>
					<br>
				<tr>
					<td>
						<button id="submit" class="btn_1_1" type="submit" value="Add income">Add income</button> 
						<input type="radio" name="reacurring?" value="0"	>						Repeat this income weekly?
						
						<input type="radio" name="reacurring?" value="1">						Repeat this income monthly?
						
					</td>
					<br>
			</form>
		</table>
	</div>
	<form action="outincome" method="post">
		<input type="submit" value="View all income">
	</form>
	<br>


</body>
</html>