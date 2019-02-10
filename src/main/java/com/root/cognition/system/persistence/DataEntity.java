package com.root.cognition.system.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 数据Entity类
 *
 * @author
 * @version 2014-05-16
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者
     *
     * @createBy
     */
    protected String creator;
    /**
     * 更新者
     *
     * @updateBy
     */
    protected String updater;
    /**
     * 创建日期
     *
     * @gmtCreate
     */
    protected Date gmtCreate;
    /**
     * 更新日期
     *
     * @gmtModified
     */
    protected Date gmtModified;
    /**
     * 删除标记（bootstrap：正常；1：删除；2：审核）
     *
     * @delFlag
     */
    protected String delFlag;
    /**
     * 查询标记
     *
     * @searchinfo
     */
    protected String searchInfo;

    public DataEntity(String id) {
        super(id);
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(String searchInfo) {
        this.searchInfo = searchInfo;
    }

    @Length(min = 1, max = 1)
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
