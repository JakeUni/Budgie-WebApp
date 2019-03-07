package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addItemServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String itemName = req.getParameter("itemName");
		int itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
		int userID = Integer.parseInt(req.getParameter("userID"));
		String rea = (req.getParameter("reacurring?"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		int noOfDays = -7; //i.e one week
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);            
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		date = calendar.getTime();
		String newDate = dateFormat.format(date);
		
		
		try {
	
			
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement(); 
			String sql = "INSERT INTO item (itemName, itemPrice, id) VALUES ('" + itemName + "', '" + itemPrice + "', '" + userID + "')";
			System.out.print(sql);
			stmt.executeUpdate(sql);
			if (rea != null) {
				sql = "INSERT INTO itemRec (itemName, itemPrice, id, datetime) VALUES ('" + itemName + "', '" + itemPrice + "', '" + userID + "', '" + newDate + "')";
				System.out.print(sql);
				stmt.executeUpdate(sql);
				System.out.print("fuck maybe");
			}
			conn.close();
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loggedin.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=green>Item added.</font>");
			rd.include(req, res);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
