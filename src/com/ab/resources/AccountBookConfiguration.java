package com.ab.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ¶ÁÈ¡AccountBook.properties£¬´æ´¢Account BookÎÄ¼þ¡£
 * 
 * @author Zhao Jinyan
 *
 */
public class AccountBookConfiguration {
	static {
		InputStream inputStream = AccountBookConfiguration.class
				.getClassLoader().getResourceAsStream("AccountBook.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			dBConfiguration = properties.getProperty(AccountBookConfigurationKey.DBConfiguration);
			properties.clone();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String dBConfiguration;
	
	public static String getDBConfiguration(){
		return dBConfiguration;
	}
}
