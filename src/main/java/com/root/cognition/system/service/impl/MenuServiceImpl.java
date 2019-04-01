package com.root.cognition.system.service.impl;


import com.root.cognition.common.persistence.Tree;
import com.root.cognition.common.until.BuildTree;
import com.root.cognition.system.dao.MenuDao;
import com.root.cognition.system.dao.RoleMenuDao;
import com.root.cognition.system.entity.SysMenu;
import com.root.cognition.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 菜单服务层
 * @author taoya
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao;

    private RoleMenuDao roleMenuDao;

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    @Autowired
    public void setRoleMenuDao(RoleMenuDao roleMenuDao) {
        this.roleMenuDao = roleMenuDao;
    }


    @Override
    public SysMenu get(String id) {
        return menuDao.get(id);
    }

    @Override
    public SysMenu get(Map<String, Object> params) {
        return menuDao.getByEntity(params);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int update(SysMenu sysMenu) {
        return menuDao.update(sysMenu);
    }

    @Override
    public List<SysMenu> list(Map<String, Object> params) {
        return menuDao.findList(params);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int remove(String id) {
        int result = menuDao.remove(id);
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int save(SysMenu sysMenu) {
        return menuDao.insert(sysMenu);
    }

    @Cacheable
    @Override
    public Tree<SysMenu> getSysMenuTree(String id) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenuList = menuDao.listMenuByUserId(id);
        for (SysMenu sysMenu : sysMenuList) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
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
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<SysMenu> getTree() {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenuDOS = menuDao.findList(new HashMap<>(16));
        for (SysMenu sysSysMenuDO : sysMenuDOS) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenuDO.getId().toString());
            tree.setParentId(sysSysMenuDO.getParentId().toString());
            tree.setText(sysSysMenuDO.getMenuName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<SysMenu> getTree(String id) {

        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();

        // 根据角色ID查询菜单列
        List<String> menuIds = roleMenuDao.listMenuIdByRoleId(id);

        List<SysMenu> sysMenuDOS = menuDao.findList(new HashMap<String, Object>(16));

        for (SysMenu sysSysMenuDO : sysMenuDOS) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenuDO.getId().toString());
            tree.setParentId(sysSysMenuDO.getParentId().toString());
            tree.setText(sysSysMenuDO.getMenuName());
            Map<String, Object> state = new HashMap<>(16);
            String menuId = sysSysMenuDO.getId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Set<String> menuListPerms(String userId) {
        List<String> perms = menuDao.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<SysMenu>> listMenuTree(String id) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenuDOS = menuDao.listMenuByUserId(id);
        for (SysMenu sysSysMenuDO : sysMenuDOS) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenuDO.getId().toString());
            tree.setParentId(sysSysMenuDO.getParentId().toString());
            tree.setText(sysSysMenuDO.getMenuName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysSysMenuDO.getMenuUrl());
            attributes.put("icon", sysSysMenuDO.getMenuIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

}
