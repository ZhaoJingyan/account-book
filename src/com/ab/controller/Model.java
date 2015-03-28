package com.ab.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class Model {

    /**
     * 初始换。
     */
    public void init() {

    }

    /**
     * 页面GET请求。
     * 
     * @throws ServletException
     * @throws IOException
     */
    public void get() throws ServletException, IOException {
    }

    /**
     * 页面POST请求。
     * 
     * @throws ServletException
     * @throws IOException
     */
    public void post() throws ServletException, IOException {
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected PrintWriter writer;

    /**
     * 跳转到jsp页面。
     * 
     * @param path jsp页面路径
     */
    protected void gotoJsp(String path) {

    }

    protected void gtoJson() {
	gotoJson(this);
    }

    protected void gotoJson(Object object) {

    }

    public void setRequest(HttpServletRequest request) {
	this.request = request;
	session = request.getSession();
    }

    public void setResponse(HttpServletResponse response) throws IOException {
	this.response = response;
	writer = response.getWriter();
    }

}
