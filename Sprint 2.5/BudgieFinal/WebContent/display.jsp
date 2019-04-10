<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>
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
	}else{
		response.sendRedirect("index.jsp");
		return;
	}
	if (!hascookie){
		response.sendRedirect("index.jsp");
		return;
	}
	%>
</body>

<form action="getCharts" method="post">
		normal
		<label class="switch">
		  <input type="checkbox" checked name ="dark">
		  <span class="slider"> </span>
		</label>		
		darkmode
		<br><br>
		
		Tips off
		<label class="switch">
		  <input type="checkbox" name ="tips">
		  <span class="slider round"></span>
		</label>
		Tips on
		<br><br>
		Text Size
		<select name="Size">
		    <option value="1">S</option>
		    <option value="2">M</option>
		    <option value="3">L</option>
	    </select>
	    <br><br>
		Prefferd Chart Type
		<select name="chart">
		    <option value="1">Pie chart of purchases</option>
		    <option value="2">Pie chart of Income</option>
		    <option value="3">Line graph of budget spending</option>
		    <option value="3">Bar chart of item types</option>
		    
	    </select>
	    
	    
		<input type="hidden" name="UID" value="<%=UID%>">
		<input type="submit" value="Submit">
		<hr>
	</form>
<a href="main.jsp">MAIN</a> 

</html>