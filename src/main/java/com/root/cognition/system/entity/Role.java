package com.root.cognition.system.entity;

import com.root.cognition.common.persistence.BaseEntity;

import java.util.List;

/**
 * @author 王睿
 * @version 2018/12/28
 */
public class Role extends BaseEntity<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     * <p>
     * roleName
     */
    private String roleName;

    /**
     * 角色中文名称
     * <p>
     * roleEname
     */
    private String roleCname;


    /**
     * 角色权限(对外）
     * <p>
     * roleType
     */
    private String rolePermissions;

    /**
     * 数据范围
     * <p>
     * dataScope
     */
    private String dataScope;

    /**
     * 角色信息备注
     * <p>
     * remark
     */
    private String remark;

    /**
     * 角色菜单id列
     * <p>
     * menuIds
     */
    private List<Long> menuIds;

    public Role(long id) {
        super(id);
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCname() {
        return roleCname;
    }

    public void setRoleCname(String roleEname) {
        this.roleCname = roleEname;
    }

    public String getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(String rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", roleCname='" + roleCname + '\'' +
                ", rolePermissions='" + rolePermissions + '\'' +
                ", dataScope='" + dataScope + '\'' +
                ", remark='" + remark + '\'' +
                ", menuIds=" + menuIds +
                ", id='" + id + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
