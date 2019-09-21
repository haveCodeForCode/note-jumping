package com.root.cognition.business.service.impl;

import com.root.cognition.business.dao.CarInspectDao;
import com.root.cognition.business.entity.CarInspect;
import com.root.cognition.business.service.CarInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author taoya
 * @date 2019-09-19 14:16:54
 */
@Service
public class CarInspectServiceImpl implements CarInspectService {

    private CarInspectDao carInspectDao;

    @Autowired
    public void setCarInspectDao(CarInspectDao carInspectDao) {
        this.carInspectDao = carInspectDao;
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

}
