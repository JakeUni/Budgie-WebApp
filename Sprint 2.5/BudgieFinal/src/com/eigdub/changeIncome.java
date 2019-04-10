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
 * Servlet implementation class changeIncome
 */
@WebServlet("/changeIncome")
public class changeIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int userIncome = Integer.parseInt(req.getParameter("userIncome"));
		if((req.getParameter("amount").length() > 8)||!(req.getParameter("amount").matches("[0-9]+(\\.[0-9][0-9]?)?"))){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/addincome.jsp");
			PrintWriter out= res.getWriter();
			out.println("<font color=red>Error with the income change amount.</font>");
			rd.include(req, res);
			return;
		}else {
			float amount = Float.valueOf(req.getParameter("amount"));
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			try {		
				Statement stmt = conn.createStatement(); 
				stmt.executeUpdate("update userincome set amount = '" + amount + "' WHERE iduserincome ='" + userIncome + "'");

				System.out.println("yeet");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("outincome");
		rd.forward(req,res);
		return;
	

	}

}
