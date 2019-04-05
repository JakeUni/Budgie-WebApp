<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="ISO-8859-1">
<title>Budgie Login</title>
<style> 	
body {
	font-family: Helvetica, Arial, sans-serif;
	background-color: #F4F5F4;
}
.container {
	position: fixed;
	left:30%;
	top:20%;
}
#login-box {
	text-align: left;
	padding-width: 20%;
	margin-width: 20%;
	/* background */
	background-color: #FFFFFF;
	/* border */
	border-color: #000000
	border-width: thin medium thick 10px;
	border-style: solid;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	
}
.login-input{
	text-align: left;
	font-size: 10px;
	font-weight: bold;
	color: gray;
}
#main-h {
	font-weight: bold;
	margin-width: 10px;
	font-size: 2em;
}
.btn{
	align: right;
	border-width: 0px;
	color: white;
	border-radius:15px;
	text-align:center;
	background: linear-gradient(135deg, #88dbf2, #3af4f4);
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}
#button-box-left{
	font-size: 10px;
	margin-top: -25px;
	margin-left: 10px;
}
#button-box-right{
	font-size: 10px;
	margin-top: -30px;
	margin-right: 10px;
}
.input-box{
	margin-top: 20px;	
	margin-bottom: 20px;
	margin-left: 10px;
	margin-right: 10px;
}

#red-decoration{
	position: absolute;
	z-index: 1;
	left:10px;
	top:10px;
	width:100px;
	height:100px;
	background-image: linear-gradient(130deg, red, orange);
	padding-width: 10px;
	border-style: thin;
	border-color: red;
	border-radius: 50px;
}
#green-decoration{
	position: absolute;
	z-index: 2;
	left:80px;
	top:-25px;
	width:81px;
	height:81px;
	background-image: linear-gradient(200deg, green, turquoise);
	padding-width: 10px;
	border-style: thin;
	border-color: red;
	border-radius: 50px;
}
#blue-decoration{
	position: absolute;
	z-index: 3;
	left:125px;
	top:0px;
	width:121px;
	height:121px;
	background-image: linear-gradient(145deg, blue, cyan);
	padding-width: 10px;
	border-style: thin;
	border-color: red;
	border-radius: 121px;
}
#purple-decoration{
	position: absolute;
	z-index: 3;
	left:500px;
	top:-25px;
	width:144px;
	height:144px;
	background-image: linear-gradient(200deg, violet, pink);
	padding-width: 10px;
	border-style: thin;
	border-color: red;
	border-radius: 144px;
}
</style>
</head>
<body>
	<div id="decorations-container">
		<div id="red-decoration"></div>
		<div id="green-decoration"></div>
		<div id="blue-decoration"></div>
		<div id="purple-decoration"></div>
	</div>
	<div class="container">
	
	<h1 id="main-h"> Sign into your account </h1>
	<div id="login-box">
	<form action="login" method="post">
		<div class="input-box">
			<label class="login-input">USERNAME</label>
				<input class="w3-input" type="text" name="user">
		</div>
		<div class="input-box"> 
			<label class="login-input">PASSWORD</label>
			<input class="w3-input" type="password" name="pass"> 
		</div>
		<br>
		<div align="right" id="button-box-right"><button class="btn" id="btn-submit" type="sumbit"><strong>&#10140;</strong></button><br><label>Log in</label></div>
	</form>	
			
		<a href="createUser.jsp">Create Account</a>
		<a href="changepass.jsp">change password </a>
	
	</div>
	</div>
	
	<div id="question-circle"> 
	
	</div>
	
</body>
</html>