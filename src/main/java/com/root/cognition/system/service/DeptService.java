package com.root.cognition.system.service;


import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.entity.SysDept;

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
	 * 根据id 查询
	 * @param deptId
	 * @return
	 */
	SysDept get(String deptId);

	/**
	 * 根据条件查询
	 * @param map
	 * @return
	 */
	List<SysDept> list(Map<String, Object> map);

	/**
	 * 根据条件查询数量
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

	/**
	 * 根据entity插入（储存）
	 * @param sysDept
	 * @return
	 */
	int save(SysDept sysDept);

	/**
	 * 根据entity更新
	 * @param sysDept
	 * @return
	 */
	int update(SysDept sysDept);

	/**
	 * 根据id 删除
	 * @param deptId
	 * @return
	 */
	int remove(String deptId);

	/**
	 * 根据id 批量删除
	 * @param deptIds
	 * @return
	 */
	int batchRemove(String[] deptIds);

	/**
	 * 获取全平台部门打成树
	 * @return
	 */
	Tree<SysDept> getTree();

	/**
	 * 查询是否包含下级部门
	 * @param deptId
	 * @return
	 */
	boolean checkDeptHasUser(String deptId);

	/**
	 * 递归查询部门id下的所有部门
	 * @param parentId
	 * @return
	 */
	List<String> listChildrenIds(String parentId);
}
