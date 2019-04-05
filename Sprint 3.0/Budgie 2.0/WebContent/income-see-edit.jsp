<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.eigdub.incomeClass"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Income</title>
</head>
<body>
	<a href="addincome.jsp">add income</a>
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

		ArrayList<incomeClass> list = (ArrayList<incomeClass>) request.getAttribute("list");
		if (list.isEmpty()) {
	%>
	<p>No income</p>

	<%
		}else{
			for (int i = 0; i < list.size(); i++) {
				%>
				<p>
					Income :
					<%=list.get(i).getIncomeName()%></p>
				<form action="changeIncome" method="post">
					Income Amount: 
					<input type="number"  step="0.01" name="amount" value='<%=list.get(i).getAmount()%>'> 
					<input type="hidden" name="userID" id="UID" value="<%=UID%>"> 
					<input type="hidden" name="userIncome" id="userIncome" value="<%=list.get(i).getIDUI()%>">

					<p>
						Income Gained On :
						<%=list.get(i).getDate()%></p>
					<%
						if (list.get(i).getIDUI() == 0) {
					%>
					<input type="hidden" value="change amount of this income">

					<%
						} else {
					%>
				
					<input type="submit" value="change amount of this income">
					<%
						}
					%>
				</form>
				<form action="deleteIncome" method="post">
						<input type="submit" value="DELETE">
					    <input type="hidden" name="userIncome" id="userIncome" value="<%=list.get(i).getIDUI()%>">
				</form>
				<hr>

				<%
			}
					}
				%>
		}
		

</body>
</html>