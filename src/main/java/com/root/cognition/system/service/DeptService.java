package com.root.cognition.system.service;


import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.entity.Dept;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {

	/**
	 * 获取全平台部门打成树列
	 *
	 * @return Tree
	 */
	Tree<Dept> getTree();

	/**
	 * 查询是否包含下级部门
	 *
	 * @param deptId
	 * @return
	 */
	boolean checkDeptHasUser(Long deptId);

	/**
	 * 递归查询部门id下的所有部门
	 *
	 * @param parentId
	 * @return
	 */
	List<Long> listChildrenIds(Long parentId);

	//*************************************************

	/**
	 * 根据id 查询
	 * @param deptId
	 * @return
	 */
	Dept get(Long deptId);

	/**
	 * 根据条件查询
	 *
	 * @param map
	 * @return
	 */
	Dept get(Map<String, Object> map);

	/**
	 * 根据条件查询
	 * @param map
	 * @return
	 */
	List<Dept> findList(Map<String, Object> map);

	/**
	 * 根据条件查询数量
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

	/**
	 * 根据entity插入（储存）
	 * @param dept
	 * @return
	 */
	int save(Dept dept);

	/**
	 * 根据entity更新
	 * @param dept
	 * @return
	 */
	int update(Dept dept);

	/**
	 * 根据id 删除
	 * @param deptId
	 * @return
	 */
	int delete(Long deptId);

	/**
	 * 根据id 批量删除
	 * @param deptIds
	 * @return
	 */
	int batchDelete(Long[] deptIds);


}
