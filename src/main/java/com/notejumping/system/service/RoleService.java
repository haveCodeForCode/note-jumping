package com.notejumping.system.service;

import com.notejumping.system.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色业务层
 *
 * @author taoya
 */
public interface RoleService {

	/**
	 * 根据用户ID查询角色菜单
	 *
	 * @param userId 用户ID
	 * @return 角色列
	 */
	List<Role> list(Long userId);

//******************************************************
	/**
	 * 根据id 查询角色
	 *
	 * @param id
	 * @return
	 */
	Role get(Long id);

	/**
	 * 根据条件查询
	 *
	 * @param params
	 * @return
	 */
	Role get(Map<String, Object> params);

	/**
	 * 按照条件查询批量角色
	 *
	 * @param params 查询条件
	 * @return 角色列
	 */
	List<Role> findList(Map<String, Object> params);

	/**
	 * 根据条件统计
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

	/**
	 * 获取用户实体保存角色
	 *
	 * @param role
	 * @return
	 */
	int save(Role role);

	/**
	 * 根据实体类更新
	 *
	 * @param role
	 * @return
	 */
	int update(Role role);

	/**
	 * 根据id 移除
	 *
	 * @param id
	 * @return
	 */
	int delete(Long id);


	/**
	 * 根据id 批量删除
	 *
	 * @param ids
	 * @return
	 */
	int batchDelect(Long[] ids);

}
