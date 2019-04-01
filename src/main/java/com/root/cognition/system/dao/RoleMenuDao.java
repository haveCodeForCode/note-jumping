package com.root.cognition.system.dao;

import com.root.cognition.common.persistence.BaseDao;
import com.root.cognition.system.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.List;

/**
 * @author 王睿
 * @version 2019/2/19
 */
@Mapper
@Repository("RoleMenuDao")
public interface RoleMenuDao extends BaseDao<RoleMenu> {
    /**
     * 根据role获取菜单列
     * @param roleId 角色ID
     * @return 菜单ID序列
     */
    List<String> listMenuIdByRoleId(String roleId);

    /**
     * 根据role删除菜单列
     * @param roleId 角色ID
     * @return 删除的数量
     */
    int removeByRoleId(String roleId);

    /**
     * 根据MenuId批量删除
     * @param menuId  菜单ID
     * @return 删除的数量
     */
    int removeByMenuId(String menuId);

    /**
     * 根据RoleMenu对象列批量插入
     * @param list RoleMenu列
     * @return 存入数量
     */
    int batchSave(List<RoleMenu> list);
}
