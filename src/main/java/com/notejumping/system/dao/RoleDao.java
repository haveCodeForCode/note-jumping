package com.notejumping.system.dao;

import com.notejumping.system.persistence.BaseDao;
import com.notejumping.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author warry
 * @date 2017-10-02 20:24:47
 */
@Mapper
@Repository("RoleDao")
public interface RoleDao extends BaseDao<Role> {

    /**
     * 根据用户id查询角色表
     * @param params
     * @return
     */
    List<Role> findWithUserId(Map<String,Object> params);
}
