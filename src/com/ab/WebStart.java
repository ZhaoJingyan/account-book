package com.ab;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.ab.resources.AccountBookConfiguration;

/**
 * 启动Jetty嵌入式服务器。
 * 
 * @author Zhao Jinyan
 *
 */
public class WebStart {

	public static void main(String[] args) {
		Server server = new Server(AccountBookConfiguration.getWebPort());
		
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setDescriptor(AccountBookConfiguration.getWebDescriptor());
		context.setResourceBase(AccountBookConfiguration.getWebResourceBase());
		context.setParentLoaderPriority(true);
		server.setHandler(context);
		
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}