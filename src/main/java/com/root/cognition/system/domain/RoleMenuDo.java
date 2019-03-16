package com.root.cognition.system.domain;

/**
 * 角色关联信息
 *
 * @author 王睿
 * @version 2019/2/19
 */
public class RoleMenuDo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色菜单表主键
     * <p>
     * id
     */
    private String id;

    /**
     * 角色ID
     * <p>
     * RoleId
     */
    private String roleId;

    /**
     * 菜单ID
     * <p>
     * MenuId
     */
    private String menuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuDo{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", menuId='" + menuId + '\'' +
                '}';
    }
}
