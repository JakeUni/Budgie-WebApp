package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class changeuserdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		try {	
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result;
			Statement stmt2 = conn.createStatement();
			ResultSet result2;
			result = stmt.executeQuery("Select * from user where userid='" + req.getParameter("userID") + "' AND password = '" + req.getParameter("password") + "'");
		
			if(result.next() && req.getParameter("email")!= null && req.getParameter("email").length() < 50 && req.getParameter("user").length() < 40) {	
				if(req.getParameter("user") != null) {
								result2 = stmt2.executeQuery("Select * from user where username ='" + req.getParameter("user") + "'");
								if (result2.next()){
									res.sendRedirect("accountDetails.jsp");
									return;
								}	
				}
				stmt2.executeUpdate("update user set email = '" + req.getParameter("email") + "', username ='"+ req.getParameter("user") + "' WHERE userid ='" + req.getParameter("userID") + "'");
				res.sendRedirect("main.jsp");
				return;
				
			}
		
	
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.sendRedirect("accountDetails.jsp");
		return;
		
	}

}
