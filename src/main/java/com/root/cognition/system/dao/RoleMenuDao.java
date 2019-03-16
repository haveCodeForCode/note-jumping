package com.root.cognition.system.dao;

import com.root.cognition.common.persistence.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 王睿
 * @version 2019/2/19
 */
@Mapper
@Repository("RoleMenuDao")
public interface RoleMenuDao extends BaseDao<RoleMenuDao> {

}
