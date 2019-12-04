package com.notejumping.system.service.impl;


import com.notejumping.common.config.Constant;
import com.notejumping.common.until.codegenerate.SnowFlake;
import com.notejumping.system.dao.RoleDao;
import com.notejumping.system.dao.RoleMenuDao;
import com.notejumping.system.dao.UserDao;
import com.notejumping.system.dao.UserRoleDao;
import com.notejumping.system.entity.Role;
import com.notejumping.system.entity.RoleMenu;
import com.notejumping.system.service.RoleService;
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
        role.preInsert();
        role.setPermissions(Constant.STRING_ZERO);
        role.setDataScope(Constant.STRING_ZERO);
        //插入
        int count = roleDao.insert(role);
        //根据插入的角色批量修改角色菜单
        if (count>0) {
            List<Long> menuIds = role.getMenuIds();
            List<RoleMenu> rms = new ArrayList<>();
            for (Long menuId : menuIds) {
                RoleMenu rmDo = new RoleMenu();
                rmDo.setId(SnowFlake.createSFid());
                rmDo.setRoleId(role.getId());
                rmDo.setMenuId(menuId);
                rms.add(rmDo);
            }
            roleMenuDao.removeByRoleId(role.getId());
            if (rms.size() > 0) {
                roleMenuDao.batchSave(rms);
            }
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
        if (roleId == null) {
            return Constant.RETURN_STATUS_INFOBUG;
        }
        //移除所有角色id相关的菜单
        roleMenuDao.removeByRoleId(roleId);
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId(SnowFlake.createSFid());
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        if (roleMenuList.size() > 0) {
             r = roleMenuDao.batchSave(roleMenuList);
        }
        return r;
    }

    @Override
    public int batchDelect(Long[] ids) {
        return roleDao.batchRemove(ids);
    }


}
