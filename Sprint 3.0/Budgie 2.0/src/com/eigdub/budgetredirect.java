package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class budgetredirect extends HttpServlet {
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
			
			ResultSet result = stmt.executeQuery("SELECT * FROM userbudget WHERE usersID = '" + UID + "'");

			if (result.next()) {
				res.sendRedirect("changebudget.jsp");
				return;
			}else {
				res.sendRedirect("addBudget.jsp");
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
