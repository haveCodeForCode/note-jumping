package com.root.cognition.business.service;


import com.root.cognition.business.entity.Car;

import java.util.List;
import java.util.Map;

/**
 * 车辆表
 *
 * @author 1122
 * @date 2019-09-19 14:16:54
 */
public interface CarService {

    Car get(Long id);

    List<Car> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Car car);

    int update(Car car);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
