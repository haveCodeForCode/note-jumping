package com.root.cognition.modules.entity;

import com.root.cognition.system.persistence.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public class FileRecord extends BaseEntity<FileRecord> implements Serializable {

    /*** 文件类型*/
    private Integer type;
    /***  URL地址*/
    private String url;


    public FileRecord() {
        super();
    }

    public FileRecord(Integer type, String url, Date createDate) {
        super();
        this.type = type;
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
