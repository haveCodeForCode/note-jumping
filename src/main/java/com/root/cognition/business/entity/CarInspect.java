package com.root.cognition.business.entity;

import com.root.cognition.system.persistence.BaseEntity;

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
	private Date carTime;
	/**
	 * 行驶证
	 */
	private String vehicleLicense;
	/**
	 * 行驶证检时间
	 */
	private Date vehicleTime;
	/**
	 * 保险（强险）
	 */
	private String carInsurance;
	/**
	 * 保险（强险）检时间
	 */
	private Date insuranceTime;


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

}
