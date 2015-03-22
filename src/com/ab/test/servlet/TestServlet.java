package com.ab.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer =  response.getWriter();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<p>RequestURI:").append(request.getRequestURL()).append("</p>");
		
		Map<String, String[]> map = request.getParameterMap();
		
		Set<String> keys = map.keySet();
		
		for(String key : keys){
			buffer.append("<p>");
			buffer.append(key);
			buffer.append("=");
			String [] values = map.get(key);
			int index = 0;
			for(String value : values){
				index++;
				buffer.append(value);
				if(values.length != index){
					buffer.append(",");
				}
			}
			buffer.append("</p>");
		}
		
		writer.println(buffer);
		
	}

}
