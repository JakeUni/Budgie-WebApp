package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class changebudget extends HttpServlet {
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
		
		
		if ((req.getParameter("amount").length() > 7)||!(req.getParameter("amount").matches("[0-9]+(\\.[0-9][0-9]?)?"))) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/changebudget.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with the budget monetary amount.</font>");
			rd.include(req, res);
			return;
		}
		
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();

			ResultSet result = stmt.executeQuery("SELECT * FROM userbudget WHERE usersID = '" + UID + "'");
			
			if (result.next()) {
				stmt2.executeUpdate("update userbudget set amount = '" + (req.getParameter("amount")) + "', daterec ='" + req.getParameter("datetime").replace("T", " ") +":00', length ='"+ req.getParameter("length") + "' WHERE usersID ='" + UID + "'");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
				PrintWriter out= res.getWriter();
				out.println("<font color=green>Budget Changed.</font>");
				rd.include(req, res);
				return;
			} else {
				res.sendRedirect("addBudget.jsp");
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
