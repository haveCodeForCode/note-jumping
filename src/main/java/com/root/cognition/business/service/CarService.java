package com.root.cognition.business.service;


import com.root.cognition.business.entity.Car;
import com.root.cognition.business.entity.CarUser;

import java.util.List;
import java.util.Map;

/**
 * 车辆表
 *
 * @author 1122
 * @date 2019-09-19 14:16:54
 */
public interface CarService {


    /**
     * 查询该用户是否关联该车
     * @param carNum
     * @return
     */
    CarUser checkCarNum(String carNum);

    /**
     * 保存中间关系
     * @param carNum
     * @return
     */
    int saveCarUser(String carNum,Long userId);

//**********************************************

    /**
     * 获取
     *
     * @param id
     * @return
     */
    Car get(Long id);

    /**
     * 通过实体获取
     * @param query
     * @return
     */
    Car getByEntity(Map<String,Object> query);
    /**
     * 查询列表
     * @param map
     * @return
     */
    List<Car> list(Map<String, Object> map);

    /**
     * 统计
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * 插入
     * @param car
     * @return
     */
    int save(Car car,Long userId);

    /**
     * 更新
     * @param car
     * @return
     */
    int update(Car car);

    /**
     * 移除
     * @param id
     * @return
     */
    int remove(Long id);

    /**
     * 批量移除
     * @param ids
     * @return
     */
    int batchRemove(Long[] ids);
}
