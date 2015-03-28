package com.ab.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX��ȡ�����
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
		throw new SAXException(id + ":<model-mapping>�޶�Ӧ��<model>�ڵ�");
	    else
		modelMappingConfiguartions.get(url).setModelConfiguartion(
			modelConfiguartion);
	}
    }

    /**
     * ��ȡ�����ļ���
     */
    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	if (qName.equals(model) && tempModelConfiguartion != null) {
	    if (modelConfiguartions.get(tempModelConfiguartion.getId()) != null)
		throw new SAXException(tempModelConfiguartion.getId()
			+ ": model.id�ظ�����");
	    modelConfiguartions.put(tempModelConfiguartion.getId(),
		    tempModelConfiguartion);
	    tempModelConfiguartion = null;
	} else if (qName.equals(model_mapping)
		&& tempModelMappingConfiguartion != null) {
	    if (modelMappingConfiguartions.get(tempModelMappingConfiguartion
		    .getUrl()) != null)
		throw new SAXException(tempModelMappingConfiguartion.getUrl()
			+ ":URL�ظ�����");
	    modelMappingConfiguartions.put(
		    tempModelMappingConfiguartion.getUrl(),
		    tempModelMappingConfiguartion);
	    tempModelMappingConfiguartion = null;
	} else if (qName.equals(url)) {
	    tempUrl = false;
	}
    }

    /**
     * ��ȡxml�ļ�ǰ���á�
     */
    @Override
    public void startDocument() throws SAXException {
	modelConfiguartions = new HashMap<String, ModelConfiguartion>();
	modelMappingConfiguartions = new HashMap<String, ModelMappingConfiguartion>();
	tempUrl = false;
    }

    /**
     * ��ȡ��ʼԪ�ء�
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
	    throw new SAXException(qName + ":xml�ļ��ṹ����");
	}
    }

    /**
     * ����model�ڵ㴴��ModelConfiguartion��
     * 
     * @param attributes model�ڵ������
     * @return model�ڵ���Ϣ
     * @throws SAXException xml��ʽ�쳣
     */
    private final ModelConfiguartion newModelConfiguartion(Attributes attributes)
	    throws SAXException {
	String id = attributes.getValue(XmlConfigHandler.id);
	String clazz = attributes.getValue(XmlConfigHandler.clazz);

	if (id == null)
	    throw new SAXException("<model>�ڵ�id����û������");
	if (clazz == null)
	    throw new SAXException("<model>�ڵ�class����û������");

	ModelConfiguartion modelConfiguartion = new ModelConfiguartion(id,
		clazz);
	return modelConfiguartion;
    }

    /**
     * ����model-mapping�ڵ㴴��ModelMappingConfiguartion��
     * 
     * @param attributes model-mapping�ڵ������
     * @return model-mapping�ڵ���Ϣ
     * @throws SAXException xml��ʽ�쳣
     */
    private final ModelMappingConfiguartion newModelMappingConfiguartion(
	    Attributes attributes) throws SAXException {
	String id = attributes.getValue(XmlConfigHandler.id);

	if (id == null)
	    throw new SAXException("<model-mapping>�ڵ�id����û������");

	ModelMappingConfiguartion modelMappingConfiguartion = new ModelMappingConfiguartion(
		id);
	return modelMappingConfiguartion;
    }
}
