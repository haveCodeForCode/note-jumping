package com.root.cognition.system.entity;

/**
 * 用户
 * @author taoya
 */
public class SysUserRole {
    /**
     * 关联表主键
     * <p>
     * id
     */
    private String id;
    /**
     * 用户主键
     * <p>
     * userId
     */
    private String userId;
    /**
     * 角色主键
     * <p>
     *  roleId
     */
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
