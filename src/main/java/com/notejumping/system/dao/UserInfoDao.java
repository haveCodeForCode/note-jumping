package com.notejumping.system.dao;

import com.notejumping.system.persistence.BaseDao;
import com.notejumping.system.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户信息Dao层
 *
 * @author Worry
 * @version 2019/4/1
 */
@Mapper
@Repository("UserInfoDao")
public interface UserInfoDao extends BaseDao<UserInfo> {

}
