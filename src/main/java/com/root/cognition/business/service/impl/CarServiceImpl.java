package com.root.cognition.business.service.impl;

import com.root.cognition.business.dao.CarDao;
import com.root.cognition.business.dao.CarUserDao;
import com.root.cognition.business.entity.Car;
import com.root.cognition.business.entity.CarUser;
import com.root.cognition.business.service.CarService;
import com.root.cognition.common.config.Constant;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.codegenerate.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * 车辆服务内容
 * @author taoya
 */
@Service
public class CarServiceImpl implements CarService {
	
	private CarDao carDao;

	private CarUserDao carUserDao;

	@Autowired
	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}

	@Autowired
	public void setCarUserDao(CarUserDao carUserDao) {
		this.carUserDao = carUserDao;
	}

	@Override
	public CarUser checkCarNum(String carNum) {
		Map<String, Object> query = Query.withDelFlag();
		query.put("carNum", carNum);
		return carUserDao.getByEntity(query);
	}

	@Override
	public int saveCarUser(String carNum,Long userId) {
		Map<String, Object> query = Query.withDelFlag();
		query.put("carNum", carNum);
		Car car = carDao.getByEntity(query);
		return createCarUser(userId,car.getId());
	}

	@Override
	public Car get(Long id){
		return carDao.get(id);
	}

	@Override
	public Car getByEntity(Map<String, Object> query) {
		return carDao.getByEntity(query);
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
	public int save(Car car,Long userId){
		car.preInsert();
		if(carDao.insert(car)>0){
			return createCarUser(userId,car.getId());
		}
		return 0;
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

	/**
	 * 添加中间关系表
	 * @param userId
	 * @param carId
	 * @return
	 */
	private int createCarUser(Long userId,Long carId){
		CarUser carUser = new CarUser();
		Long sfuserId = SnowFlake.createSFid();
		carUser.setUserId(userId);
		carUser.setCarId(carId);
		carUser.setUserCarId(sfuserId);
		carUser.setDelFlag(Constant.DEL_FLAG_NORMAL);
		return carUserDao.insert(carUser);
	}
	
}
