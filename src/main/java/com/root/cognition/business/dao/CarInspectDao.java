package com.root.cognition.business.dao;


import com.root.cognition.business.entity.CarInspect;
import com.root.cognition.system.persistence.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-09-19 14:16:54
 */
@Mapper
@Repository("CarInspectDao")
public interface CarInspectDao extends BaseDao<CarInspect> {

}
