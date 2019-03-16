package com.root.cognition.system.dao;

import com.root.cognition.common.persistence.BaseDao;
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
public interface UserRoleInfoDao extends BaseDao<UserRoleInfo> {


	List<Long> listRoleId(String userId);

	int removeByUserId(Long userId);

	int removeByRoleId(Long roleId);

	int batchSave(List<UserRoleInfo> list);

	int batchRemoveByUserId(Long[] ids);
}
