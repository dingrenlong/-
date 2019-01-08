package com.drl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="TestServlet",urlPatterns="/testservlet")
public class TestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6315747458768560717L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException
	    {
			System.out.println("doGet");
	    }
}
