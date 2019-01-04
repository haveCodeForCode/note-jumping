/**
 * 
 */
package com.root.cognition.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.google.common.collect.Maps;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.Map;

/**
 * Entity支持类
 * @author 
 * @version 2014-05-16
 */
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT  = "2";

	/**
	 * 实体编号（唯一标识）
	 * @ id
	 */
	protected String id;

	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 * @ sqlMap
	 */
	protected Map<String, String> sqlMap;

	public BaseEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
