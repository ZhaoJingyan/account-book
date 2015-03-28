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
 * ��ȡ�����ļ���
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
	 * Ĭ�Ϲ��캯����
	 * 
	 * @throws ParserConfigurationException
	 *             �����쳣
	 * @throws SAXException
	 *             XML�ĵ��쳣
	 * @throws IOException
	 *             IO�쳣
	 */
	public XmlConfigBuilder() throws ParserConfigurationException,
			SAXException, IOException {
		this(AccountBookConfiguration.getControllerConfiguration());
	}

	/**
	 * ͨ��XML�ļ�·�����졣
	 * 
	 * @param path
	 *            XML�ļ�·��
	 * @throws ParserConfigurationException
	 *             �����쳣
	 * @throws SAXException
	 *             XML�ĵ��쳣
	 * @throws IOException
	 *             IO�쳣
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
	 * ��ȡXML�ļ�������Ϣ��
	 * 
	 * @return XML�ļ�������Ϣ
	 */
	public ControllerConfiguration parse() {
		return new ControllerConfiguration(modelMappingConfiguartions);
	}
}
