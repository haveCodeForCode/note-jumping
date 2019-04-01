package com.root.cognition.system.service;

import com.root.cognition.system.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色业务层
 *
 * @author taoya
 */
@Service
public interface RoleService {

	/**
	 * 根据id 查询角色
	 *
	 * @param id
	 * @return
	 */
	SysRole get(String id);

	/**
	 * 根据条件查询
	 *
	 * @param params
	 * @return
	 */
	SysRole get(Map<String, Object> params);

	/**
	 * 按照条件查询批量角色
	 *
	 * @param params 查询条件
	 * @return 角色列
	 */
	List<SysRole> list(Map<String, Object> params);

	/**
	 * 根据用户ID查询角色菜单
	 *
	 * @param userId 用户ID
	 * @return 角色列
	 */
	List<SysRole> list(String userId);

	/**
	 * 获取用户实体保存角色
	 *
	 * @param sysRole
	 * @return
	 */
	int save(SysRole sysRole);

	/**
	 * 根据实体类更新
	 *
	 * @param sysRole
	 * @return
	 */
	int update(SysRole sysRole);

	/**
	 * 根据id 移除
	 *
	 * @param id
	 * @return
	 */
	int remove(String id);


	/**
	 * 根据id 批量删除
	 *
	 * @param ids
	 * @return
	 */
	int batchDelect(String[] ids);
}
