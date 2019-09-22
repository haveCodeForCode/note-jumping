package com.root.cognition.business.service;

import com.root.cognition.business.entity.CarInspect;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 1122
 * @date 2019-09-19 14:16:54
 */
public interface CarInspectService {

    CarInspect get(Long id);

    List<CarInspect> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CarInspect carInspect);

    int update(CarInspect carInspect);

    int remove(Long id);

    int batchRemove(Long[] ids);

    boolean sumDate(Date insuranceTime, String s, String carInsurance, String realName, String carNum, String toString, String mobile,Date SmsTime) throws ParseException;
}
