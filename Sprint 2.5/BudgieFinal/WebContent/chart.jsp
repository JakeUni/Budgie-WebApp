<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.eigdub.totalItem,java.io.OutputStream, java.awt.Color,java.awt.BasicStroke, com.eigdub.dbManager, java.sql.Connection, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.eigdub.chartMake"%>
<%@ page import="org.jfree.chart.ChartFactory"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="org.jfree.chart.JFreeChart"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset"%>
<%@ page import="org.jfree.chart.*"%>
<%@ page import="org.jfree.chart.entity.*"%>
<%@ page import="org.jfree.data.general.*"%>
<%@ page import="java.awt.*"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>Charts</title>

<style> 
#chartcontainer {
	display: grid;
}
html {
	background-color: WhiteSmoke;
	font-family: Helvetica;
}

.chart {
	padding: 10px;
	grid-column: 1;
	border-style: solid;
	border-color: black;
	border-thickness: 1px;
	border-radius: 5px;
	align: left;
	width: 450px;
	text-align: center;
	background-color: white;
	margin: 10px;'
}
#chart1 {
	grid-row: 1;
}
#chart2 {
	grid-row: 2;
}
#chart3 {
	grid-row: 3;
}
#chart4 {
	grid-row: 4;
}
</style>

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
	}else{
		response.sendRedirect("index.jsp");
		return;
	}
	if (!hascookie){
		response.sendRedirect("index.jsp");
		return;
	}
	%>
	<h1>Charts</h1>
	<form action="getCharts" method="post">
		<select name="Date">
		    <option value="1">Weekly</option>
		    <option value="2">Monthly</option>
		    <option value="3">Yearly</option>
	    </select>
		<input type="hidden" name="UID" value="<%=UID%>">
		<input type="hidden" name="link" value=<%=getServletContext().getRealPath(".").replace(" ", "" + (char) 132)%>/>
		<input type="submit" value="Charts">
		<hr>
	</form>
	<div id="chartcontainer"> 
	<div class="chart" id="chart1"><img src="piechart2.png" WIDTH="300" HEIGHT="200" BORDER="0"></div>
	<div class="chart" id="chart2"> <img src="bar1.png" WIDTH="300" HEIGHT="200" BORDER="0"> </div>
	<div class="chart" id="chart3"> <img src="line1.png" WIDTH="300" HEIGHT="200" BORDER="0"> </div>
	<div class="chart" id="chart4"> <img src="incPie.png" WIDTH="300" HEIGHT="200" BORDER="0"> </div>	
	</div>
</body>
</html>