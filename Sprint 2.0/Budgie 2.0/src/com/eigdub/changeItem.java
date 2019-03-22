package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class changeItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int useritem = Integer.parseInt(req.getParameter("useritem"));
		int cost = Integer.parseInt(req.getParameter("cost"));
		int uid = Integer.parseInt(req.getParameter("userID"));

		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		try {		
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate("update useritems set cost = '" + cost + "' WHERE iduseritems ='" + useritem + "'");

			System.out.println("yeet");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.sendRedirect("main.jsp");

	}
	}


