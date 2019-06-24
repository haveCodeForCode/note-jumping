package com.root.cognition.system.service;

import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单业务层
 * @author taoya
 */
public interface MenuService {

	/**
	 * 获取菜单并形成树列
	 *
	 * @return
	 */
	Tree<Menu> getTree();

	/**
	 * 获取id对应的树列
	 *
	 * @param id
	 * @return
	 */
	Tree<Menu> getTree(String id);

	/**
	 * 根据userid查询对应user菜单形成树
	 *
	 * @param id
	 * @return
	 */
	Tree<Menu> getSysMenuTree(Long id);

	/**
	 * 根据用户id查询对应的数据
	 *
	 * @param id
	 * @return
	 */
	List<Tree<Menu>> listMenuTree(Long id);

	/**
	 * @param userId
	 * @return
	 */
	Set<String> menuListPerms(Long userId);

//*************************************************

	/**
	 * 根据id查询菜单
	 * @param id
	 * @return
	 */
	Menu get(Long id);

	/**
	 * 根据条件查询菜单
	 * @param params
	 * @return
	 */
	Menu get(Map<String, Object> params);

	/**
	 * 根据条件查询
	 * @param params
	 * @return
	 */
	List<Menu> findList(Map<String, Object> params);

	/**
	 * 根据条件统计
	 * @param map
	 * @return
	 */
	int count(Map<String, Object> map);

	/**
	 * 根据entity更新菜单
	 * @param menu
	 * @return
	 */
	int update(Menu menu);

	/**
	 * 保存entity
	 * @param menu
	 * @return
	 */
	int save(Menu menu);

	/**
	 * 根据id移除
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 批量删除
	 * @param menuIds
	 * @return
	 */
	int batchDelete(Long[] menuIds);

}
