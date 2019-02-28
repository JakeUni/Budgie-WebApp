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

public class showItemsServlet extends HttpServlet{
	private static final long serialVersionUID = 3L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int userID = Integer.parseInt(req.getParameter("userID"));
		try {
	        
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement(); 
			String sql = "Select * from item where id='" + userID + "'";
			ResultSet result = stmt.executeQuery(sql);
			
			String list = "";
	         
			while( result.next()){
				 list = list + result.getString("itemName") + "   " + result.getInt("itemPrice") + "   " + result.getString("datetime") + "\n";
				
			}
			 System.out.println(list);

			conn.close();
			try {
				Thread.sleep(4000);
				req.setAttribute("test", userID);
				getServletConfig().getServletContext().getRequestDispatcher("/loggedin.jsp").forward(req, res);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
