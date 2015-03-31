package com.ab.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public abstract class Model {
    
    public static final String application_json = "application/json";
    
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
    protected Gson gson;

    /**
     * ��ת��jspҳ�档
     * 
     * @param path jspҳ��·��
     */
    protected void gotoJsp(String path) {

    }

    /**
     * ������ת��ΪModel
     */
    protected void gotoJson() {
	gotoJson(this);
    }

    protected void gotoJson(Object object) {
	setContentType(application_json);
	writer.print(gson.toJson(object));
    }

    protected final void setContentType(String contentType){
	response.setContentType(contentType);
    }
    
    public void _setRequest(HttpServletRequest request) {
	this.request = request;
	session = request.getSession();
    }

    public void _setResponse(HttpServletResponse response) throws IOException {
	this.response = response;
	writer = response.getWriter();
    }

    public void _setGson(Gson gson){
	this.gson = gson;
    }
}
