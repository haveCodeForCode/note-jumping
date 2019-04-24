package com.root.cognition.system.service.impl;


import com.root.cognition.common.config.DataDic;
import com.root.cognition.system.dao.RoleDao;
import com.root.cognition.system.dao.RoleMenuDao;
import com.root.cognition.system.dao.UserDao;
import com.root.cognition.system.dao.UserRoleDao;
import com.root.cognition.system.entity.Role;
import com.root.cognition.system.entity.RoleMenu;
import com.root.cognition.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 角色业务层
 *
 * @author taoya
 */
@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";


    private RoleDao roleDao;

    private RoleMenuDao roleMenuDao;

    private UserDao userDao;

    private UserRoleDao userRoleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setRoleMenuDao(RoleMenuDao roleMenuDao) {
        this.roleMenuDao = roleMenuDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public Role get(Map<String, Object> params) {
        return roleDao.getByEntity(params);
    }

    @Override
    public Role get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public List<Role> findList(Map<String, Object> params) {
        return roleDao.findList(params);
    }

    @Override
    public int count(Map<String, Object> map) {
        return roleDao.count(map);
    }

    @Override
    public List<Role> list(Long userId) {
        List<Long> rolesIds = userRoleDao.listRoleId(userId);
        List<Role> roles = roleDao.findList(new HashMap<>(16));
        for (Role role : roles) {
//            role.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(role.getId(), roleId)) {
//                    role.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(Role role) {
        int count = roleDao.insert(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getId();
        List<RoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuDao.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuDao.batchSave(rms);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        int count = roleDao.remove(id);
        userRoleDao.removeByRoleId(id);
        roleMenuDao.removeByRoleId(id);
        return count;
    }

    @Override
    public int update(Role role) {
        //更新角色对象
        int r = roleDao.update(role);
        //获取角色中菜单列
        List<Long> menuIds = role.getMenuIds();
        //获取角色id
        Long roleId = role.getId();
        if (roleId != null) {
            return DataDic.RETURN_STATUS_INFOBUG;
        }
        //移除所有角色id相关的菜单
        roleMenuDao.removeByRoleId(roleId);
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        if (roleMenuList.size() > 0) {
            roleMenuDao.batchSave(roleMenuList);
        }
        return r;
    }

    @Override
    public int batchDelect(Long[] ids) {
        return roleDao.batchRemove(ids);
    }


}
