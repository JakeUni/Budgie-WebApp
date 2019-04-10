package com.eigdub;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class getCharts extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req.getParameter("link"));

		int UID = Integer.parseInt(req.getParameter("UID"));
		int date = Integer.parseInt(req.getParameter("Date"));
		String link = req.getParameter("link").replace("" + (char) 132," ");
		System.out.println(link);
		System.out.println(link + "/piechart2.png");
		
		chartMake.makePie(link + "/piechart2.png" ,UID,date) ;
    	try {
			chartMake.makeBar(link  + "/bar1.png" ,UID,date) ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	chartMake.makeLine(link + "/line1.png" ,UID,date) ;
    	chartMake.makeIncomePie(link + "/incPie.png" ,UID,date) ;
		res.sendRedirect("chart.jsp");

	}

}
