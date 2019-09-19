package com.root.cognition.business.service.impl;

import com.root.cognition.business.dao.CarDao;
import com.root.cognition.business.entity.Car;
import com.root.cognition.business.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class CarServiceImpl implements CarService {
	
	private CarDao carDao;
	
	@Autowired
	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}

	@Override
	public Car get(Long id){
		return carDao.get(id);
	}
	
	@Override
	public List<Car> list(Map<String, Object> map){
		return carDao.findList(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return carDao.count(map);
	}
	
	@Override
	public int save(Car car){
		return carDao.insert(car);
	}
	
	@Override
	public int update(Car car){
		return carDao.update(car);
	}
	
	@Override
	public int remove(Long id){
		return carDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return carDao.batchRemove(ids);
	}
	
}
