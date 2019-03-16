/**
 *
 */
package com.root.cognition.common.persistence;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * Entity支持类
 * @author warry
 * @version 2014-05-16
 */
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     *
     * @ DEL_FLAG
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

    /**
     * 实体编号（唯一标识）
     *
     * @ id
     */
    protected String id;
    /**
     * 创建者
     * <p>
     * createBy
     */
    protected String createBy;

    /**
     * 更新者
     * <p>
     * updateBy
     */
    protected String updateBy;

    /**
     * 创建日期
     * <p>
     * gmtCreate
     */
    protected Date createTime;

    /**
     * 更新日期
     * <p>
     * UpdateTime
     */
    protected Date updateTime;

    /**
     * 删除标记（bootstrap：正常；1：删除；2：审核）
     * <p>
     * delFlag
     */
    protected String delFlag;

    /**
     * 查询标记
     * <p>
     * searchinfo
     */
    protected String searchInfo;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     *
     * @ sqlMap
     */
    protected Map<String, String> sqlMap;

    public BaseEntity(String id) {
        this.id = id;
    }

    public BaseEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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


    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    protected boolean isNewRecord = false;

//	@JsonIgnore
//	@XmlTransient
//	public Map<String, String> getSqlMap() {
//		if (sqlMap == null){
//			sqlMap = Maps.newHashMap();
//		}
//		return sqlMap;
//	}

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

}
