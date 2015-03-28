package com.ab.model;

import java.io.IOException;

import javax.servlet.ServletException;

import com.ab.controller.Model;

public class AccountModel extends Model {

	@Override
	public void post() throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		writer.println(name);
	}

	@Override
	public void get() throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		writer.println("<h1>Test AccountModel<GET> !!! </h1>");
	}

	private String name;
	
	public void setName(String name){
		this.name = name;
	}
}
