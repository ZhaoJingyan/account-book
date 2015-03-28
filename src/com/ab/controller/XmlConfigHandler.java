package com.ab.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX读取句柄。
 * 
 * @author Zhao Jinyan
 *
 */
class XmlConfigHandler extends DefaultHandler {

    public static final String model = "model";
    public static final String id = "id";
    public static final String clazz = "class";
    public static final String model_mapping = "model-mapping";
    public static final String url = "url";
    public static final String models = "models";

    private ModelConfiguartion tempModelConfiguartion;
    private Map<String, ModelConfiguartion> modelConfiguartions;

    private ModelMappingConfiguartion tempModelMappingConfiguartion;
    private Map<String, ModelMappingConfiguartion> modelMappingConfiguartions;

    private boolean tempUrl;

    public Map<String, ModelMappingConfiguartion> getModelMappingConfiguartions() {
	return modelMappingConfiguartions;
    }

    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {
	if (tempUrl) {
	    String context = new String(ch, start, length).trim();
	    tempModelMappingConfiguartion.setUrl(context);
	}
    }

    @Override
    public void endDocument() throws SAXException {
	Set<String> urls = modelMappingConfiguartions.keySet();

	for (String url : urls) {
	    String id = modelMappingConfiguartions.get(url).getId();
	    ModelConfiguartion modelConfiguartion = modelConfiguartions.get(id);
	    if (modelConfiguartion == null)
		throw new SAXException(id + ":<model-mapping>无对应的<model>节点");
	    else
		modelMappingConfiguartions.get(url).setModelConfiguartion(
			modelConfiguartion);
	}
    }

    /**
     * 读取结束文件。
     */
    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	if (qName.equals(model) && tempModelConfiguartion != null) {
	    if (modelConfiguartions.get(tempModelConfiguartion.getId()) != null)
		throw new SAXException(tempModelConfiguartion.getId()
			+ ": model.id重复设置");
	    modelConfiguartions.put(tempModelConfiguartion.getId(),
		    tempModelConfiguartion);
	    tempModelConfiguartion = null;
	} else if (qName.equals(model_mapping)
		&& tempModelMappingConfiguartion != null) {
	    if (modelMappingConfiguartions.get(tempModelMappingConfiguartion
		    .getUrl()) != null)
		throw new SAXException(tempModelMappingConfiguartion.getUrl()
			+ ":URL重复定义");
	    modelMappingConfiguartions.put(
		    tempModelMappingConfiguartion.getUrl(),
		    tempModelMappingConfiguartion);
	    tempModelMappingConfiguartion = null;
	} else if (qName.equals(url)) {
	    tempUrl = false;
	}
    }

    /**
     * 读取xml文件前调用。
     */
    @Override
    public void startDocument() throws SAXException {
	modelConfiguartions = new HashMap<String, ModelConfiguartion>();
	modelMappingConfiguartions = new HashMap<String, ModelMappingConfiguartion>();
	tempUrl = false;
    }

    /**
     * 获取开始元素。
     */
    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes attributes) throws SAXException {
	if (qName.equals(models)) {
	    tempModelMappingConfiguartion = null;
	    tempModelConfiguartion = null;
	} else if (qName.equals(model) && tempModelConfiguartion == null
		&& tempModelMappingConfiguartion == null) {
	    tempModelConfiguartion = newModelConfiguartion(attributes);
	} else if (qName.equals(model_mapping)
		&& tempModelConfiguartion == null
		&& tempModelMappingConfiguartion == null) {
	    tempModelMappingConfiguartion = newModelMappingConfiguartion(attributes);
	} else if (qName.equals(url) && tempModelMappingConfiguartion != null) {
	    tempUrl = true;
	} else {
	    throw new SAXException(qName + ":xml文件结构错误");
	}
    }

    /**
     * 根据model节点创建ModelConfiguartion。
     * 
     * @param attributes model节点的属性
     * @return model节点信息
     * @throws SAXException xml格式异常
     */
    private final ModelConfiguartion newModelConfiguartion(Attributes attributes)
	    throws SAXException {
	String id = attributes.getValue(XmlConfigHandler.id);
	String clazz = attributes.getValue(XmlConfigHandler.clazz);

	if (id == null)
	    throw new SAXException("<model>节点id属性没有设置");
	if (clazz == null)
	    throw new SAXException("<model>节点class属性没有设置");

	ModelConfiguartion modelConfiguartion = new ModelConfiguartion(id,
		clazz);
	return modelConfiguartion;
    }

    /**
     * 根据model-mapping节点创建ModelMappingConfiguartion。
     * 
     * @param attributes model-mapping节点的属性
     * @return model-mapping节点信息
     * @throws SAXException xml格式异常
     */
    private final ModelMappingConfiguartion newModelMappingConfiguartion(
	    Attributes attributes) throws SAXException {
	String id = attributes.getValue(XmlConfigHandler.id);

	if (id == null)
	    throw new SAXException("<model-mapping>节点id属性没有设置");

	ModelMappingConfiguartion modelMappingConfiguartion = new ModelMappingConfiguartion(
		id);
	return modelMappingConfiguartion;
    }
}
