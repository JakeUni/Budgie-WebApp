<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.eigdub.itemClass"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items</title>
</head>
<body>
	<a href="loggedin.jsp">add items</a>
	<hr>

	<%
		
	%>
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

		ArrayList<itemClass> list = (ArrayList<itemClass>) request.getAttribute("list");
		if (list.isEmpty()) {
	%>
	<p>No purchases</p>

	<%
		}
		for (int i = 0; i < list.size(); i++) {
	%>
	<p>
		Item :
		<%=list.get(i).getItemName()%></p>
	<form action="changeitem" method="post">
		Purchase cost: 
		<input type="number"  step="0.01" name="cost" value='<%=list.get(i).getItemPrice()%>'> 
		Item Type : <%=list.get(i).getType()%>
		<input type="hidden" name="userID" id="UID" value="<%=UID%>"> 
		<input type="hidden" name="useritem" id="useritem" value="<%=list.get(i).getuseritemsid()%>">	
		<br>
	
		<%
			if (list.get(i).getuseritemsid() == 0) {
		%>
		<input type="hidden" value="change this purchase">
		
		<%
			} else {
		%>
				purchased on :	<input type="datetime-local" id="datetime" name="datetime" min="2017-06-07T00:00" max="2020-06-14T00:00" value="<%=list.get(i).getItemDate().replace(" ","T").substring(0,16)%>">				
	
		<br>
		<input type="submit" value="change this purchase">
	
		<%
			}
		%>
	</form>
	<form action="deleteitem" method="post">
			<input type="submit" value="DELETE">
		    <input type="hidden" name="useritem" id="useritem" value="<%=list.get(i).getuseritemsid()%>">
	</form>
	<hr>

	<%
		}
	%>

</body>
</html>