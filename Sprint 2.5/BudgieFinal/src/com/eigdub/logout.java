package com.eigdub;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
		    Cookie cookie = new Cookie("user", "");
	        cookie.setMaxAge(0);
	        res.addCookie(cookie);
			
		} 		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		PrintWriter out= res.getWriter();
		out.println("<font color=purple>Logged Out.</font>");
		rd.include(req, res);
		return;
		
	}

}
