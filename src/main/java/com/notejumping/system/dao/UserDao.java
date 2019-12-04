package com.notejumping.system.dao;

import com.notejumping.system.persistence.BaseDao;
import com.notejumping.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @author 王睿
 * @version 2018/12/24
 */
@Mapper
@Repository("UserDao")
public interface UserDao extends BaseDao<User> {

    /**
     * 根据登陆传入信息擦护心用户（手机、邮箱）
     * @param loginInfo
     * @return
     */
    User getWihtLogininfo(String loginInfo);
}
