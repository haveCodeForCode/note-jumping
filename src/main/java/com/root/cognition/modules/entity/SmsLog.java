package com.root.cognition.modules.entity;


import com.root.cognition.common.persistence.BaseEntity;

import java.io.Serializable;
import java.util.Date;



/**
 * 短息日志表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-15 15:54:43
 */
public class SmsLog extends BaseEntity<SmsLog> implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 模块
	 */
	private String module;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 是否推送（0草稿；1：发送成功
	 */
	private String ispush;
	/**
	 * 备注
	 */
	private String remake;
	/**
	 * 模版ID
	 */
	private String templatecode;


	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIspush() {
		return ispush;
	}

	public void setIspush(String ispush) {
		this.ispush = ispush;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getTemplatecode() {
		return templatecode;
	}

	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}

	@Override
	public String toString() {
		return "SmsLog{" +
				"module='" + module + '\'' +
				", mobile='" + mobile + '\'' +
				", content='" + content + '\'' +
				", ispush='" + ispush + '\'' +
				", remake='" + remake + '\'' +
				", templatecode='" + templatecode + '\'' +
				", id=" + id +
				", createBy=" + createBy +
				", updateBy=" + updateBy +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", delFlag='" + delFlag + '\'' +
				'}';
	}
}
