package com.root.cognition.system.dao;

import com.root.cognition.common.persistence.BaseDao;
import com.root.cognition.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户相关信息Dao
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
@Repository("UserRelationDao")
public interface UserRoleDao extends BaseDao<SysUserRole> {

	List<String> listRoleId(String userId);

	int removeByUserId(String userId);

	int removeByRoleId(String roleId);

	int batchSave(List<SysUserRole> list);

	int batchRemoveByUserId(String[] ids);
}
