package com.eigdub;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
			ArrayList<itemClass> list = new ArrayList<itemClass>();
	        int i = 0;
			while( result.next()){
				list.add(new itemClass(result.getInt("itemPrice"),result.getString("itemName"),result.getString("datetime"),result.getInt("itemId")));  
				
			}
			conn.close();
			req.setAttribute("list", list);
			getServletConfig().getServletContext().getRequestDispatcher("/see-edit.jsp").forward(req, res);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
