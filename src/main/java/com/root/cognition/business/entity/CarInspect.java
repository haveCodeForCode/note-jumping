package com.root.cognition.business.entity;

import com.root.cognition.system.persistence.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-19 14:16:54
 */
public class CarInspect extends BaseEntity<CarInspect> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 车辆id
	 */
	private Long carId;
	/**
	 * 外观时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date carTime;
	/**
	 * 行驶证
	 */
	private String vehicleLicense;
	/**
	 * 行驶证检时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date vehicleTime;
	/**
	 * 保险（强险）
	 */
	private String carInsurance;
	/**
	 * 保险（强险）检时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date insuranceTime;

	/**
	 * 车检更新时间
	 **/
	private Date carSmsTimes;

	/**
	 * 驾驶证更新时间
	 **/
	private Date vehicleSmsTimes;

	/**
	 * 保险检更新时间
	 */
	private Date insuranceSmsTimes;

//*************************车辆相关数据******************************
	/*** 车牌号*/
	private String carNum;

	/*** 车主联系电话*/
	private String mobile;

	/*** 车主姓名*/
	private String realName;

	/**
	 * 设置：车辆id
	 */
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	/**
	 * 获取：车辆id
	 */
	public Long getCarId() {
		return carId;
	}
	/**
	 * 设置：外观时间
	 */
	public void setCarTime(Date carTime) {
		this.carTime = carTime;
	}
	/**
	 * 获取：外观时间
	 */
	public Date getCarTime() {
		return carTime;
	}
	/**
	 * 设置：行驶证
	 */
	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}
	/**
	 * 获取：行驶证
	 */
	public String getVehicleLicense() {
		return vehicleLicense;
	}
	/**
	 * 设置：行驶证检时间
	 */
	public void setVehicleTime(Date vehicleTime) {
		this.vehicleTime = vehicleTime;
	}
	/**
	 * 获取：行驶证检时间
	 */
	public Date getVehicleTime() {
		return vehicleTime;
	}
	/**
	 * 设置：保险（强险）
	 */
	public void setCarInsurance(String carInsurance) {
		this.carInsurance = carInsurance;
	}
	/**
	 * 获取：保险（强险）
	 */
	public String getCarInsurance() {
		return carInsurance;
	}
	/**
	 * 设置：保险（强险）检时间
	 */
	public void setInsuranceTime(Date insuranceTime) {
		this.insuranceTime = insuranceTime;
	}
	/**
	 * 获取：保险（强险）检时间
	 */
	public Date getInsuranceTime() {
		return insuranceTime;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public void setCarSmsTimes(Date carSmsTimes) {
		this.carSmsTimes = carSmsTimes;
	}

	public void setVehicleSmsTimes(Date vehicleSmsTimes) {
		this.vehicleSmsTimes = vehicleSmsTimes;
	}

	public void setInsuranceSmsTimes(Date insuranceSmsTimes) {
		this.insuranceSmsTimes = insuranceSmsTimes;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getCarSmsTimes() {
		return carSmsTimes;
	}

	public Date getVehicleSmsTimes() {
		return vehicleSmsTimes;
	}

	public Date getInsuranceSmsTimes() {
		return insuranceSmsTimes;
	}

	@Override
	public String toString() {
		return "CarInspect{" +
				"carId=" + carId +
				", carTime=" + carTime +
				", vehicleLicense='" + vehicleLicense + '\'' +
				", vehicleTime=" + vehicleTime +
				", carInsurance='" + carInsurance + '\'' +
				", insuranceTime=" + insuranceTime +
				", carSmsTimes=" + carSmsTimes +
				", vehicleSmsTimes=" + vehicleSmsTimes +
				", insuranceSmsTimes=" + insuranceSmsTimes +
				", carNum='" + carNum + '\'' +
				", mobile='" + mobile + '\'' +
				", realName='" + realName + '\'' +
				", id=" + id +
				", createBy=" + createBy +
				", updateBy=" + updateBy +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", delFlag='" + delFlag + '\'' +
				'}';
	}
}
