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
 * Servlet implementation class addGoal
 */
@WebServlet("/addGoal")
public class addGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	if((req.getParameter("amount").length() > 8)||!(req.getParameter("amount").matches("[0-9]+(\\.[0-9][0-9]?)?"))){
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addGoal.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=red>Error with the Goal amount.</font>");
		rd.include(req, res);
		return;
	}else if(req.getParameter("name").contentEquals("")){
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addGoal.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=red>Error with the Goal Name.</font>");
		rd.include(req, res);
		return;
	}else if(req.getParameter("reacurring?") == null || req.getParameter("type") == null){
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addGoal.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=red>Error with the Goal type or reacurring.</font>");
		rd.include(req, res);
		return;
	}
	else {
		
		
	
		int rea = Integer.parseInt(req.getParameter("reacurring?"));
		int type = Integer.parseInt(req.getParameter("type"));
		String amount = req.getParameter("amount");
		String name = req.getParameter("name");
		int uid = Integer.parseInt(req.getParameter("userID"));
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {		
			Statement stmt = conn.createStatement(); 
			System.out.println("INSERT INTO usergoals (userid,length,type,amount,goalName) VALUES ('" + uid + "', '" + rea + "','"+ type +"','" + amount + "','" + name + "')");
			stmt.executeUpdate("INSERT INTO usergoals (userid,length,type,amount,goalName) VALUES ('" + uid + "', '" + rea + "','"+ type +"','" + amount + "','" + name + "')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=green>Goal Added.</font>");
		rd.include(req, res);
		return;
	}

}	
}
