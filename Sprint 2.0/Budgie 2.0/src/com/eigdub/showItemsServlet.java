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
			Statement stmt2 = conn.createStatement(); 

			ResultSet result = stmt.executeQuery("Select * from item where usersid='" + userID + "'");
			ResultSet result2 ;
			
			ArrayList<itemClass> list = new ArrayList<itemClass>();
			int rTotal = 0;
			while( result.next()){
				rTotal = 0;
				result2 = stmt2.executeQuery("SELECT * FROM useritems where itemID ='" + result.getInt("itemId") + "' and userID = '" + userID + "' ORDER BY datepurchased DESC");
				while(result2.next()) {
									System.out.println(result2.getString("datepurchased"));
									list.add(new itemClass(result2.getInt("cost"),result.getString("itemName"),result2.getString("datepurchased"),result.getInt("itemId"),result2.getInt("iduseritems")));  
									rTotal = rTotal + result2.getInt("cost");
				}
				result2.close();
				
				list.add(new itemClass(rTotal,"Total :" + result.getString("itemName"),"All Time",result.getInt("itemId"), 0));  

			}
			conn.close();
			req.setAttribute("list", list);
			getServletConfig().getServletContext().getRequestDispatcher("/see-edit.jsp").forward(req, res);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
