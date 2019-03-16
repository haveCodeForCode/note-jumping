package com.root.cognition.system.entity;

import com.root.cognition.common.persistence.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户信息
 * @author LineInkBook
 * @version 2018/12/24
 */
public class UserInfo extends BaseEntity<UserInfo>{

    private static final long serialVersionUID = 1L;

    public UserInfo(String id) {
        super(id);
    }

    /**
     * 用户关联字段
     */
    private String userId;

    /**
     * 性别
     * <p>
     * sex
     */
    private char sex;

    /**
     * 出身日期
     * <p>
     * birth
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 图片ID
     * <p>
     * picId
     */
    private String picId;

    /**
     * 所住省份
     * <p>
     * province
     */
    private String province;

    /**
     * 所在城市
     * <p>
     * city
     */
    private String city;

    /**
     * 所在地区
     * <p>
     * district
     */
    private String district;

    /**
     * 居住地
     * <p>
     * liveAddress
     */
    private String liveAddress;

    /**
     * 爱好
     * <p>
     * hobby
     */
    private String hobby;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                ", picId='" + picId + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", liveAddress='" + liveAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
