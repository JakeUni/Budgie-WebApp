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
		<table>
    <tr>
        <td><b>Name</b></td>
        <td> <%= list.get(i).getItemName() %></td>
    </tr>
    <tr>
        <td><b>Price</b></td>
        <td><%= list.get(i).getItemPrice() %>
</td>
    </tr>
    <tr>
        <td><b>Date</b></td>
        <td><%= list.get(i).getItemDate() %>
</td>
    </tr>
   
</table>
	<button type="button" onclick="">Click me </button>  
<br>
<br>
<%}
		
%>

</body>
</html>