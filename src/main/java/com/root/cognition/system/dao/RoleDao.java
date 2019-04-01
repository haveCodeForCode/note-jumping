package com.root.cognition.system.dao;

import com.root.cognition.common.persistence.BaseDao;
import com.root.cognition.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色
 *
 * @author warry
 * @date 2017-10-02 20:24:47
 */
@Mapper
@Repository("RoleDao")
public interface RoleDao extends BaseDao<SysRole> {

}
