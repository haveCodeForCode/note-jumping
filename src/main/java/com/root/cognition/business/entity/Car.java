package com.root.cognition.business.entity;

import com.root.cognition.system.persistence.BaseEntity;

import java.io.Serializable;


/**
 * 车辆表
 *
 * @author 1122
 * @date 2019-09-19 14:16:54
 */
public class Car extends BaseEntity<Car> implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 车主姓名*/
    private String realName;
    /*** 身份证号*/
    private String identityNum;
    /*** 车主联系电话*/
    private String mobile;
    /*** 车牌号*/
    private String carNum;
    /*** 车辆类型*/
    private String carType;
    /*** 归属账号*/
    private String userId;
    /*** 备注*/
    private String remarks;

    /**
     * 设置：车主姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取：车主姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置：身份证号
     */
    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    /**
     * 获取：身份证号
     */
    public String getIdentityNum() {
        return identityNum;
    }

    /**
     * 设置：车主联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：车主联系电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：车牌号
     */
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    /**
     * 获取：车牌号
     */
    public String getCarNum() {
        return carNum;
    }

    /**
     * 设置：车辆类型
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * 获取：车辆类型
     */
    public String getCarType() {
        return carType;
    }

    /**
     * 设置：归属账号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取：归属账号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置：备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取：备注
     */
    public String getRemarks() {
        return remarks;
    }
}
