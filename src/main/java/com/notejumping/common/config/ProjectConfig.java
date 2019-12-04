package com.notejumping.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Worry
 * @version 2019/9/13
 */
@Component
@ConfigurationProperties(prefix="cognition")
public class ProjectConfig {

    /*** 上传路径*/
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
