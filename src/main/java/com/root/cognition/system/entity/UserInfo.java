package com.root.cognition.system.entity;

import com.root.cognition.system.persistence.DataEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author LineInkBook
 * @version 2018/12/24
 */
public class UserInfo extends DataEntity<UserInfo> {

    private static final long serialVersionUID = 1L;

    public UserInfo(String id) {
        super(id);
    }

    /**
     * 性别
     * sex
     */
    private Long sex;
    /**
     * 出身日期
     * birth
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    /**
     * 图片ID
     * picId
     */
    private String picId;
    /**
     * 居住地
     * liveAddress
     */
    private String liveAddress;
    /**
     * 爱好
     * hobby
     */
    private String hobby;
    /**
     * 所住省份
     * province
     */
    private String province;
    /**
     * 所在城市
     * city
     */
    private String city;
    /**
     * 所在地区
     * district
     */
    private String district;

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
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
                "sex=" + sex +
                ", birth=" + birth +
                ", picId='" + picId + '\'' +
                ", liveAddress='" + liveAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
