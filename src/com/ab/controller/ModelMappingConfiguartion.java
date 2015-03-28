package com.ab.controller;

/**
 * model-mappingµƒ≈‰÷√–≈œ¢°£
 * 
 * @author Zhao
 *
 */
class ModelMappingConfiguartion {
    public ModelMappingConfiguartion(String id) {
	this.id = id;
    }

    private String id;
    private String url;
    private ModelConfiguartion modelConfiguartion;

    public String getId() {
	return this.id;
    }

    public String getUrl() {
	return this.url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public void setModelConfiguartion(ModelConfiguartion modelConfiguartion) {
	this.modelConfiguartion = modelConfiguartion;
    }

    public String getModelClassName() {
	return modelConfiguartion.getClazz();
    }
}
