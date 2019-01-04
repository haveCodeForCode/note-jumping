package com.root.cognition.system.dao.user;

import com.root.cognition.system.dao.CrudDao;
import com.root.cognition.system.entity.user.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 王睿
 * @version 2018/12/24
 */
@Mapper
public interface UserDao extends CrudDao<UserDao> {

     User loginway(User user);
}
