<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.eigdub.itemClass" %>
<%@ page import="java.util.ArrayList" %> 
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
		if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user"))
			{ 
				UID = cookie.getValue();
			}
		}
		}else{
			response.sendRedirect("index.jsp");
		}
		
		ArrayList<itemClass> list = (ArrayList<itemClass>) request.getAttribute("list");
		for (int i = 0; i<list.size(); i++){%>
		<p>Item : <%= list.get(i).getItemName() %></p>
		<form action="changeitem" method="post">
		Purchase cost: <input type="text" name="cost" value='<%= list.get(i).getItemPrice() %>'>
		<input type="hidden" name="userID" id="UID" value="<%=UID%>">
		<input type="hidden" name="useritem" id="useritem" value="<%=list.get(i).getuseritemsid()%>">
	
		<p>purchased on : <%= list.get(i).getItemDate() %></p>
		<%
			if(list.get(i).getuseritemsid() == 0){
				%>
						<input type="hidden" value="change price of this purchase">
				
				<%
				}else{
					%>
							<input type="submit" value="change price of this purchase">
					<%
			}
		
		%>
		</form>
		<hr>
		
<%}
		
%>

</body>
</html>