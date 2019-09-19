package com.root.cognition.business.entity;

/**
 * @author Worry
 * @version 2019/9/19
 */
public class CarUser {

    private Long userCarId;
    private Long userId;
    private Long carId;
    private String delFlag;

//****************************

    public Long getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(Long userCarId) {
        this.userCarId = userCarId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


    @Override
    public String toString() {
        return "CarUser{" +
                "userCarId=" + userCarId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }

}
