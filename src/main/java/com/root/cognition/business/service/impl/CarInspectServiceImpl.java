package com.root.cognition.business.service.impl;

import com.root.cognition.business.dao.CarInspectDao;
import com.root.cognition.business.entity.CarInspect;
import com.root.cognition.business.service.CarInspectService;
import com.root.cognition.common.config.Constant;
import com.root.cognition.common.until.DateUntils;
import com.root.cognition.modules.service.SmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author taoya
 * @date 2019-09-19 14:16:54
 */
@Service
public class CarInspectServiceImpl implements CarInspectService {

    private CarInspectDao carInspectDao;

    private SmsLogService smsLogService;


    @Autowired
    public void setCarInspectDao(CarInspectDao carInspectDao) {
        this.carInspectDao = carInspectDao;
    }

    @Autowired
    public void setSmsLogService(SmsLogService smsLogService) {
        this.smsLogService = smsLogService;
    }

    @Override
    public CarInspect get(Long id) {
        return carInspectDao.get(id);
    }

    @Override
    public List<CarInspect> list(Map<String, Object> map) {
        return carInspectDao.findList(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return carInspectDao.count(map);
    }

    @Override
    public int save(CarInspect carInspect) {
        carInspect.preInsert();
        return carInspectDao.insert(carInspect);
    }

    @Override
    public int update(CarInspect carInspect) {
        return carInspectDao.update(carInspect);
    }

    @Override
    public int remove(Long id) {
        return carInspectDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return carInspectDao.batchRemove(ids);
    }


    @Override
    public boolean sumDate(Date date, String aTypes, String aTypeNum, String realName, String carNum, String time, String mobile,Date smsTime) throws ParseException {
        //初始化变量
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String signName = "知域知识网";
        String templateCode = "SMS_174585821";
        List<String> value = new ArrayList<>();
        boolean sendSms = false;
        //判断是否到期
        String oneDate = DateUntils.getYearMonthToYYDDHHmmSS(Constant.INT_TWO, sdf.format(date), 1);
        Date od = sdf.parse(oneDate);
        if (od.after(nowTime) && (smsTime ==null || smsTime.before(nowTime))) {
            sendSms = true;
        } else {
            String threeDate = DateUntils.getYearMonthToYYDDHHmmSS(Constant.INT_TWO, sdf.format(date), 3);
            Date td = sdf.parse(threeDate);
            if (td.after(nowTime)&& (smsTime ==null ||smsTime.before(nowTime))) {
                sendSms = true;
            } else {
                String sevenDate = DateUntils.getYearMonthToYYDDHHmmSS(Constant.INT_TWO, sdf.format(date), 7);
                Date sd = sdf.parse(sevenDate);
                if (sd.after(nowTime) && smsTime == null) {
                    sendSms = true;
                }
            }
        }
        if (sendSms) {
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
            String dates = sdfs.format(date);
            value.add("name");
            value.add(realName);
            value.add("carNum");
            value.add("carNum");
            value.add("type");
            value.add(aTypes);
            value.add("typeName");
            value.add(aTypeNum);
            value.add("date");
            value.add(dates);
            String[] keyword = new String[value.size()];
            value.toArray(keyword);
            smsLogService.snedSmsMessage(Constant.service_SMS, mobile, signName, templateCode, keyword, null);
        }
        return sendSms;
    }

}
