package com.root.cognition.system.service.impl;


import com.root.cognition.system.dao.MenuDao;
import com.root.cognition.system.dao.RoleMenuDao;
import com.root.cognition.system.entity.Menu;
import com.root.cognition.common.persistence.Tree;
import com.root.cognition.system.service.MenuService;
import com.root.cognition.common.until.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 菜单服务层
 *
 * @author LineInkBook
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao;
	private RoleMenuDao roleMenuDao;
    private Menu menu;


    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    @Autowired
    public void setRoleMenuDao(RoleMenuDao roleMenuDao){
        this.roleMenuDao = roleMenuDao;
    }

    /**
     * 获取角色菜单
     * @param
     * @return 树形菜单
     */
    @Cacheable("SysMenu")
    @Override
    public Tree<Menu> getSysMenuTree(String id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menus = menuDao.listMenuByUserId(id);
        //循环获取系统菜单
        for (Menu sysMenu : menus) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenu.getId().toString());
            tree.setParentId(sysMenu.getParentId().toString());
            tree.setText(sysMenu.getMenuName());

            Map<String, Object> attributes = new HashMap<>(16);

            attributes.put("url", sysMenu.getMenuUrl());
            attributes.put("icon", sysMenu.getMenuIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> tMenu = BuildTree.build(trees);
        return tMenu;
    }

    @Override
    public List<Menu> list(Menu menu) {
        List<Menu> menus = menuDao.findList(menu);
        return menus;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int remove(String id) {
        int result = menuDao.delete(id);
        return result;
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int save(Menu menu) {
        int r = menuMapper.save(menu);
        return r;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int update(Menu menu) {
        int r = menuDao.update(menu);
        return r;
    }

    @Override
    public Menu get(Long id) {
        Menu menu = menuDao.get(id);
        return menu;
    }

    @Override
    public Set<String> listPerms(String userId) {
        return null;
    }

    @Override
    public Tree<Menu> getTree() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = menuDao.list(new HashMap<>(16));
        for (Menu sysMenuDO : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<Menu> getTree(Long id) {
        // 根据roleId查询权限
        List<Menu> menus = menuDao.list(new HashMap<String, Object>(16));
        List<Long> menuIds = roleMenuDao.listMenuIdByRoleId(id);
        List<Long> temp = menuIds;
        for (Menu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = menuDao.list(new HashMap<String, Object>(16));
        for (Menu sysMenuDO : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long menuId = sysMenuDO.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = menuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<Menu>> listMenuTree(Long id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<MenuDO>>();
        List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
        for (Menu sysMenuDO : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

}
