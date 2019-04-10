<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page
	import="com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account details</title>
</head>
<style> 
.row1col1 {
	grid-row: 1;
	grid-col:1;
}
.row1col2 {
	grid-row: 1;
	grid-col:2;
}
.row2col1 {
	grid-row: 2;
	grid-col:1;
}
.row2col2 {
	grid-row: 2;
	grid-col:2;
}
.row3col1 {
	grid-row: 3;
	grid-col:1;
}
.row3col2 {
	grid-row: 3;
	grid-col:2;
}
html {
	font-family: Helvetica, sans-serif;
}
table {
	margin-top: -20px;
}
.databox {
	display: inline-grid;
    background: linear-gradient(to bottom, #99ff99 3%, #99ff66 100%);
	text-align: left;
	padding: 5%;
	border-radius: 20px;
	margin: 5%;
	/* background */
	background-color: #FFFFFF;
	/* border */
	border-color: #000000
	border-width: thin medium thick 10px;
	border-style: solid;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	width: 200px;

}
.input_text {
	border-radius: 5px;
	background: inherit;
	visibility: hidden border-bottom: 2px solid #333300;
	width: 100px;
	border-color: #333300;
	border-right-width: 0;
	border-left-width: 0;
	border-top-width: 0;
	border-radius: 0;
}

.btn_1_2:hover {
	background-color: #00cc66;
	opacity: 1;
}

html {
	background-color: WhiteSmoke;
}
.btn_1_2 {
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
	background-color:   #00cc66;
}

.btn_1_2>a {
	color: black;
	text-decoration: none;
}
.btn_1_3:hover {
	background-color: #66ff33;
	opacity: 1;
}
.btn_1_3 {
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
	opacity: 0.8;
	background-color: #ff884d;
}
.btn_1_3:hover {
	background-color: #ff6666;
	opacity: 1;
}
.btn_1_3>a {
	text-decoration: none;
		color: black;
}
.btn_1_2>a {
	color: black;
	text-decoration: none;
}
.btn_1_3:hover {
	background-color: #66ff33;
	opacity: 1;
}
.btn_1_3 {
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
	opacity: 0.8;
	background-color: #ff884d;
}
.btn_1_3>a {
	text-decoration: none;
		color: black;
}
.btn_1_4:hover {
	background-color: #0099ff;
	opacity: 1;
}
.btn_1_4 {
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
	opacity: 0.8;
	background-color:  #00ccff;
}
.btn_1_4>a {
	text-decoration: none;
		color: black;
}

#topbar {
	
	background-color: LightGreen;
	position: ABSOLUTE;
	display: grid;
	height: 30px;
	width: 50%;
	border-style: solid;
	border-width: 0.5px;
	margin: 0;
	border-radius: 10px;
	border-color: darkgreen;
}
#cogcontainer {
	background-color: darkgreen;
	grid-row: 1;
		margin-width: auto;
	border-radius: 5px;
	position: ABSOLUTE;
	margin: auto;
	align: right;
	left: 300px;
	font-size: 16px;
	border-style: solid;
		border-width: thin
}
#bustcontainer {
	background-color: darkgreen;
	margin-width: auto;
	border-radius: 5px;
	grid-row: 1;
	border-style: solid;
	position: ABSOLUTE;
	margin: auto;
	align: left;
	left: 4px;
	border-width: thin;
}
</style>
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
	<nav id="topbar"> 
		<div id="cogcontainer"> &#9881; </div> 
		<div id="bustcontainer"> &#128100;</div> 	
	</nav>
	<br>
	<br>
	<h1>Welcome <%= result.getString("username") %></h1>
	<div class="databox"> 
	<form action="changeuserdetails" method="post">
		<div class="row1col1"> Username: </div> <div class="row1col2"> <input type="text" class="input_text" name="user" > </div> <br>
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		<div class="row2col1">Email:  </div> <div class="row2col2"> <input type="email" class="input_text" name="email" value=<%=  result.getString("email") %>> </div> <br>		
		<div class="row3col1"> Password: </div> <div class="row3col2"> 	<input type="password" name="password"  class="input_text"/> </div> <br>
		<br>
		<button class="btn_1_4 type="submit" value="Change"> Change </button>
	</div>
	</form>
	<button class="btn_1_2"> <a href="main.jsp">MAIN</a> </button> <br> 
	<button class="btn_1_2"> <a href="changepass.jsp">Change Password</a> </button>  <br><br>
	<button class="btn_1_3"> <a href="clear.jsp">Clear Data</a> </button> <br>	
</body>
</html>