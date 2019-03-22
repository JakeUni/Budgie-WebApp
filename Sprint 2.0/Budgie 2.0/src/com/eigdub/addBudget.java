package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addBudget extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//making end date by adding the length onto the current date
		int length = Integer.parseInt(req.getParameter("length"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		int noOfDays = length; //i.e one week
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);            
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		date = calendar.getTime();
		String newDate = dateFormat.format(date);
		
		String amount = req.getParameter("amount");
		int uid = Integer.parseInt(req.getParameter("userID"));
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {		
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate("INSERT INTO userbudget (usersid,	,amount) VALUES ('" + uid + "', '" + newDate + "','"+ amount +"')");

			System.out.println("yeet");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.sendRedirect("main.jsp");

	}

}
