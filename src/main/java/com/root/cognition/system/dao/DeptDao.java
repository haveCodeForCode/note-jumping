package com.root.cognition.system.dao;

import com.root.cognition.common.persistence.BaseDao;
import com.root.cognition.system.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
@Mapper
@Repository("DeptDao")
public interface DeptDao extends BaseDao<Dept> {

	/**
	 * 查询父级部门
	 *
	 * @return
	 */
	String[] listParentDept();

}
