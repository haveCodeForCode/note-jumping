package com.root.cognition.system.service.impl;


import com.root.cognition.system.dao.RoleDao;
import com.root.cognition.system.dao.RoleMenuDao;
import com.root.cognition.system.dao.UserDao;
import com.root.cognition.system.dao.UserRoleInfoDao;
import com.root.cognition.system.entity.Role;
import com.root.cognition.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";


    private RoleDao roleDao;
    private RoleMenuDao roleMenuDao;
    private UserDao userDao;
    private UserRoleInfoDao userRoleInfoDao;

    @Autowired
    public void setRoleMenuDao(RoleMenuDao roleMenuDao) {
        this.roleMenuDao = roleMenuDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    @Autowired
    public void setUserRoleInfoDao(UserRoleInfoDao userRoleInfoDao){ this.userRoleInfoDao = userRoleInfoDao; }


    @Override
    public List<Role> list() {
        List<Role> roles = roleDao.list(new HashMap<>(16));
        return roles;
    }


    @Override
    public List<Role> list(String userId) {
        List<Long> rolesIds = userRoleInfoDao.listRoleId(userId);
        List<Role> roles = roleMapper.list(new HashMap<>(16));
        for (Role roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Role role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        userRoleMapper.removeByRoleId(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public Role get(String id) {
        Role role = roleDao.get(id);
        return role;
    }

    @Override
    public int update(Role role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

}
