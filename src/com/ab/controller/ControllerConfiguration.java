package com.ab.controller;

import java.util.Map;
import java.util.Set;

/**
 * Controller �����ļ���
 * 
 * @author Zhao Jinyan
 *
 */
class ControllerConfiguration {

    /**
     * ���췽����
     * 
     * @param modelMappingConfiguartions
     */
    public ControllerConfiguration(
	    Map<String, ModelMappingConfiguartion> modelMappingConfiguartions) {
	this.modelMappingConfiguartions = modelMappingConfiguartions;
    }

    private Map<String, ModelMappingConfiguartion> modelMappingConfiguartions;

    public String getModelClassName(String url) {
	ModelMappingConfiguartion modelMappingConfiguartion = modelMappingConfiguartions
		.get(url);
	if (modelMappingConfiguartion == null) {
	    return null;
	}
	return modelMappingConfiguartion.getModelClassName();
    }

    public Set<String> getAllUrls() {
	return modelMappingConfiguartions.keySet();
    }
}
