package com.ab.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.ab.resources.AccountBookConfiguration;

/**
 * 读取配置文件。
 * 
 * @author Zhao
 *
 */
class XmlConfigBuilder {

	public static XmlConfigBuilder getXmlConfigBuilder(String path)
			throws ParserConfigurationException, SAXException, IOException {
		return new XmlConfigBuilder(path);
	}

	public static XmlConfigBuilder getXmlConfigBuilder()
			throws ParserConfigurationException, SAXException, IOException {
		return new XmlConfigBuilder();
	}

	/**
	 * 默认构造函数。
	 * 
	 * @throws ParserConfigurationException
	 *             解析异常
	 * @throws SAXException
	 *             XML文档异常
	 * @throws IOException
	 *             IO异常
	 */
	public XmlConfigBuilder() throws ParserConfigurationException,
			SAXException, IOException {
		this(AccountBookConfiguration.getControllerConfiguration());
	}

	/**
	 * 通过XML文件路径构造。
	 * 
	 * @param path
	 *            XML文件路径
	 * @throws ParserConfigurationException
	 *             解析异常
	 * @throws SAXException
	 *             XML文档异常
	 * @throws IOException
	 *             IO异常
	 */
	public XmlConfigBuilder(String path) throws ParserConfigurationException,
			SAXException, IOException {
		InputStream inputStream = XmlConfigBuilder.class.getClassLoader()
				.getResourceAsStream(path);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XmlConfigHandler handler = new XmlConfigHandler();
		parser.parse(inputStream, handler);
		modelMappingConfiguartions = handler.getModelMappingConfiguartions();
	}

	private Map<String, ModelMappingConfiguartion> modelMappingConfiguartions;

	/**
	 * 获取XML文件配置信息。
	 * 
	 * @return XML文件配置信息
	 */
	public ControllerConfiguration parse() {
		return new ControllerConfiguration(modelMappingConfiguartions);
	}
}
