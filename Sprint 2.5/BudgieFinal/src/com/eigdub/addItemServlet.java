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
		String Temp = req.getParameter("itemPrice");
		if ((Temp.equals(""))||!(Temp.matches("[0-9]+(\\.[0-9][0-9]?)?"))||((Temp.length() >8))) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loggedin.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with Item Price.</font>");
			rd.include(req, res);
			return;
		}

		
		float itemPrice = Float.valueOf(req.getParameter("itemPrice"));
		int userID = Integer.parseInt(req.getParameter("userID"));
		String rea = (req.getParameter("reacurring?"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);            
		
		ResultSet result;
		boolean empty = true;
		int itemID = 1;
		
		if(itemName.contentEquals("")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loggedin.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with item name.</font>");
			rd.include(req, res);
			return;
		}
		if(((itemPrice < 0)||(itemPrice <= 0)||(itemPrice>10000)||(itemName.equals(""))||!(itemPrice > 0))){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loggedin.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with item name.</font>");
			rd.include(req, res);
			return;
		}
	
		
		
		try {
			System.out.println(itemName  + "fucking cunt" + userID + itemPrice);
			dbManager db = new dbManager();

			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			
			result = stmt.executeQuery("SELECT * from item where itemName='" + itemName + "' and usersid = '" + userID + "'");
			
			while (result.next()){
				empty = false;
				itemID = result.getInt("itemId");
			}
			
			int essential = 0;
			if (req.getParameter("check") != null) {
				essential = Integer.parseInt(req.getParameter("check"));
			}
			if (empty) {
				if (req.getParameter("type").contentEquals("")) {
					stmt.executeUpdate("INSERT INTO item (itemName, usersid,essential,type) VALUES ('" + itemName + "', '" + userID  +  "', '" + essential + "','misc')");
					result = stmt.executeQuery("SELECT * from item where itemName='" + itemName + "' and usersid = '" + userID + "'");
					result.next();
					itemID = result.getInt("itemId");				
				}else {
					stmt.executeUpdate("INSERT INTO item (itemName, usersid,essential, type) VALUES ('" + itemName + "', '" + userID +  "', '" + essential + "','" +req.getParameter("type")+"')");
					result = stmt.executeQuery("SELECT * from item where itemName='" + itemName + "' and usersid = '" + userID + "'");
					result.next();
					itemID = result.getInt("itemId");

				}
				
			}
			

			String sql = "INSERT INTO useritems (itemID, userID, cost) VALUES ('" + itemID + "', '" + userID + "', '" + itemPrice + "')";

			if(!(req.getParameter("datetime") == "")) {
				System.out.print(req.getParameter("datetime") );
				sql = "INSERT INTO useritems (itemID, userID, cost,datepurchased) VALUES ('" + itemID + "', '" + userID + "', '" + itemPrice + "','"+ req.getParameter("datetime").replace("T", " ") +":00')";
			}
			System.out.print(sql);
			stmt.executeUpdate(sql);
			
			
			if (rea != null) {
				if( rea.contentEquals("0")) {
					calendar.add(Calendar.WEEK_OF_YEAR, 1);
					date = calendar.getTime();
					String newDate = dateFormat.format(date);
					sql = "INSERT INTO itemRec (itemID, daterec, userid, Price) VALUES ('" + itemID + "', '" + newDate + "', '" + userID + "', '" + itemPrice + "')";
					if(!(req.getParameter("datetime") == "")) {
						sql = "INSERT INTO itemRec (itemID, daterec, userid, Price) VALUES ('" + itemID + "', '" + req.getParameter("datetime").replace("T", " ") + ":00', '" + userID + "', '" + itemPrice + "')";
					}

					}else {
					calendar.add(Calendar.MONTH, 1);
					date = calendar.getTime();
					String newDate = dateFormat.format(date);
					
					sql = "INSERT INTO itemRec (itemID, daterec, userid, Price,length) VALUES ('" + itemID + "', '" + newDate + "', '" + userID + "', '" + itemPrice +  "', '" + rea + "')";
					if(!(req.getParameter("datetime") == "")) {
						sql = "INSERT INTO itemRec (itemID, daterec, userid, Price,length) VALUES ('" + itemID + "', '" + req.getParameter("datetime").replace("T", " ") + ":00', '" + userID + "', '" + itemPrice +  "', '" + rea + "')";
					}
					
				}
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
