package com.root.cognition.system.service;

import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单业务层
 * @author taoya
 */
@Service
public interface MenuService {

	/**
	 * 根据id查询菜单
	 * @param id
	 * @return
	 */
	SysMenu get(String id);

	/**
	 * 根据条件查询菜单
	 * @param params
	 * @return
	 */
	SysMenu get (Map<String, Object> params);

	/**
	 * 根据entity更新菜单
	 * @param sysMenu
	 * @return
	 */
	int update(SysMenu sysMenu);

	/**
	 * 获取菜单并形成树列
	 * @return
	 */
	Tree<SysMenu> getTree();

	/**
	 * 获取id对应的树列
	 * @param id
	 * @return
	 */
	Tree<SysMenu> getTree(String id);

	/**
	 * 保存entity
	 * @param sysMenu
	 * @return
	 */
	int save(SysMenu sysMenu);

	/**
	 * 根据id移除
	 * @param id
	 * @return
	 */
	int remove(String id);

	/**
	 * 根据userid查询对应user菜单形成树
	 * @param id
	 * @return
	 */
	Tree<SysMenu> getSysMenuTree(String id);

	/**
	 *
	 * @param id
	 * @return
	 */
	List<Tree<SysMenu>> listMenuTree(String id);

	/**
	 *
	 * @param params
	 * @return
	 */
	List<SysMenu> list(Map<String, Object> params);

	/**
	 *
	 * @param userId
	 * @return
	 */
	Set<String> menuListPerms(String userId);
}
