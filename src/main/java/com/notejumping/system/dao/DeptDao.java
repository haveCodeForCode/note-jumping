package com.notejumping.system.dao;

import com.notejumping.system.persistence.BaseDao;
import com.notejumping.system.entity.Dept;
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

	/**
	 * 查询全部相关部门
	 *
	 * @return
	 */
	String[] listAllDept();
}
