package com.notejumping.system.entity;

import com.notejumping.system.persistence.BaseEntity;

/**
 * 用户信息
 * @author LineInkBook
 * @version 2018/12/24
 */
public class UserInfo extends BaseEntity<UserInfo>{

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     * <p>
     * userId
     */
    private Long userId;

    /**
     * 用户名
     * <p>
     * name
     */
    private String userName;

    /**
     * 图片ID
     * <p>
     * picId
     */
    private String picUrl;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", id=" + id +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
