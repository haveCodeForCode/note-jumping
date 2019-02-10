package com.root.cognition.system.entity;

import com.root.cognition.system.persistence.DataEntity;

import java.util.List;

/**
 * @author 王睿
 * @version 2018/12/28
 */
public class UserRole extends DataEntity<UserRole> {

    private String roleName;
    private User user;
    private List<Permission> permissions;


    public UserRole(String id) {
        super(id);
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleName='" + roleName + '\'' +
                ", user=" + user +
                ", permissions=" + permissions +
                '}';
    }
}
