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
			Statement stmt4 = conn.createStatement();
			Statement stmt5 = conn.createStatement();
			Statement newDate = conn.createStatement();
			ResultSet result = stmt.executeQuery("Select userid from user where username='" + user + "' and password ='" + pass.toString() + "'");
			ResultSet result2;
			ResultSet result3;
			ResultSet result4;
			ResultSet newDateRes;
			
			if (result.next()) {
				int temp = result.getInt("userid");
				Cookie loginCookie = new Cookie("user",String.valueOf(temp));
				loginCookie.setMaxAge(30*60);
				res.addCookie(loginCookie);
			
			
						
				boolean marker = false;
				
				do {
					marker = false;
					
					result = stmt.executeQuery("SELECT * FROM itemrec WHERE userid = '" + temp + "' AND daterec < NOW()");
					while(result.next()){
				
						System.out.println("CUNT");
						result2 = stmt2.executeQuery("SELECT * FROM item WHERE itemID = '" + result.getInt("itemID") + "'");      
						if(result.getInt("length") == 0){
							newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 WEEK)");
						}else{
							newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 MONTH)");
						}
						newDateRes.next();		
						String nextDate = newDateRes.getString(1);
						result2.next();
						stmt3.executeUpdate("INSERT INTO useritems (itemID, userID, cost) VALUES ('" + result2.getInt("itemId") + "', '" + result2.getInt("usersid") + "', '" + result.getInt("Price") + "')");
						stmt3.executeUpdate("update itemrec set daterec = '" + nextDate + "' WHERE itemrecid ='" + result.getString("itemrecid") + "'");
						marker = true;
					}
				}while(marker == true);
				
				marker = false;
				do {
					marker = false;
					result3 = stmt4.executeQuery("SELECT * FROM incomerec WHERE userID = '" + temp + "' AND daterec < NOW()");
					while(result3.next()){
							System.out.println("SHOULD WORK");
							result4 = stmt5.executeQuery("SELECT * FROM income WHERE incomeID = '" + result3.getInt("incomeID") + "'");
							marker = true;
							if(result3.getInt("length") == 0){
								newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 WEEK)");
							}else{
								newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 MONTH)");
							}
							newDateRes.next();		
							String nextDate = newDateRes.getString(1);
							result4.next();
							stmt5.executeUpdate("INSERT INTO userIncome (incomeID, userID, amount) VALUES ('" + result4.getInt("incomeID") + "', '" + result4.getInt("userid") + "', '" + result3.getInt("amount") + "')");
							stmt5.executeUpdate("update incomerec set daterec = '" + nextDate + "' WHERE idincomerec ='" + result3.getString("idincomerec") + "'");
					}
				}while(marker == true);
				
				marker = true;
				while(marker == true) {
					marker = false;		
					result = stmt.executeQuery("SELECT * FROM userbudget where length = 0 AND usersID = '" + temp + "' AND daterec < (SELECT DATE_ADD(NOW() ,INTERVAL -1 WEEK))");
					while (result.next()) {
						marker = true;
						newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 WEEK)");	
						newDateRes.next();		
						String nextDate = newDateRes.getString(1);
						System.out.println(nextDate);
						stmt3.executeUpdate("update userbudget set daterec = '" + nextDate + "' WHERE usersID ='" + temp + "'");
					}
				}
				marker = true;
				while(marker == true) {
					marker = false;		
					result = stmt.executeQuery("SELECT * FROM userbudget where length = 1 AND usersID = '" + temp + "' AND daterec < (SELECT DATE_ADD(NOW() ,INTERVAL -1 MONTH))");
					while (result.next()) {
						marker = true;
						newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 MONTH)");	
						newDateRes.next();		
						String nextDate = newDateRes.getString(1);
						System.out.println(nextDate);
						stmt3.executeUpdate("update userbudget set daterec = '" + nextDate + "' WHERE usersID ='" + temp + "'");
					}
				}
				
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
			e.printStackTrace();
		}
	}
}
