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

public class addBudget extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//making end date by adding the length onto the current date
	if(req.getParameter("reacurring?") == (null)||(req.getParameter("amount").length() > 8)||!(req.getParameter("amount").matches("[0-9]+(\\.[0-9][0-9]?)?"))){
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addBudget.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=red>Error with the budget monetary amount.</font>");
		rd.include(req, res);
		return;
	}else {
		int rea = Integer.parseInt(req.getParameter("reacurring?"));
		String amount = req.getParameter("amount");
		int uid = Integer.parseInt(req.getParameter("userID"));
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {		
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate("INSERT INTO userbudget (usersid,amount,length) VALUES ('" + uid + "', '" + amount + "','"+ rea +"')");

			System.out.println("yeet");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=green>Budget Added.</font>");
		rd.include(req, res);
		return;
	}

}
}
		
