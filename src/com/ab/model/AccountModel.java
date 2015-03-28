package com.ab.model;

import java.io.IOException;

import javax.servlet.ServletException;

import com.ab.controller.Model;

public class AccountModel extends Model {

	@Override
	public void post() throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("<h1>Test AccountModel !!! </h1>");
	}

	@Override
	public void get() throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("<h1>Test AccountModel !!! </h1>");
	}

	
}
