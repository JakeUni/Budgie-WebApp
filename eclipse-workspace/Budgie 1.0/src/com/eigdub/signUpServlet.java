package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;




public class signUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		String reppass = req.getParameter("reppass");
		String email = req.getParameter("email");
	System.out.print(user);
	System.out.print(pass);
	System.out.print(reppass);
	System.out.print(email);

		try {
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt;
			stmt = conn.createStatement();

			String sql = "Select * from user where username = '" + user + "'";
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				conn.close();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/createUser.jsp");
				PrintWriter out= res.getWriter();
				out.println("<font color=red>Username is taken sorry.</font>");
				rd.include(req, res);
			}
			if(!pass.contentEquals(reppass)) {
				conn.close();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/createUser.jsp");
				PrintWriter out= res.getWriter();
				out.println("<font color=red>passwords do not match sorry.</font>");
				rd.include(req, res);
			}
			if((pass != null ) && (user != null) && (email != null)){
				System.out.print("INSERT INTO user (username, password, email) VALUES ('" + user + "' , '" + pass + "' , '" + email + "'");
				stmt.executeUpdate("INSERT INTO user (username, password, email) VALUES ('" + user + "' , '" + pass + "' , '" + email + "')");
				conn.close();
				res.sendRedirect("index.jsp");
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
