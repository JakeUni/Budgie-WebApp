package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class changeItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int useritem = Integer.parseInt(req.getParameter("useritem"));
		if((req.getParameter("cost").length() > 8)||!(req.getParameter("cost").matches("[0-9]+(\\.[0-9][0-9]?)?"))){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loggedin.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with the item change length.</font>");
			rd.include(req, res);
			return;
		}else {
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			try {		
				Statement stmt = conn.createStatement(); 
				stmt.executeUpdate("update useritems set cost = '" + req.getParameter("cost") + "',datepurchased ='"+req.getParameter("datetime").replace("T", " ") +":00' WHERE iduseritems ='" + useritem + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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


