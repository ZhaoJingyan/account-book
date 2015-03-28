package com.ab.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class Model {

    /**
     * ��ʼ����
     */
    public void init() {

    }

    /**
     * ҳ��GET����
     * 
     * @throws ServletException
     * @throws IOException
     */
    public void get() throws ServletException, IOException {
    }

    /**
     * ҳ��POST����
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
     * ��ת��jspҳ�档
     * 
     * @param path jspҳ��·��
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
