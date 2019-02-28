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

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		try {
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "Select id from user where username='" + user + "' and password ='" + pass.toString() + "'";
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				int temp = result.getInt("id");
				conn.close();
				req.setAttribute("test", temp);
				getServletConfig().getServletContext().getRequestDispatcher("/loggedin.jsp").forward(req, res);
			} else {
				res.sendRedirect("index.jsp");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
