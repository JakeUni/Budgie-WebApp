package com.eigdub;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.*;
import org.jfree.chart.entity.*;
import org.jfree.data.general.*;
import java.awt.*;
import java.io.*;
import org.jfree.data.category.DefaultCategoryDataset;
public class chartMake {

	
	
	public static void makeLine(String link, int UID, int Time) {
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		Statement stmt;
		Statement stmt2;
		Statement stmt3;
		ResultSet result2;	 
		ResultSet result3;
		DefaultCategoryDataset  dataset =  new DefaultCategoryDataset( );  
		try {
			int NE = 0;
			int E = 0;
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			ResultSet result = stmt.executeQuery("Select * from userbudget where usersID='" + UID + "'");
			String date = "";		
			int total = 0;
			int count = 0;
			int count2 = 0;
			int rtotal = 0;
			int avg = 0;
			Statement newDate = conn.createStatement();
			ResultSet newDateRes;
			while (result.next()) {
				String old = "";
				int remainder = 0;
				boolean testing = false;						
				boolean yes = false;
				if(result.getInt("length") == 0){
					newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 WEEK)");
					newDateRes.next();
					date = newDateRes.getString(1);
					newDateRes.close();
					result3 = stmt3.executeQuery("SELECT * FROM useritems where userID = '" + UID + "' and datepurchased <= '"+ date +"' and datepurchased >= '" + result.getString("dateRec") + "'");
					while(result3.next()) {
						count ++;
					}
					System.out.println("This is the max" + count);
					
					for(int i=0;i<8;i++) {
						remainder = (result.getInt("amount")-rtotal);
						if (remainder < 0) {
							remainder = 0;
						}
						count2 = 0;
						total = 0;
						newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL " + i +" day)");
						newDateRes.next();
						date = newDateRes.getString(1);
						newDateRes.close();
						result2 = stmt2.executeQuery("SELECT * FROM useritems where userID = '" + UID + "' and datepurchased <= '" + date + "' and datepurchased >= '" + result.getString("dateRec") + "'");
						
						while (result2.next()) {
							count2++;
							total = total + result2.getInt("cost");
						}
						
						if(count2 == count) {
							if(!testing) {
								dataset.addValue(remainder,"Spending future" , (old.substring(8, 10) + "/" +old.substring(5, 7)));
								testing = true;
							}
							avg = rtotal/i;
							rtotal = rtotal + avg;
							dataset.addValue(remainder,"Spending future" , date.substring(8, 10) + "/" + date.substring(5, 7));
							
						}else {
							rtotal = total;
							dataset.addValue(remainder, "Spending over the week since budget" , (date.substring(8, 10) + "/" + date.substring(5, 7)));
						}
						
						avg = (total/(i+1));
						
						old = date;
					}
				// ;
				}else{
					newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL 1 MONTH)");
					newDateRes.next();
					date = newDateRes.getString(1);
					newDateRes.close();
					result3 = stmt3.executeQuery("SELECT * FROM useritems where userID = '" + UID + "' and datepurchased <= '"+ date +"' and datepurchased >= '" + result.getString("dateRec") + "'");
					while(result3.next()) {
						count ++;
					}
					System.out.println("This is the max" + count);
					for(int i=1;i<30;i++) {
						System.out.println(date);

						remainder = (result.getInt("amount")-rtotal);
						if (remainder < 0) {
							remainder = 0;
						}
						count2 = 0;
						total = 0;
						newDateRes = newDate.executeQuery("SELECT DATE_ADD('"+	result.getString("daterec")  +"',INTERVAL " + i +" day)");
						newDateRes.next();
						date = newDateRes.getString(1);
						newDateRes.close();
						result2 = stmt2.executeQuery("SELECT * FROM useritems where userID = '" + UID + "' and datepurchased <= '" + date + "' and datepurchased >= '" + result.getString("dateRec") + "'");
						
						while (result2.next()) {
							count2++;
							total = total + result2.getInt("cost");
						}
						
						if(count2 == count) {
							if(!testing) {
								dataset.addValue(remainder,"Spending future" , (old.substring(8, 10) + "/" + old.substring(5, 7)));
								testing = true;
							}
							avg = rtotal/i;
							rtotal = rtotal + avg;
							dataset.addValue(remainder,"Spending future" ,(date.substring(8, 10) + "/" + date.substring(5, 7)));
							
						}else {
							rtotal = total;
							dataset.addValue((result.getInt("amount")-total),"Spending over the week since budget" , (date.substring(8, 10) + "/" + date.substring(5, 7)));
						}
						avg = total/i;
						old = date;
					}
			}
		}} catch (SQLException e) {
			e.printStackTrace();
		}
		JFreeChart chart = ChartFactory.createLineChart("Spending by type", "Time", "Amount of money (£)", dataset);
		try {
			System.out.println(link);
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file1 = new File(link);
			ChartUtilities.saveChartAsPNG(file1, chart, 450, 300, info);
		} catch (Exception e) {
		}	
	}
	
	
	public static void makeBar(String link, int UID, int Time) throws SQLException {

		dbManager db = new dbManager();
		Connection conn = db.getConnection();
	
		Statement stmt;
		Statement stmt2;
		Statement stmt3;
		ResultSet result2;
		ResultSet result3;
		DefaultCategoryDataset  dataset =  new DefaultCategoryDataset( );  
		
		String date;
		Statement newDate = conn.createStatement();
		ResultSet newDateRes;
		String sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 WEEK)";
		if (Time == 1) {
			sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 WEEK)";
		}else if(Time == 2) {
			sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 MONTH)";
		}else if(Time == 3) {
			sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 YEAR)";
		}
		newDateRes = newDate.executeQuery(sql);
		newDateRes.next();
		date = newDateRes.getString(1);
		newDateRes.close();
		

		try {
			int NESS = 0;
			int ESS = 0;
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			ResultSet result = stmt.executeQuery("Select  Distinct type from item where usersid='" + UID + "'");
			while (result.next()) {
				ESS = 0;
				NESS = 0;
				result2 = stmt2.executeQuery("SELECT * FROM item where type ='" + result.getString("type")	+ "' and usersid = '" + UID + "'");
				while (result2.next()) {
					result3 = stmt3.executeQuery("SELECT * FROM useritems where  datepurchased <= NOW() AND itemID ='" + result2.getInt("itemId") + "' AND datepurchased >='" + date + "'");
					while (result3.next()) {
						if(result2.getInt("essential") == 1) {
							ESS = ESS + result3.getInt("cost");
						}else {
							NESS = NESS + result3.getInt("cost");
						}
					}
				}				
				System.out.println(ESS + " = ess");
				System.out.println(NESS + "= non ess");
	
				if(ESS != 0 || NESS != 0) {
					int E = 0;
					dataset.addValue(ESS,"Essential" , result.getString( "type" ));
					dataset.addValue(NESS,"Not Essential" , result.getString( "type" ));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JFreeChart chart = ChartFactory.createBarChart3D("Amount of money", "Type", "Amount of money (£)", dataset);
		try {
			System.out.println(link);
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file1 = new File(link);
			ChartUtilities.saveChartAsPNG(file1, chart, 450, 300, info);
		} catch (Exception e) {
		}	
	}
	
	public static void makeIncomePie(String link, int UID, int Time) {
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		Statement stmt;
		Statement stmt2;
		ResultSet result2;
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			ResultSet result = stmt.executeQuery("Select * from income where userid='" + UID + "'");
			int rTotal = 0;
			String date;
			Statement newDate = conn.createStatement();
			ResultSet newDateRes;
			String sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 WEEK)";
			if (Time == 1) {
				sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 WEEK)";
			}else if(Time == 2) {
				sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 MONTH)";
			}else if(Time == 3) {
				sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 YEAR)";
			}
			newDateRes = newDate.executeQuery(sql);
			newDateRes.next();
			date = newDateRes.getString(1);
			newDateRes.close();
			while (result.next()) {
				rTotal = 0;
				result2 = stmt2.executeQuery("SELECT * FROM userincome where  date <= NOW() AND incomeID ='" + result.getInt("incomeID") + "' AND date >='" + date + "'");		

				while (result2.next()) {
					rTotal = rTotal + result2.getInt("amount");
				}
				result2.close();
				
				if(rTotal != 0) {
					dataset.setValue(result.getString("incomeName"), rTotal);
				}
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFreeChart chart = ChartFactory.createPieChart3D("Spending by type", dataset, true, true, false);
		try {
			System.out.println(link);
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file1 = new File(link);
			ChartUtilities.saveChartAsPNG(file1, chart, 450, 300, info);
		} catch (Exception e) {
		}	
	}




	public static void makePie(String link, int UID, int Time) {
		dbManager db = new dbManager();
		Connection conn = db.getConnection();
		Statement stmt;
		Statement stmt2;
		ResultSet result2;
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		try {
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			ResultSet result = stmt.executeQuery("Select * from item where usersid='" + UID + "'");
			int rTotal = 0;
			String date;
			Statement newDate = conn.createStatement();
			ResultSet newDateRes;
			String sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 WEEK)";
			if (Time == 1) {
				sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 WEEK)";
			}else if(Time == 2) {
				sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 MONTH)";
			}else if(Time == 3) {
				sql = "SELECT DATE_ADD(NOW() ,INTERVAL -1 YEAR)";
			}
			newDateRes = newDate.executeQuery(sql);
			newDateRes.next();
			date = newDateRes.getString(1);
			newDateRes.close();
			while (result.next()) {
				rTotal =0;
				result2 = stmt2.executeQuery("SELECT * FROM useritems where  datepurchased <= NOW() AND itemID ='" + result.getInt("itemId") + "' AND datepurchased >='" + date + "'");		
				while (result2.next()) {
					rTotal = rTotal + result2.getInt("cost");
				}
				result2.close();
				if(rTotal != 0) {
					dataset.setValue(result.getString("itemName"), rTotal);

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFreeChart chart = ChartFactory.createPieChart3D("Income by item", dataset, true, true, false);
		try {
			System.out.println(link);
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File file1 = new File(link);
			ChartUtilities.saveChartAsPNG(file1, chart, 450, 300, info);
		} catch (Exception e) {
		}	
	}
}
