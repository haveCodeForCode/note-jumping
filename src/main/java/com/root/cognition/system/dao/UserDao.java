package com.root.cognition.system.dao;

import com.root.cognition.common.persistence.BaseDao;
import com.root.cognition.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @author 王睿
 * @version 2018/12/24
 */
@Mapper
@Repository("UserDao")
public interface UserDao extends BaseDao<User> {

}
