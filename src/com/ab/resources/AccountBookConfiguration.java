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
	    dBConfiguration = properties
		    .getProperty(AccountBookConfigurationKey.DBConfiguration);
	    webPort = Integer.valueOf(properties
		    .getProperty(AccountBookConfigurationKey.WebPort));
	    webDescriptor = properties
		    .getProperty(AccountBookConfigurationKey.WebDescriptor);
	    webResourceBase = properties
		    .getProperty(AccountBookConfigurationKey.WebResourceBase);
	    controllerConfiguration = properties
		    .getProperty(AccountBookConfigurationKey.ControllerConfiguration);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static String dBConfiguration;
    private static int webPort;
    private static String webDescriptor;
    private static String webResourceBase;
    private static String controllerConfiguration;

    public static String getDBConfiguration() {
	return dBConfiguration;
    }

    public static int getWebPort() {
	return webPort;
    }

    public static String getWebDescriptor() {
	return webDescriptor;
    }

    public static String getWebResourceBase() {
	return webResourceBase;
    }

    public static String getControllerConfiguration() {
	return controllerConfiguration;
    }
}
