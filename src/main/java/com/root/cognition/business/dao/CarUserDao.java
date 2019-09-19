package com.root.cognition.business.dao;

import com.root.cognition.business.entity.CarUser;
import com.root.cognition.system.persistence.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Worry
 * @version 2019/9/19
 */
@Mapper
@Repository("CarUserDao")
public interface CarUserDao extends BaseDao<CarUser> {

}
