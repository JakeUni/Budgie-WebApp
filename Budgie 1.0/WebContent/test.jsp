<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.eigdub.dbManager"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		if (conn == null) {
			out.println(conn);
			
		} else {
			out.println(conn);
			
		}
		
	%>

</body>
</html>