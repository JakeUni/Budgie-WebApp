package com.eigdub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		try {
			dbManager db = new dbManager();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();

			String sql = "Select id from user where username='" + user + "' and password ='" + pass.toString() + "'";
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				int temp = result.getInt("id");
				Cookie loginCookie = new Cookie("user",String.valueOf(temp));
				//setting cookie to expiry in 30 mins until we decide on something better
				loginCookie.setMaxAge(30*60);
				res.addCookie(loginCookie);
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date currentDate = new Date();
				int noOfDays = 7; //i.e one week
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentDate);            
				calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
				String nextDate = dateFormat.format(calendar.getTime());
				boolean marker = false;
				do {
					marker = false;
					sql = "SELECT * FROM itemrec WHERE id = '" + temp + "'";
					result = stmt.executeQuery(sql);
					while(result.next()){
						if (currentDate.after(result.getDate("datetime"))) {
							marker = true;
							System.out.print("yay");
							stmt2.executeUpdate("INSERT INTO item (itemName, itemPrice, id) VALUES ('" + result.getString("itemName") + "', '" + result.getInt("itemPrice") + "', '" + temp + "')");
							stmt2.executeUpdate("update itemrec set datetime = '" + nextDate + "' WHERE itemId ='" + result.getString("itemId") + "'");
						}
					}
				
				}while(marker == true);
			
				
				res.sendRedirect("loggedin.jsp");
				conn.close();
				
			} else {
				conn.close();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				PrintWriter out= res.getWriter();
				out.println("<font color=red>Either user name or password is wrong please try again.</font>");
				rd.include(req, res);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
