package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
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
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/changepass.jsp");
				PrintWriter out= res.getWriter();
				out.println("<font color=red>Error new passwords dont match try again.</font>");
				rd.include(req, res);
				return;		
			}else 
			{
				try {
					dbManager db = new dbManager();
					Connection conn = db.getConnection();
					Statement stmt = conn.createStatement();
					Statement stmt2 = conn.createStatement();
		
					ResultSet result = stmt.executeQuery("Select * from user where username='" + user + "' and password ='" + pass.toString() + "'");
					if(result.next()) {
						stmt2.executeUpdate("update user set password = '" + pass1 + "' WHERE username ='" + user + "'");
						res.sendRedirect("index.jsp");
					}else {
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/changepass.jsp");
						PrintWriter out= res.getWriter();
						out.println("<font color=red>Error with current username or password.</font>");
						rd.include(req, res);
						return;
						
		
					}
		
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}
			}
		}

}
