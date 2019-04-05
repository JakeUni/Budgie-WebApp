package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class clear extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String UID = null;
		Cookie[] cookies = req.getCookies();
		boolean hascookie = false;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					UID = cookie.getValue();
					hascookie = true;
				}
			}
		}else{
			res.sendRedirect("index.jsp");
			return;
		}
		if (!hascookie){
			res.sendRedirect("index.jsp");
			return;
		}
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {		
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate("DELETE FROM incomerec WHERE userID ='" + UID + "'");
			stmt.executeUpdate("DELETE FROM userincome WHERE userID ='" + UID + "'");
			stmt.executeUpdate("DELETE FROM usergoals WHERE userid ='" + UID + "'");
			stmt.executeUpdate("DELETE FROM useritems WHERE userID ='" + UID + "'");
			stmt.executeUpdate("DELETE FROM itemrec WHERE userid ='" + UID + "'");
			stmt.executeUpdate("DELETE FROM item WHERE usersid ='" + UID + "'");
			stmt.executeUpdate("DELETE FROM userbudget WHERE usersID ='" + UID + "'");
			stmt.executeUpdate("DELETE FROM income WHERE usersID ='" + UID + "'");

			System.out.println("yeet");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=orange>All data cleared.</font>");
		rd.include(req, res);
		return;
		
		
		
	}

}
