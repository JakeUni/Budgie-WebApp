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

public class changepass extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("oldpass");
		String pass1 = req.getParameter("newpass1");
		String pass2 = req.getParameter("newpass2");
		if(!pass1.equals(pass2)) {
			res.sendRedirect("index.jsp");
		}
		try {
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();

			ResultSet result = stmt.executeQuery("Select * from user where username='" + user + "' and password ='" + pass.toString() + "'");
			while (result.next()){
				stmt2.executeUpdate("update user set password = '" + pass1 + "' WHERE username ='" + user + "'");
			}
			res.sendRedirect("index.jsp");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

}
