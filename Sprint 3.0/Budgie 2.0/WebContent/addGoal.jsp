<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		} else {
			response.sendRedirect("index.jsp");
			return;
		}
		if (!hascookie) {
			response.sendRedirect("index.jsp");
			return;
		}

	%>
<form action="addGoal" method="post">
		Enter Goal Amount please : <input type="number" step="0.01" name="amount"><br>
		Enter Goal Name please : <input type="text" name="name"><br>
		
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		<input type="radio" name="reacurring?" value="0"	>						Repeat this goal weekly?				
		<input type="radio" name="reacurring?" value="1">						Repeat this goal monthly?
		<br>
		<input type="radio" name="type" value="0"	>						Save this much money		
		<input type="radio" name="type" value="1">						Make this much money
		<br>
		<input type="submit" value="Add Goal">
	
	<a href="main.jsp">main</a>
	<a href="seeGoals.jsp">See/Edit Goals</a>
	
	<br>
</body>
</html>