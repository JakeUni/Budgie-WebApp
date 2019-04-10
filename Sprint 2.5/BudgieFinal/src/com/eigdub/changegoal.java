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

public class changegoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		if((req.getParameter("amount").length() > 8)||!(req.getParameter("amount").matches("[0-9]+(\\.[0-9][0-9]?)?"))){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/seeGoals.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with the goal amount.</font>");
			rd.include(req, res);
			return;
		}else if(req.getParameter("name")==null || req.getParameter("name").length() > 40 || req.getParameter("length") ==null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/seeGoals.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error.</font>");
			rd.include(req, res);
			return;
		}
		else {
			float amount = Float.valueOf(req.getParameter("amount"));
			//int uid = Integer.parseInt(req.getParameter("userID"));

			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			try {		
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("update usergoals set amount = '" + amount + "', goalName ='" + req.getParameter("name") + "',length ='" + req.getParameter("length") + "' WHERE goalID ='" + id + "'");

				System.out.println("yeet");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/seeGoals.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=green>Done.</font>");
		rd.include(req, res);
		return;

	}

}
