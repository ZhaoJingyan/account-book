package com.ab.controller;

/**
 * һ��Model��������Ϣ��
 * 
 * @author Zhao Jinyan
 *
 */
public class ModelConfiguartion {

    public ModelConfiguartion(String id, String clazz) {
	this.id = id;
	this.clazz = clazz;
    }

    private String id;
    private String clazz;

    public String getId() {
	return this.id;
    }

    public String getClazz() {
	return this.clazz;
    }
}
