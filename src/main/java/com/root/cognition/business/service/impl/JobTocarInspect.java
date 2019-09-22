package com.root.cognition.business.service.impl;

import com.root.cognition.business.dao.CarInspectDao;
import com.root.cognition.business.entity.CarInspect;
import com.root.cognition.business.service.CarInspectService;
import com.root.cognition.common.until.Query;
import com.root.cognition.modules.config.AlibabaSms;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Worry
 * @version 2019/9/22
 */
public class JobTocarInspect implements Job {

    private AlibabaSms alibabaSms;

    private CarInspectDao carInspectDao;

    private CarInspectService carInspectService;

    @Autowired
    public void setAlibabaSms(AlibabaSms alibabaSms) {
        this.alibabaSms = alibabaSms;
    }

    @Autowired
    public void setCarInspectDao(CarInspectDao carInspectDao) {
        this.carInspectDao = carInspectDao;
    }

    @Autowired
    public void setCarInspectService(CarInspectService carInspectService) {
        this.carInspectService = carInspectService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            alibabaSms.setConfigureAlibaba();
            Map<String, Object> param = Query.withDelFlag();
            List<CarInspect> carInspectList = carInspectDao.findList(param);
            for (CarInspect carInspect : carInspectList) {
                System.err.println("=========执行检测==========");
                //保险时间
                boolean iCar = carInspectService.sumDate(carInspect.getInsuranceTime(), "保险（强险）", carInspect.getCarInsurance(), carInspect.getRealName(), carInspect.getCarNum(), carInspect.getInsuranceTime().toString(), carInspect.getMobile(), carInspect.getInsuranceSmsTimes());
                if (iCar) {
                    carInspect.setInsuranceSmsTimes(new Date());
                }

                //车辆检查
                boolean bCar = carInspectService.sumDate(carInspect.getCarTime(), "车辆检查", carInspect.getCarNum(), carInspect.getRealName(), carInspect.getCarNum(), carInspect.getCarTime().toString(), carInspect.getMobile(), carInspect.getCarSmsTimes());
                if (bCar) {
                    carInspect.setCarTime(new Date());
                }

                //驾驶证
                boolean vCar = carInspectService.sumDate(carInspect.getVehicleTime(), "驾驶证", carInspect.getVehicleLicense(), carInspect.getRealName(), carInspect.getCarNum(), carInspect.getVehicleTime().toString(), carInspect.getMobile(), carInspect.getVehicleSmsTimes());
                if (vCar) {
                    carInspect.setVehicleTime(new Date());
                }
                carInspectDao.update(carInspect);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
