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
		int noOfDays = 7; //i.e one week
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);            
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		date = calendar.getTime();
		String newDate = dateFormat.format(date);
		ResultSet result;
		boolean empty = true;
		int itemID = 1;
		try {
			System.out.println(itemName  + "fucking cunt" + userID + itemPrice);
			dbManager db = new dbManager();

			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			
			result = stmt.executeQuery("SELECT * from item where itemName='" + itemName + "' and usersid = '" + userID + "'");
			
			while (result.next()){
				empty = false;
				itemID = result.getInt("itemId");
				System.out.println(itemName);
				System.out.println("fuck cunt  " + itemID);

			}
			if (empty) {
				stmt.executeUpdate("INSERT INTO item (itemName, usersid) VALUES ('" + itemName + "', '" + userID + "')");
				result = stmt.executeQuery("SELECT * from item where itemName='" + itemName + "' and usersid = '" + userID + "'");
				result.next();
				System.out.println("fuck cunt2  " + itemID);
				itemID = result.getInt("itemId");
			}
			
			
			String sql = "INSERT INTO useritems (itemID, userID, cost) VALUES ('" + itemID + "', '" + userID + "', '" + itemPrice + "')";
			System.out.print(sql);
			stmt.executeUpdate(sql);
			
			
			if (rea != null) {
				sql = "INSERT INTO itemRec (itemID, daterec, userid, Price) VALUES ('" + itemID + "', '" + newDate + "', '" + userID + "', '" + itemPrice + "')";
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
