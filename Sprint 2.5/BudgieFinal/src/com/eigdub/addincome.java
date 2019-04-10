package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inIncome")
public class addincome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String incomeName = req.getParameter("incomeName");
		String Temp = req.getParameter("amount");
		
		if ((Temp.equals(""))||!(Temp.matches("[0-9]+(\\.[0-9][0-9]?)?"))||((Temp.length() >8))) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/addincome.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with income amount.</font>");
			rd.include(req, res);
			return;
		}
		
		float amount = Float.valueOf((req.getParameter("amount")));
		int userID = Integer.parseInt(req.getParameter("userID"));
		String rea = (req.getParameter("reacurring?"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);            
		
		ResultSet result;
		boolean empty = true;
		int incomeID = 1;
		if(incomeName.contentEquals("")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loggedin.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with income name.</font>");
			rd.include(req, res);
			return;
		}
		if((amount < 0)||(amount <= 0)||(amount>100000)||(incomeName.equals(""))||!(amount > 0)){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/addincome.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with income name.</font>");
			rd.include(req, res);
			return;
		}
		
		try {
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			
			result = stmt.executeQuery("SELECT * from income where incomeName='" + incomeName + "' and userid = '" + userID + "'");
			
			while (result.next()){
				empty = false;
				incomeID = result.getInt("incomeID");
			}
			if (empty) {
				stmt.executeUpdate("INSERT INTO income (incomeName, userid) VALUES ('" + incomeName + "', '" + userID + "')");
				result = stmt.executeQuery("SELECT * from income where incomeName='" + incomeName + "' and userid = '" + userID + "'");
				result.next();
				incomeID = result.getInt("incomeID");
			}
			
			
			String sql = "INSERT INTO userincome (incomeID, userID, amount) VALUES ('" + incomeID + "', '" + userID + "', '" + req.getParameter("amount") + "')";
			stmt.executeUpdate(sql);
			
			if (rea != null) {
				if( rea.contentEquals("0")) {
					calendar.add(Calendar.WEEK_OF_YEAR, 1);
					date = calendar.getTime();
					String newDate = dateFormat.format(date);
					sql = "INSERT INTO incomerec (incomeID, daterec, userID, amount) VALUES ('" + incomeID + "', '" + newDate + "', '" + userID + "', '" + req.getParameter("amount") + "')";

				}else {
					calendar.add(Calendar.MONTH, 1);
					date = calendar.getTime();
					String newDate = dateFormat.format(date);
					sql = "INSERT INTO incomerec (incomeID, daterec, userID, amount,length) VALUES ('" + incomeID + "', '" + newDate + "', '" + userID + "', '" + req.getParameter("amount") +  "', '" + rea + "')";
					
					
				}
				stmt.executeUpdate(sql);
			}
			conn.close();
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/addincome.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=green>income added.</font>");
			rd.include(req, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	
	}

}
