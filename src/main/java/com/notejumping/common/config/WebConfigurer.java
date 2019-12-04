package com.notejumping.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
class WebConfigurer implements WebMvcConfigurer {

	private ProjectConfig projectConfig;

	@Autowired
	public void setProjectConfig(ProjectConfig projectConfig) {
		this.projectConfig = projectConfig;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploaded_files/**").addResourceLocations("file:"+projectConfig.getUploadPath());
	}

}