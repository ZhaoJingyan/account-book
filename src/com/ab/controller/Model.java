package com.ab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class Model {
	public void init(){
		
	}
	
	public void get() throws ServletException, IOException{}
	
	public void post() throws ServletException, IOException{}
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		session = request.getSession();
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
