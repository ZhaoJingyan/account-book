package com.ab.controller;

import java.util.Map;
import java.util.Set;

/**
 * Controller 配置文件。
 * 
 * @author Zhao Jinyan
 *
 */
class ControllerConfiguration {

    /**
     * 构造方法。
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
