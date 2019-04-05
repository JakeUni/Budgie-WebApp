package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class deletegoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {		
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate("DELETE FROM usergoals WHERE goalID ='" + id + "'");

	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		res.sendRedirect("seeGoals.jsp");

		return;
	}

}
