package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addItemServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String itemName = req.getParameter("itemName");
		int itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
		int userID = Integer.parseInt(req.getParameter("userID"));
		PrintWriter out = res.getWriter();
		try {
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement(); 
			String sql = "INSERT INTO item (itemName, itemPrice, id) VALUES ('" + itemName + "', '" + itemPrice + "', '" + userID + "')";
			System.out.print(sql);
			stmt.executeUpdate(sql);
			conn.close();
			
			out.print("Item Added");

			
			try {
				System.out.print("sleeping");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("returning");
			req.setAttribute("test", userID);
			getServletConfig().getServletContext().getRequestDispatcher("/loggedin.jsp").forward(req, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
