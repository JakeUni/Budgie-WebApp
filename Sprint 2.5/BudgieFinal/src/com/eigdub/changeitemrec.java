package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changeitemrec
 */
@WebServlet("/changeitemrec")
public class changeitemrec extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int itemrecid = Integer.parseInt(req.getParameter("itemrecid"));
		if((req.getParameter("cost").length() > 8)||!(req.getParameter("cost").matches("[0-9]+(\\.[0-9][0-9]?)?"))){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loggedin.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with the item change length.</font>");
			rd.include(req, res);
			return;
		}else {
			float cost = Float.valueOf(req.getParameter("cost"));
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			try {		
				Statement stmt = conn.createStatement(); 
				stmt.executeUpdate("update itemrec set price = '" + cost + "',daterec ='"+req.getParameter("datetime").replace("T", " ") +":00',length ='"+ req.getParameter("length")+ "' WHERE itemrecid ='" + itemrecid + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		res.sendRedirect("seeRec.jsp");

		return;

	}
}
