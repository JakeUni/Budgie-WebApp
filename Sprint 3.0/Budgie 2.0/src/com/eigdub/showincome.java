package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/outincome")
public class showincome extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String userID = null;
		Cookie[] cookies = req.getCookies();
		boolean hascookie = false;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					userID = cookie.getValue();
					hascookie = true;
				}
			}
		} else {
			res.sendRedirect("index.jsp");
			return;
		}
		if (!hascookie) {
			res.sendRedirect("index.jsp");
			return;
		}
		try {
	        
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement(); 
			Statement stmt2 = conn.createStatement(); 

			ResultSet result = stmt.executeQuery("Select * from income where userid='" + userID + "'");
			ResultSet result2 ;
			
			ArrayList<incomeClass> list = new ArrayList<incomeClass>();
			float rTotal = 0;
			while( result.next()){
				rTotal = 0;
				result2 = stmt2.executeQuery("SELECT * FROM userincome where incomeID ='" + result.getInt("incomeID") + "' and userID = '" + userID + "' ORDER BY date DESC");
				while(result2.next()) {
									list.add(new incomeClass(result2.getFloat("amount"),result.getString("incomeName"),result2.getString("date"),result.getInt("incomeID"),result2.getInt("iduserincome")));  
									rTotal = rTotal + result2.getFloat("amount");
				}
				result2.close();
				
				list.add(new incomeClass(rTotal,"Total :" + result.getString("incomeName"),"All Time",result.getInt("incomeID"), 0));  

			}
			conn.close();
			req.setAttribute("list", list);
			getServletConfig().getServletContext().getRequestDispatcher("/income-see-edit.jsp").forward(req, res);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
