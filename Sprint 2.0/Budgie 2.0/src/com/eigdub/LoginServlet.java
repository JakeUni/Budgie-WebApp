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
			Statement stmt3 = conn.createStatement();

			ResultSet result = stmt.executeQuery("Select userid from user where username='" + user + "' and password ='" + pass.toString() + "'");
			ResultSet result2;

			if (result.next()) {
				int temp = result.getInt("userid");
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
					
					result = stmt.executeQuery("SELECT * FROM itemrec WHERE userid = '" + temp + "'");
					while(result.next()){
						if (currentDate.after(result.getDate("daterec"))) {
							result2 = stmt2.executeQuery("SELECT * FROM item WHERE itemID = '" + result.getInt("itemID") + "'");
							marker = true;
							System.out.print("yay");
							result2.next();
							stmt3.executeUpdate("INSERT INTO useritems (itemID, userID, cost) VALUES ('" + result2.getInt("itemId") + "', '" + result2.getInt("usersid") + "', '" + result.getInt("Price") + "')");
							stmt3.executeUpdate("update itemrec set daterec = '" + nextDate + "' WHERE itemrecid ='" + result.getString("itemrecid") + "'");
						}
					}
				
				}while(marker == true);
			
				
				res.sendRedirect("main.jsp");
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
