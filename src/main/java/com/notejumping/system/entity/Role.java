package com.notejumping.system.entity;

import com.notejumping.system.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王睿
 * @version 2018/12/28
 */
public class Role extends BaseEntity<Role> implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     * <p>
     * roleName
     */
    private String name;

    /**
     * 角色中文名称
     * <p>
     * roleEname
     */
    private String cname;

    /**
     * 角色权限(对外）
     * <p>
     * roleType
     */
    private String permissions;

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
//************临时变量***************
    /**
     * 角色菜单id列
     * <p>
     * menuIds
     */
    private List<Long> menuIds;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
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

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

//    public String getRoleId() {
//        return roleId = String.valueOf(this.id);
//    }
//
//    public void setRoleId(String roleId) {
//        this.id = Long.parseLong(roleId);
//    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", cname='" + cname + '\'' +
                ", permissions='" + permissions + '\'' +
                ", dataScope='" + dataScope + '\'' +
                ", remark='" + remark + '\'' +
                ", menuIds=" + menuIds +
                ", id=" + id +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
