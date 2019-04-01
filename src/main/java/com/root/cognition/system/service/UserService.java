package com.root.cognition.system.service;

import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.entity.SysDept;
import com.root.cognition.system.entity.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	SysUser get(String id);

	/**
	 * 根据条件查询
	 * @param params
	 * @return
	 */
	SysUser get(Map<String, Object> params);

	/**
	 * 根据条件查询
	 * @param map
	 * @return
	 */
	List<SysUser> list(Map<String, Object> map);

	/**
	 * 根据条件统计
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

	/**
	 * 根据entity保存
	 * @param sysUser
	 * @return
	 */
	int save(SysUser sysUser);

	/**
	 * 根据entity更新
	 * @param sysUser
	 * @return
	 */
	int update(SysUser sysUser);

	/**
	 * 根据id 删除
	 * @param userId
	 * @return
	 */
	int remove(String userId);

	/**
	 * 根据 id数组批量删除
	 * @param userIds
	 * @return
	 */
	int batchremove(String[] userIds);

	/**
	 * 查询是否存在该用户
	 * @param params
	 * @return
	 */
	boolean exit(Map<String, Object> params);

	/**
	 *
	 * @param userId
	 * @return
	 */
	Set<String> listRoles(Long userId);

	/**
	 * 根据部门获取用户树
	 * @return
	 */
	Tree<SysDept> getTree();

//	int resetPwd(UserVO userVO, UserDO userDO) throws Exception;
//
//	int adminResetPwd(UserVO userVO) throws Exception;


	/**
	 * 更新个人信息
	 *
	 * @param sysUserDO
	 * @return
	 */
	int updatePersonal(SysUser sysUserDO);

	/**
	 * 更新个人图片
	 *
	 * @param file        图片
	 * @param avatar_data 裁剪信息
	 * @param userId      用户ID
	 * @throws Exception
	 */
//	Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
