package com.notejumping.system.service.impl;


import com.notejumping.common.until.BuildTree;
import com.notejumping.common.until.Query;
import com.notejumping.system.dao.MenuDao;
import com.notejumping.system.dao.RoleMenuDao;
import com.notejumping.system.entity.Menu;
import com.notejumping.system.service.MenuService;
import com.notejumping.system.persistence.Tree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 菜单服务层
 * @author taoya
 */
@Service
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
    public Menu get(Long id) {
        return menuDao.get(id);
    }

    @Override
    public Menu get(Map<String, Object> params) {
        return menuDao.getByEntity(params);
    }

    @Override
    public int count(Map<String, Object> map) {
        return menuDao.count(map);
    }

    /**
     * 根据对象更新
     * @param menu
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int update(Menu menu) {
        return menuDao.update(menu);
    }

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    @Override
    public List<Menu> findList(Map<String, Object> params) {
        List<Menu> menuList = menuDao.findList(params);
        return menuList;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        return menuDao.remove(id);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int save(Menu menu) {
        menu.preInsert();
        return menuDao.insert(menu);
    }


    @Override
    public Tree<Menu> getTree() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();

        Map<String, Object> query = Query.withDelFlag();
        List<Menu> menuDOS = menuDao.findList(query);
        for (Menu sysMenuDO : menuDOS) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        return BuildTree.build(trees);
    }

    /**
     * 获取一用户已选数
     * 逻辑：先取出除了父级的所有菜单的状态，
     *      在将所有菜单取出循环对比，赋值子菜单状态，回传树
     * @param id
     * @return
     */
    @Override
    public Tree<Menu> getTree(String id) {
        // 根据roleId查询权限
        //去除父级菜单
        Map<String,Object> query = Query.withDelFlag();
        List<Menu> menus = menuDao.findList(query);

        List<Long> menuIds = roleMenuDao.listMenuIdByRoleId(Long.parseLong(id));
        for (Menu menu : menus) {
            menuIds.remove(menu.getParentId());
        }
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        for (Menu sysMenuDO : menus) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long menuId = sysMenuDO.getId();
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
    public Set<String> menuListPerms(Long userId) {
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
    public List<Tree<Menu>> listMenuTree(Long id) {
        List<Menu> menuDOS = menuDao.listMenuByUserId(id);
        List<Tree<Menu>> trees = treeFormation(menuDOS);
        // 默认顶级菜单为０，根据数据库实际情况调整
        return BuildTree.buildList(trees, "0");
    }

    @Override
    public int batchDelete(Long[] menuIds) {
        return menuDao.batchRemove(menuIds);
    }

    /**
     * 形成树
     *
     * @param menuList
     * @return
     */
    private List<Tree<Menu>> treeFormation(List<Menu> menuList) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        for (Menu sysMenuDO : menuList) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        return trees;
    }
}
