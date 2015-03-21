package com.ab;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.ab.resources.AccountBookConfiguration;

public class WebStart {

	public static void main(String[] args) {
		Server server = new Server(AccountBookConfiguration.getWebPort());
		
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setDescriptor(AccountBookConfiguration.getWebDescriptor());
		context.setResourceBase(AccountBookConfiguration.getWebResourceBase());
		context.setParentLoaderPriority(true);
		
		System.out.println(AccountBookConfiguration.getWebDescriptor());
		System.out.println(AccountBookConfiguration.getWebResourceBase());
		
		server.setHandler(context);
		
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
