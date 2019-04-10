package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class deleteitem extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String useritemid = req.getParameter("useritem");
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {		
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate("DELETE FROM useritems WHERE iduseritems ='" + useritemid + "'");

	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		RequestDispatcher rd = req.getRequestDispatcher("outItem");
		rd.forward(req,res);
		//RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/loggedin.jsp");
		//PrintWriter out= res.getWriter();
		//	out.println("<font color=green>Item Changed.</font>");
		//	rd1.include(req, res);
		return;
	}

}
