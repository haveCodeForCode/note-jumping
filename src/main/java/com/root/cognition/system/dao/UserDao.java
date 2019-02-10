package com.root.cognition.system.dao;

import com.root.cognition.system.entity.User;
import com.root.cognition.system.persistence.CrudDao;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 王睿
 * @version 2018/12/24
 */
@Mapper
public interface UserDao extends CrudDao<User> {

     User loginway(User user);
}
